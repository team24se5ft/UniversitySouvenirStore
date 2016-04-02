package sg.edu.nus.iss.universitystore.model;

import sg.edu.nus.iss.universitystore.constants.Constants;

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

	public Discount(String code, String description, String startDate, String period, String percentage,
			String eligibilty) {
		this(code, description,
				startDate.equals(Constants.Data.Discount.ALWAYS) ? Constants.Data.Discount.ALWAYS : startDate,
				period.equals(Constants.Data.Discount.ALWAYS) ? -1 : Integer.parseInt(period),
				Float.parseFloat(percentage), eligibilty);
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
		return code + ",\"" + description + "\","
				+ (startDate.equals(Constants.Data.Discount.ALWAYS) ? Constants.Data.Discount.ALWAYS : startDate) + ","
				+ (period == Constants.Data.Discount.ALWAYS_VAL ? Constants.Data.Discount.ALWAYS : period) + "," + percentage + "," + eligibilty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eligibilty == null) ? 0 : eligibilty.hashCode());
		result = prime * result + Float.floatToIntBits(percentage);
		result = prime * result + period;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eligibilty == null) {
			if (other.eligibilty != null)
				return false;
		} else if (!eligibilty.equals(other.eligibilty))
			return false;
		if (Float.floatToIntBits(percentage) != Float.floatToIntBits(other.percentage))
			return false;
		if (period != other.period)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	public boolean equalsIgnoresPercentage(Object obj) {
		float tempPercentage = percentage;

		Discount other = (Discount) obj;
		percentage = other.percentage;

		boolean status = equals(other);

		percentage = tempPercentage;

		return status;
	}

}
