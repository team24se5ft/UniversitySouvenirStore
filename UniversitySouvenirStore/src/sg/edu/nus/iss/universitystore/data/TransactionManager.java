package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Transaction;

public class TransactionManager {

	private static TransactionManager instance;
	
	private DataFile<Transaction> transactionData;
	
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
	
	
	/**
	 * Constructor
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
		} catch (StoreException e){
			// TODO Auto-generated Catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a single instance of TransactionManager
	 * 
	 * @return DataFileManager
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
	 * Initialize all Data Files
	 * @throws FileNotFoundException 
	 * 
	 * @throws StoreException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException, StoreException {
		transactionData = new DataFile<>(Constants.Data.FileName.TRANSACTION_DAT);
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

			transactionList.add(
					new Transaction(transactionStrSplt[TransactionArg.IDENTIFIER.ordinal()],transactionStrSplt[TransactionArg.PRODUCT_ID.ordinal()] ,transactionStrSplt[TransactionArg.MEMBER_ID.ordinal()],transactionStrSplt[TransactionArg.QUANTITY.ordinal()],transactionStrSplt[TransactionArg.DATE.ordinal()]));
		}

		return transactionList;
	}

	/**
	 * Add Transaction to Data file
	 */
	
	public Transaction addTransaction() throws IOException,StoreException{
		Transaction trans=new Transaction(trans_id, prod_id, member_id, quantity, date);
	}
}
