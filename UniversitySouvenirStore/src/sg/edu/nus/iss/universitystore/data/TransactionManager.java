package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.TransactionException;
import sg.edu.nus.iss.universitystore.exception.TransactionException.TransactionError;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.Transaction;
import sg.edu.nus.iss.universitystore.model.TransactionItem;
import sg.edu.nus.iss.universitystore.model.TransactionReport;

/**
 * Manager class for handling the payment & transaction functionality.
 * 
 * @author Samrat
 *
 */
public class TransactionManager {

	/***********************************************************/
	// Enums
	/***********************************************************/

	/**
	 * Transaction Arguments
	 */
	public enum TransactionArg {
		IDENTIFIER(0), PRODUCT_ID(1), MEMBER_ID(2), QUANTITY(3), DATE(5);

		private int position;

		private TransactionArg(int position) {
			this.position = position;
		}
	}

	/***********************************************************/
	// Instance Variables
	/***********************************************************/

	/**
	 * The singleton instance of this class
	 */
	private static TransactionManager instance;

	/**
	 * The data file where the transactions will be written.
	 */
	private DataFile<Transaction> transactionData;

	/**
	 * Instance of Inventory Manager.
	 */
	private InventoryManager inventoryManager;

	/**
	 * Instance of Discount Manager.
	 */
	private DiscountManager discountManager;

	/**
	 * Instance of Member Manager
	 */
	private MemberManager memberManager;

	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/**
	 * Make the constructor private, since we provide the singleton instance.
	 * 
	 * @throws InventoryException
	 */
	private TransactionManager() throws TransactionException {
		try {
			initialize();
		} catch (DiscountException | MemberException | InventoryException exp) {
			throw new TransactionException(TransactionError.OTHER_ERROR, exp.getMessage());
		} catch (IOException ioExp) {
			throw new TransactionException(TransactionError.UNKNOWN_ERROR);
		}
	}

	/**
	 * Initialize all Data Files
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws StoreException
	 * @throws IOException
	 * @throws InventoryException
	 */
	private void initialize() throws DiscountException, MemberException, InventoryException, IOException {
		transactionData = new DataFile<>(Constants.Data.FileName.TRANSACTION_DAT);
		discountManager = DiscountManager.getInstance();
		inventoryManager = InventoryManager.getInstance();
		memberManager = MemberManager.getInstance();
	}

	/**
	 * Method to get the current transaction id that will be newly created.
	 * 
	 * @return The transaction id.
	 * @throws IOException
	 * @throws StoreException
	 */
	private int getTransactionId() throws TransactionException {
		// Get the transactionId
		int transactionId = 1;
		ArrayList<TransactionReport> allTransactions = getTransactionReport();

		if (allTransactions.size() != 0) {
			TransactionReport lastTransaction = allTransactions.get(allTransactions.size() - 1);
			transactionId = (Integer.valueOf(lastTransaction.getIdentifier())) + 1;
		}
		return transactionId;
	}

	/**
	 * Checks if the requested quantity exists or not.
	 * 
	 * @param arrTransactionItem
	 *            The list of transaction items that have been added to the
	 *            cart.
	 * @throws IOException
	 * @throws StoreException
	 */
	private void checkIfRequestedQuantityExistsInInventory(ArrayList<TransactionItem> arrTransactionItem)
			throws TransactionException {
		// Check the quantities using inventory manager
		for (TransactionItem transactionItem : arrTransactionItem) {
			Product product;
			try {
				product = inventoryManager.findProduct(transactionItem.getProduct().getIdentifier());
			} catch (InventoryException inventoryExp) {
				// TODO: Check functionality
				throw new TransactionException(TransactionError.OTHER_ERROR, inventoryExp.getMessage());
			}
			int availableQuantity = product.getQuantity();

			if (availableQuantity < transactionItem.getQuantity()) {
				throw new TransactionException(TransactionError.REQUESTED_QUANTITY_MORE_THAN_AVAILABLE);
			}
		}
	}

	/**
	 * Method to update the inventory quantity after the sale has taken place.
	 * 
	 * @param transactionItem
	 *            The transaction item that needs to be updated.
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException
	 */
	private boolean updateInventoryAfterSale(Transaction transaction) throws IOException, InventoryException {
		boolean status = true;
		for (int i = 0; i < transaction.getTransactionItemList().size(); i++) {
			TransactionItem transactionItem = transaction.getTransactionItemList().get(i);
			Product product = transactionItem.getProduct();
			int updatedQuantity = product.getQuantity() - transactionItem.getQuantity();
			product.setQuantity(updatedQuantity);
			if (!inventoryManager.updateProduct(product)) {
				status = false;
			}
		}
		return status;
	}

	private boolean updateLoyaltyPointsOfMemberAfterSale(String memberId, float totalAmount)
			throws TransactionException {
		// Update the member loyalty points
		if (!memberId.equals(ViewConstants.Labels.STR_PUBLIC)) {
			try {
				// Get member to update the loyalty point
				Member updatedMember = memberManager.getMember(memberId);
				// Calculate the loyalty points earned
				int earnedLoyaltyPoints = (int) totalAmount / 10;
				// Set the loyalty points to the new member
				updatedMember.setLoyaltyPoints(updatedMember.getLoyaltyPoints() + earnedLoyaltyPoints);
				// Update the dB
				return memberManager.updateMember(memberManager.getMember(memberId), updatedMember);
			} catch (MemberException e) {
				throw new TransactionException(TransactionError.UNABLE_TO_UPDATE_LOYALTY_POINTS);
			}
		}
		return true;
	}
	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * Get a single instance of TransactionManager
	 * 
	 * @return TransactionManager The singleton instance of this class.
	 * @throws TransactionException
	 * @throws InventoryException
	 */
	public static TransactionManager getInstance() throws TransactionException {
		if (instance == null) {
			synchronized (TransactionManager.class) {
				if (instance == null) {
					instance = new TransactionManager();
				}
			}
		}
		return instance;
	}

	/**
	 * (3.3.d) Get TransactionReports from Data File to fulfill the
	 * transactionTable
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<TransactionReport> getTransactionReport() throws TransactionException {
		ArrayList<TransactionReport> transactionList = new ArrayList<>();

		String[] transactionStrList;
		try {
			transactionStrList = transactionData.getAll();
		} catch (IOException e) {
			throw new TransactionException(TransactionError.UNKNOWN_ERROR);
		}

		for (String transactionStr : transactionStrList) {
			// If line in Data file is empty, skip line
			if (transactionStr.isEmpty())
				continue;

			String[] transactionStrSplt = transactionStr.split(Constants.Data.FILE_SEPTR);
			String productId = transactionStrSplt[TransactionArg.PRODUCT_ID.ordinal()];
			Product product;
			try {
				product = InventoryManager.getInstance().findProduct(productId);
				if (product == null) {
					continue;
				}
				int quantity = Integer.valueOf(transactionStrSplt[TransactionArg.QUANTITY.ordinal()]);
				String memberId = transactionStrSplt[TransactionArg.MEMBER_ID.ordinal()];
				String date = transactionStrSplt[TransactionArg.DATE.ordinal()];
				int Identifider = Integer.valueOf(transactionStrSplt[TransactionArg.IDENTIFIER.ordinal()]);
				TransactionItem transactionitem = new TransactionItem(product, quantity);
				transactionList.add(new TransactionReport(Identifider, transactionitem, memberId, date));
			} catch (InventoryException e) {
				e.printStackTrace();
			}
		}
		sortTransactionReport(transactionList);
		return transactionList;
	}

	/**
	 * query for transaction record by input startDate and endDate
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws TransactionException
	 */
	public ArrayList<TransactionReport> getTransactionReport(LocalDate startDate, LocalDate endDate)
			throws TransactionException {
		ArrayList<TransactionReport> transactionList = new ArrayList<>();

		String[] transactionStrList;
		try {
			transactionStrList = transactionData.getAll();
		} catch (IOException e) {
			throw new TransactionException(TransactionError.UNKNOWN_ERROR);
		}

		for (String transactionStr : transactionStrList) {
			// If line in Data file is empty, skip line
			if (transactionStr.isEmpty())
				continue;

			String[] transactionStrSplt = transactionStr.split(Constants.Data.FILE_SEPTR);
			String date = transactionStrSplt[TransactionArg.DATE.ordinal()];
			LocalDate localdate = LocalDate.parse(date);
			if (localdate.isBefore(startDate) || localdate.isAfter(endDate)) {
				continue;
			}
			String productId = transactionStrSplt[TransactionArg.PRODUCT_ID.ordinal()];
			Product product;
			try {
				product = InventoryManager.getInstance().findProduct(productId);
				if (product == null) {
					continue;
				}
				int quantity = Integer.valueOf(transactionStrSplt[TransactionArg.QUANTITY.ordinal()]);
				String memberId = transactionStrSplt[TransactionArg.MEMBER_ID.ordinal()];
				int Identifider = Integer.valueOf(transactionStrSplt[TransactionArg.IDENTIFIER.ordinal()]);
				TransactionItem transactionitem = new TransactionItem(product, quantity);
				transactionList.add(new TransactionReport(Identifider, transactionitem, memberId, date));
			} catch (InventoryException e) {
				e.printStackTrace();
			}
		}
		sortTransactionReport(transactionList);
		return transactionList;
	}

	/**
	 * (3.3.d) Get All Transactions from Data File,reserve for future utilize
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Transaction> getAllTransaction() throws TransactionException {
		ArrayList<Transaction> transactionList = new ArrayList<>();

		String[] transactionStrList;
		try {
			transactionStrList = transactionData.getAll();
		} catch (IOException e) {
			throw new TransactionException(TransactionError.UNKNOWN_ERROR);
		}
		String tempId = "1";
		for (String transactionStr : transactionStrList) {
			// If line in Data file is empty, skip line
			if (transactionStr.isEmpty())
				continue;

			String[] transactionStrSplt = transactionStr.split(Constants.Data.FILE_SEPTR);
			String productId = transactionStrSplt[TransactionArg.PRODUCT_ID.ordinal()];
			Product product;
			try {
				product = InventoryManager.getInstance().findProduct(productId);
				if (product == null) {
					continue;
				}
				int quantity = Integer.valueOf(transactionStrSplt[TransactionArg.QUANTITY.ordinal()]);
				String memberId = transactionStrSplt[TransactionArg.MEMBER_ID.ordinal()];
				String date = transactionStrSplt[TransactionArg.DATE.ordinal()];
				String Identifider = transactionStrSplt[TransactionArg.IDENTIFIER.ordinal()];
				TransactionItem transactionitem = new TransactionItem(product, quantity);
				ArrayList<TransactionItem> list = new ArrayList<TransactionItem>();
				if (Identifider.equals(String.valueOf(Identifider))) {
					list.add(transactionitem);
				} else {
					tempId = Identifider;
					transactionList.add(new Transaction(Identifider, list, memberId, date));
				}
			} catch (InventoryException e) {
				e.printStackTrace();
			}
		}
		return transactionList;
	}

	/**
	 * use default way to sort transaction Report by(product id)
	 * 
	 * @param reportList
	 */
	public void sortTransactionReport(ArrayList<TransactionReport> reportList) {
		reportList.sort(new Comparator<TransactionReport>() {

			@Override
			public int compare(TransactionReport report1, TransactionReport report2) {
				return report1.getItem().getProduct().getIdentifier()
						.compareTo(report2.getItem().getProduct().getIdentifier());
			}
		});
	}

	/**
	 * Method to get the total amount of an array of transaction items after the
	 * discount.
	 * 
	 * @param arrTransactionItem
	 *            The list of transaction items.
	 * @param discountId
	 *            The discount that is being offered.
	 * @return The total price of all the items.
	 */
	public float getTotal(ArrayList<TransactionItem> arrTransactionItem, String discountId) {
		// Get the total sum first
		float total = 0;
		for (TransactionItem transactionItem : arrTransactionItem) {
			total += transactionItem.getTotal();
		}

		// It is possible that no discount is applicable.
		if (discountId == null || discountId.length() == 0) {
			return total;
		}

		Discount discount;
		try {
			discount = discountManager.findDiscount(discountId);
			total *= ((1 - discount.getPercentage() * 0.01));
			return total;
		} catch (DiscountException e) {
			return total;
		}
	}

	/**
	 * Method to add write transactions to the file.
	 * 
	 * @param arrTransactionItem
	 *            The list of transaction items in the transaction.
	 * @param discountId
	 *            The discountId applied to the transaction.
	 * @param memberId
	 *            The member who is associated with the transaction.
	 * @return true is successfully written to file, else false.
	 */
	public boolean addTransaction(ArrayList<TransactionItem> arrTransactionItem, String discountId, String memberId)
			throws TransactionException {
		// Get the transactionId
		int transactionId = getTransactionId();
		// Check if the requested item exists, else throw exceptions
		checkIfRequestedQuantityExistsInInventory(arrTransactionItem);

		// Check if it valid discount
		if (discountId != null&& discountId.length() != 0) {
			// Check Discount Id
			try {
				discountManager.findDiscount(discountId);
			} catch (DiscountException e1) {
				throw new TransactionException(TransactionError.INVALID_DISCOUNT_ID);
			}
		}

		// Check if it is a valid member
		if (memberId != null || memberId.length() != 0) {
			if (!memberId.equals(ViewConstants.Labels.STR_PUBLIC)) {
				try {
					memberManager.getMember(memberId);
				} catch (MemberException e) {
					throw new TransactionException(TransactionError.INVALID_MEMBER_ID);
				}
			}
		} else {
			throw new TransactionException(TransactionError.INVALID_MEMBER_ID);
		}

		// Loop through all the elements
		// Create a new Transaction object
		Transaction transaction = new Transaction(transactionId, arrTransactionItem, memberId, LocalDate.now());
		try {
			transactionData.add(transaction);
			if (updateInventoryAfterSale(transaction)) {
				return updateLoyaltyPointsOfMemberAfterSale(memberId, getTotal(arrTransactionItem, discountId));
			} else {
				// TODO : Remove all the items that have been written because
				// something went wrong
				// Revert the constant value as well.
			}
		} catch (Exception e) {
			// TODO : Remove all the items that have been written because
			// something went wrong
			// Revert the constant value as well.
			return false;
		}
		return false;
	}
}
