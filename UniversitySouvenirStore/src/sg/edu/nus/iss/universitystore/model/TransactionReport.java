package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;

/**
 * Transaction Report
 * 
 * @author Boyuan
 *
 */
public class TransactionReport {
	
	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private int identifier;
	private TransactionItem item;
	private String memberId;
	private LocalDate date;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	/**
	 * Transaction Report Constructor
	 * 
	 * @param identifier
	 * @param item
	 * @param memberId
	 * @param date
	 */
	public TransactionReport(int identifier,TransactionItem item, String memberId, String date) {
		this.identifier=identifier;
		this.item = item;
		this.memberId = memberId;
		this.date = LocalDate.parse(date);
	}
	
	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	/**
	 * @return the item
	 */
	public TransactionItem getItem() {
		return item;
	}

	/**
	 * @return the member id
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

	/**
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}
	

}
