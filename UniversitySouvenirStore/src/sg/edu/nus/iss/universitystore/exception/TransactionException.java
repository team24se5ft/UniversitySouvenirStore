/**
 * 
 */
package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.messages.Messages;

/**
 * Transaction Exception
 * 
 * @author Samrat
 *
 */
public class TransactionException extends Exception{
	/***********************************************************/
	// Enum listing all possible errors possible during transaction
	/***********************************************************/
	public enum TransactionError {
		INVALID_DISCOUNT_ID(Messages.Error.Transaction.INVALID_DISCOUNT_ID),
		REQUESTED_QUANTITY_MORE_THAN_AVAILABLE(Messages.Error.Transaction.REQUESTED_QUANTITY_MORE_THAN_AVAILABLE),
		INVALID_MEMBER_ID(Messages.Error.Transaction.INVALID_MEMBER_ID),
		UNABLE_TO_UPDATE_LOYALTY_POINTS(Messages.Error.Transaction.UNABLE_TO_UPDATE_LOYALTY_POINTS),
		OTHER_ERROR(Messages.Error.Common.OTHER_ERROR),
		INVALID_START_DATE(Messages.Error.Transaction.INVALID_START_DATE),
		INVALID_END_DATE(Messages.Error.Transaction.INVALID_END_DATE),
		END_DATE_BEFORE_START_DATE(Messages.Error.Transaction.END_DATE_BEFORE_START_DATE),
		INVALID_LOYALTY_POINTS_APPLIED(Messages.Error.Transaction.INVALID_LOYALTY_POINTS_APPLIED),
	    UNKNOWN_ERROR(Messages.Error.Common.UNKNOWN_ERROR)
	    ;
		
		/**
		 * Instance variable for holding the error message.
		 */
	    private final String message;

	    /**
	     * @param message The message associated with the enum.
	     */
	    private TransactionError(final String message) {
	        this.message = message;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return message;
	    }
	}
	
	/***********************************************************/
	// Instance variables
	/***********************************************************/
	private TransactionError error;
	private String customMessage;
	
	/***********************************************************/
	// Getters
	/***********************************************************/

	public TransactionError getError() {
		return error;
	}
	
	@Override
	public String getMessage() {
		return error.toString()
				+ (customMessage==null||customMessage.isEmpty() ? Constants.Common.EMPTY_STR : Constants.Common.NEW_LINE + customMessage);
	}

	/***********************************************************/
	// Constructor
	/***********************************************************/
	public TransactionException(TransactionError error) {
		this.error = error;
	}

	public TransactionException(TransactionError error, String customMessage) {
		this(error);
		this.customMessage = customMessage;
	}
}
