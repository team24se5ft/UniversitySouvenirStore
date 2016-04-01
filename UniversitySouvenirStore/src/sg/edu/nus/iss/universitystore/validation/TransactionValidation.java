package sg.edu.nus.iss.universitystore.validation;

import java.time.LocalDate;

import sg.edu.nus.iss.universitystore.exception.TransactionException;
import sg.edu.nus.iss.universitystore.exception.TransactionException.TransactionError;

/**
 * Validations for Transactions
 * 
 * @author Sanjay
 *
 */
public class TransactionValidation extends Validation {
	
	/**
	 * Validations Start Date and End Date entry
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Boolean
	 * @throws TransactionException
	 */
	public static boolean isValidData(String startDateStr, String endDateStr) throws TransactionException{
		
		if(!isDate(startDateStr))
			throw new TransactionException(TransactionError.INVALID_START_DATE);
		
		if(!isDate(endDateStr))
			throw new TransactionException(TransactionError.INVALID_END_DATE);
		
		LocalDate startDate=LocalDate.parse(startDateStr);
		LocalDate endDate=LocalDate.parse(endDateStr);
		
		if(endDate.isBefore(startDate)) {
			throw new TransactionException(TransactionError.END_DATE_BEFORE_START_DATE);
		}
		
		return true;
	}
}
