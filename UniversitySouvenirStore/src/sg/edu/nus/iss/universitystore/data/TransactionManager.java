package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Transaction;
import sg.edu.nus.iss.universitystore.model.TransactionItem;

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
	
	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/**
	 * Make the constructor private, since we provide the singleton instance.
	 */
	private TransactionManager() {
		try {
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StoreException e) {
			// TODO Auto-generated Catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize all Data Files
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws StoreException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException, StoreException {
		transactionData = new DataFile<>(Constants.Data.FileName.TRANSACTION_DAT);
		discountManager = new DiscountManager();
		inventoryManager = new InventoryManager();
	}
	
	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * Get a single instance of TransactionManager
	 * 
	 * @return TransactionManager The singleton instance of this class.
	 */
	public static TransactionManager getInstance() {
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
	 * Method to get the total amount of an array of transaction items after the discount.
	 * @param arrTransactionItem The list of transaction items.
	 * @param discountId The discount that is being offered.
	 * @return
	 */
	public float getTotal(ArrayList<TransactionItem> arrTransactionItem ,String discountId) throws IOException{
		// Get the total sum first
		float total = 0;
		for(TransactionItem transactionItem : arrTransactionItem) {
			total += transactionItem.getTotal();
		}
		
		// It is possible that no discount is applicable.
		Discount discount = discountManager.findDiscount(discountId);
		if(discount != null) {
			
		}
		return total;
	}
	/**
	 * Add Transaction to Data file
	 */

	/*
	 * public Transaction addTransaction() throws IOException,StoreException{
	 * Transaction trans=new Transaction(trans_id, prod_id, member_id, quantity,
	 * date); }
	 */
}
