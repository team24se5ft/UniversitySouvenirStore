package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;

public class TransactionReport {
	private int identifier;
	private TransactionItem item;// item need to be shown in report
	private String memberId; // memberId
	private LocalDate date; // transaction created date
	
	

	public TransactionReport(int identifier,TransactionItem item, String memberId, String date) {
		this.identifier=identifier;
		this.item = item;
		this.memberId = memberId;
		this.date = LocalDate.parse(date);
	}

	public TransactionItem getItem() {
		return item;
	}

	public void setItem(TransactionItem item) {
		this.item = item;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	

}
