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
	    EMPTY_MEMBER_NAME(Messages.Error.Member.EMPTY_MEMBER_NAME),
	    EMPTY_MEMBER_ID(Messages.Error.Member.EMPTY_MEMBER_ID),
	    INVALID_MEMBER_NAME(Messages.Error.Member.INVALID_MEMBER_NAME),
	    MEMBER_IDENTIFIER_ALREADY_PRESENT(Messages.Error.Member.MEMBER_IDENTIFIER_ALREADY_PRESENT),
	    MEMBER_NOT_PRESENT_IN_FILE(Messages.Error.Member.MEMBER_NOT_PRESENT_IN_FILE),
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
