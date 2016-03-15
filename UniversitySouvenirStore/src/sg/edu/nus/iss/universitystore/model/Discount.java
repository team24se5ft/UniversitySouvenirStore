package sg.edu.nus.iss.universitystore.model;

import java.time.LocalDate;

public class Discount {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String code;
	private String description;
	private String startDate;
	private int period;
	private float percentage;
	private String eligibilty;// "M" means Member,"A" means All

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public Discount(String code, String description, String startDate, int period, float percentage,
			String eligibilty) {
		this.code = code;
		this.description = description;
		this.startDate = startDate;
		this.period = period;
		this.percentage = percentage;
		this.eligibilty = eligibilty;
	}

	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}

	/**
	 * @return the eligibilty
	 */
	public String getEligibilty() {
		return eligibilty;
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
		return "Discount [code=" + code + ", description=" + description + ", startDate=" + startDate + ", period="
				+ period + ", percentage=" + percentage + ", eligibilty=" + eligibilty + "]";
	}
}
