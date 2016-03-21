package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	public Transaction(String identifier,String productId,String memberId,String quantity,String date){
		this.identifier=identifier;
		this.productId=productId;
		this.memberId=memberId;
		this.quantity=Integer.parseInt(quantity);
		
		//Convert String to LocalDate. Remove if unnecessary.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		this.date = LocalDate.parse(date, formatter);
	}
	
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
