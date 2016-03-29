package sg.edu.nus.iss.universitystore.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import sg.edu.nus.iss.universitystore.constants.Constants;

/**
 * Validation of Basic Data
 * 
 * @author Sanjay
 *
 */
class Validation {

	/**
	 * Is String value of Number Type
	 * 
	 * @param numberString
	 * @return
	 */
	static boolean isNumber(String numberString) {
		return numberString.matches(Constants.Common.Validation.NUMBER_MATCH);
	}

	/**
	 * Is String value of Float Type
	 * 
	 * @param floatString
	 * @return
	 */
	static boolean isFloat(String floatString) {
		return floatString.matches(Constants.Common.Validation.FLOAT_MATCH);
	}

	/**
	 * Is String value of Date Type, also checks if it a valid date
	 * 
	 * @param dateString
	 * @return
	 * @throws DateTimeParseException
	 */
	static boolean isDate(String dateString) throws DateTimeParseException {
		LocalDate date = null;
		if (dateString.matches(Constants.Common.Validation.DATE_MATCH))
			date = LocalDate.parse(dateString, Constants.Common.YYYY_MM_DD_FORMAT);
		return date != null;
	}
}
