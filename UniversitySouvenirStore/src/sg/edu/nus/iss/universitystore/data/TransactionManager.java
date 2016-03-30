package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.exception.TransactionException;
import sg.edu.nus.iss.universitystore.exception.TransactionException.TransactionError;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.Transaction;
import sg.edu.nus.iss.universitystore.model.TransactionItem;
import sg.edu.nus.iss.universitystore.utility.CommonUtils;

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
	private TransactionManager() throws TransactionException, InventoryException {
		try {
			initialize();
		} catch (IOException | DiscountException | MemberException | StoreException e) {
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
	private void initialize()
			throws IOException, DiscountException, MemberException, StoreException, InventoryException {
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
	private int getTransactionId() throws IOException, StoreException {
		// Get the transactionId
		int transactionId = 1;
		ArrayList<Transaction> allTransactions = getAllTransactions();

		if (allTransactions.size() != 0) {
			Transaction lastTransaction = allTransactions.get(allTransactions.size() - 1);
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
			throws IOException, StoreException {
		// Check the quantities using inventory manager
		for (TransactionItem transactionItem : arrTransactionItem) {
			Product product = inventoryManager.findProduct(transactionItem.getProduct().getIdentifier());
			int availableQuantity = product.getQuantity();

			if (availableQuantity < transactionItem.getQuantity()) {
				// TODO - Move to constants
				throw new StoreException(CommonUtils.MessageTitleType.ERROR,
						"Requested quantity more than that available in the store",
						CommonUtils.MessageType.ERROR_MESSAGE);
			}
		}
	}

	private void updateInventoryAfterSale(TransactionItem transactionItem)
			throws IOException, StoreException, InventoryException {
		Product product = transactionItem.getProduct();
		int updatedQuantity = product.getQuantity() - transactionItem.getQuantity();
		product.setQuantity(updatedQuantity);
		inventoryManager.updateProduct(product);
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
	public static TransactionManager getInstance() throws TransactionException, InventoryException {
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
	 * (3.3.d) Get All Transactions from Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Transaction> getAllTransactions() throws IOException {
		ArrayList<Transaction> transactionList = new ArrayList<>();
		String[] transactionStrList = transactionData.getAll();

		for (String transactionStr : transactionStrList) {
			// If line in Data file is empty, skip line
			if (transactionStr.isEmpty())
				continue;

			String[] transactionStrSplt = transactionStr.split(Constants.Data.FILE_SEPTR);

			transactionList.add(new Transaction(transactionStrSplt[TransactionArg.IDENTIFIER.ordinal()],
					transactionStrSplt[TransactionArg.PRODUCT_ID.ordinal()],
					transactionStrSplt[TransactionArg.MEMBER_ID.ordinal()],
					transactionStrSplt[TransactionArg.QUANTITY.ordinal()],
					transactionStrSplt[TransactionArg.DATE.ordinal()]));
		}
		return transactionList;
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
		if(discountId == null || discountId.length() == 0) {
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
			throws StoreException, IOException {
		// Get the transactionId
		int transactionId = getTransactionId();
		// Check if the requested item exists
		checkIfRequestedQuantityExistsInInventory(arrTransactionItem);

		if(discountId != null || discountId.length() != 0) {
			// Check Discount Id
			try {
				discountManager.findDiscount(discountId);
			} catch (DiscountException e1) {
				throw new StoreException(CommonUtils.MessageTitleType.ERROR, "Invalid discount ID.",
						CommonUtils.MessageType.ERROR_MESSAGE);
			}
		}

		try {// TODO - Remove this.
				// Check member Id
			if (memberManager.getMember(memberId) == null) {
				if (!memberId.equals("PUBLIC")) {// TODO - Move to constants
					// TODO - Move to constants
					throw new StoreException(CommonUtils.MessageTitleType.ERROR, "Invalid Member ID.",
							CommonUtils.MessageType.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Return Value
		boolean returnValue = false;

		// Loop through all the elements
		for (TransactionItem transactionItem : arrTransactionItem) {
			// Create a new Transaction object
			Transaction transaction = new Transaction(transactionId, transactionItem.getProduct().getIdentifier(),
					memberId, transactionItem.getQuantity(), LocalDate.now());
			try {
				returnValue = transactionData.add(transaction);
				updateInventoryAfterSale(transactionItem);
			} catch (Exception e) {
				// TODO : Remove all the items that have been written because
				// something went wrong
				// Revert the constant value as well.
			}
		}
		// If something went wrong, then we clear up all the contents that have
		// been written for this transaction.
		if (!returnValue) {
			// Revert the constant value as well
		}
		return returnValue;
	}
}
