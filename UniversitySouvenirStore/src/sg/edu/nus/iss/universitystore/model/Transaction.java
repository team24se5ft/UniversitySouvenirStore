package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String identifier;
	private String productId;
	private String memberId;
	private int quantity;
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
	public Transaction(int identifier, String productId, String memberId, int quantity, LocalDate date) {
		// Set the values
		this.identifier = String.valueOf(identifier);// TODO - Validate if this
														// gives an integer
		this.productId = productId;
		this.memberId = memberId;
		this.quantity = quantity;
		this.date = date;// TODO - Put validation for getting the correct format
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
	public Transaction(String identifier, String productId, String memberId, String quantity, String date) {
		// Call the main constructor
		this(Integer.parseInt(identifier), productId, memberId, Integer.parseInt(quantity), LocalDate.parse(date));
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
	public String getProductId() {
		return productId;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
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
		return identifier + "," + productId + "," + memberId + "," + quantity + "," + date;
	}
}
