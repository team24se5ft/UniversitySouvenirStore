package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;

public class Transaction {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String identifier;
	private String productId;
	private String memberId;
	private int quantity;
	private LocalDate date;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	
	/***********************************************************/
	//Getters & Setters
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
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [identifier=" + identifier + ", productId=" + productId + ", memberId=" + memberId
				+ ", quantity=" + quantity + ", date=" + date + "]";
	}
}
