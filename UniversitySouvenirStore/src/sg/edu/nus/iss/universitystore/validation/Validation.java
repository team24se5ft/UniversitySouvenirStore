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
abstract class Validation {

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
	
	/**
	 * Is String of Type Code. i.e. consists of only alphabets in capitals
	 * 
	 * @param codeString
	 * @return
	 */
	static boolean isCode(String codeString) {
		return codeString.matches(Constants.Common.Validation.CODE_MATCH);
	}
	
	static boolean isName(String nameString) {
		return !nameString.trim().isEmpty() && nameString.matches(Constants.Common.Validation.NAME_MATCH);
	}
}
