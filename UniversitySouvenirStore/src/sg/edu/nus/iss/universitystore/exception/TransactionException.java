/**
 * 
 */
package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.exception.MemberException.MemberError;
import sg.edu.nus.iss.universitystore.messages.Messages;

/**
 * @author Samrat
 *
 */
public class TransactionException extends Exception{
	/***********************************************************/
	// Enum listing all possible errors possible during login
	/***********************************************************/
	public enum TransactionError {
		INVALID_DISCOUNT_ID(Messages.Error.Transaction.INVALID_DISCOUNT_ID),
		REQUESTED_QUANTITY_MORE_THAN_AVAILABLE(Messages.Error.Transaction.REQUESTED_QUANTITY_MORE_THAN_AVAILABLE),
		INVALID_MEMBER_ID(Messages.Error.Transaction.INVALID_MEMBER_ID),
		UNABLE_TO_UPDATE_LOYALTY_POINTS(Messages.Error.Transaction.UNABLE_TO_UPDATE_LOYALTY_POINTS),
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
	
	/***********************************************************/
	// Getters
	/***********************************************************/

	public TransactionError getError() {
		return error;
	}
	
	@Override
	public String getMessage() {
		return error.toString();
	}
	
	/***********************************************************/
	// Constructor
	/***********************************************************/
	
	public TransactionException(TransactionError error) {
		this.error = error;
	}
}
