package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Transaction Domain Object
 * 
 * @author Samrat
 *
 */
public class Transaction {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String identifier;
	private ArrayList<TransactionItem> itemList;
	private String memberId;
	private LocalDate date;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Constructor for one object of a Transaction
	 * 
	 * @param identifier
	 *            The unique identifier of this transaction.
	 * @param productId
	 *            The product id associated with this transaction.
	 * @param memberId
	 *            The member if associated with this transaction.
	 * @param quantity
	 *            The quantity of the particular product
	 * @param date
	 *            The date of the transaction
	 */
	public Transaction(int identifier, ArrayList<TransactionItem> itemList, String memberId, LocalDate date) {
		this.identifier = String.valueOf(identifier);
		this.itemList = itemList;
		this.memberId = memberId;
		this.date = date;
	}

	/**
	 * Constructor for getting all the values as strings.
	 * 
	 * @param identifier
	 *            The unique identifier of this transaction.
	 * @param productId
	 *            The product id associated with this transaction.
	 * @param memberId
	 *            The member if associated with this transaction.
	 * @param quantity
	 *            The quantity of the particular product
	 * @param date
	 *            The date of the transaction
	 */
	public Transaction(String identifier, ArrayList<TransactionItem> itemList, String memberId, String date) {
		// Call the main constructor
		this(Integer.parseInt(identifier), itemList, memberId, LocalDate.parse(date));
	}
	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the productId
	 */
	public ArrayList<TransactionItem> getTransactionItemList() {
		return itemList;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String value="";
		for(TransactionItem item : itemList){
			value+=identifier + "," + item.getProduct().getIdentifier() + "," + memberId + "," + item.getQuantity() + "," + date.toString();
			value+="\n";
		}
		return value;
	}
}
