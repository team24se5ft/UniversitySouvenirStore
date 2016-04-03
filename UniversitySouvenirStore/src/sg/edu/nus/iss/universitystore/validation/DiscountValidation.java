package sg.edu.nus.iss.universitystore.validation;

import java.time.format.DateTimeParseException;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.DiscountException.DiscountError;

/**
 * Validations for Discount
 * 
 * @author Sanjay
 *
 */
public class DiscountValidation extends Validation {

	/**
	 * Validates format of contents of Discount
	 * 
	 * @param code
	 * @param description
	 * @param startDate
	 * @param period
	 * @param percentage
	 * @param eligibilty
	 * @return Boolean
	 * @throws DiscountException
	 */
	public static boolean isValidData(String code, String description, String startDate, String period,
			String percentage, String eligibilty) throws DiscountException {

		// Check for empty fields
		if (code.isEmpty() || description.isEmpty() || startDate.isEmpty() || period.isEmpty() || percentage.isEmpty()
				|| eligibilty.isEmpty())
			throw new DiscountException(DiscountError.EMPTY_DISCOUNT_FIELDS);

		// Check if Discount Code contains only alphabets
		if (!isCode(code))
			throw new DiscountException(DiscountError.INVALID_CODE);

		// Check if Percentage format is incorrect
		if (!isFloat(percentage))
			throw new DiscountException(DiscountError.INVALID_PERCENTAGE);
		else if (!percentage.matches(Constants.Data.Discount.Pattern.PERCENTAGE_MATCH))
			throw new DiscountException(DiscountError.INVALID_PERCENTAGE);

		// Check if Date is of valid format
		try {
			if (!isDate(startDate) && !startDate.matches(Constants.Data.Discount.Pattern.START_DATE_MATCH)) {
				throw new DiscountException(DiscountError.INVALID_START_DATE);
			}
		} catch (DateTimeParseException dateTimeExp) {
			throw new DiscountException(DiscountError.INVALID_START_DATE);
		}

		// Check if Period is of correct format
		if (!isNumber(period) && !period.matches(Constants.Data.Discount.Pattern.PERIOD_MATCH))
			throw new DiscountException(DiscountError.INVALID_PERIOD);

		// Check if Eligibility is M or A
		if (!eligibilty.matches(Constants.Data.Discount.Pattern.ELIGIBILITY_MATCH))
			throw new DiscountException(DiscountError.INVALID_ELIGIBILITY);

		return isValidValue(startDate, period, percentage);
	}

	/**
	 * Validates if the period is <=365 and if percentage is < 100
	 * 
	 * @param dataLine
	 * @return Boolean
	 */
	private static boolean isValidValue(String startDateStr, String periodStr, String percentageStr) throws DiscountException {
		float percentage = Float.parseFloat(percentageStr);

		if (!(percentage > 0 && percentage <= 100))
			throw new DiscountException(DiscountError.INVALID_PERCENTAGE_RANGE);
		
		if(startDateStr.equals(Constants.Data.Discount.ALWAYS) && !periodStr.equals(Constants.Data.Discount.ALWAYS))
			throw new DiscountException(DiscountError.START_DATE_PERIOD_ALWAYS);
		
		if(!startDateStr.equals(Constants.Data.Discount.ALWAYS) && periodStr.equals(Constants.Data.Discount.ALWAYS))
			throw new DiscountException(DiscountError.PERIOD_START_DATE_ALWAYS);
		
		if(periodStr.equals(Constants.Data.Discount.ALWAYS))
			return true;
		
		int period = Integer.parseInt(periodStr);

		if (!(period > 0))
			throw new DiscountException(DiscountError.INVALID_PERIOD);

		return true;

	}
}
