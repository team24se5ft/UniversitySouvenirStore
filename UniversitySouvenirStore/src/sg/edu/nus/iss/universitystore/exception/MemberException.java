/**
 * 
 */
package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.messages.Messages;

/**
 * Member Exception
 * 
 * @author Samrat
 *
 */
public class MemberException extends Exception {

	/***********************************************************/
	// Enum listing all possible errors possible during member
	/***********************************************************/
	public enum MemberError {
		
	    INVALID_MEMBER_ID(Messages.Error.Member.INVALID_MEMBER_ID),
	    INVALID_MEMBER_NAME(Messages.Error.Member.INVALID_MEMBER_NAME),
	    INVALID_LOYALTY_POINTS(Messages.Error.Member.INVALID_LOYALTY_POINTS),
	    MEMBER_IDENTIFIER_ALREADY_PRESENT(Messages.Error.Member.MEMBER_IDENTIFIER_ALREADY_PRESENT),
	    MEMBER_NOT_PRESENT_IN_FILE(Messages.Error.Member.MEMBER_NOT_PRESENT_IN_FILE),
	    EMPTY_MEMBER_FIELDS(Messages.Error.Member.EMPTY_MEMBER_FIELDS),
	    ADD_EMPTY_MEMBER_FIELDS(Messages.Error.Member.ADD_EMPTY_MEMBER_FIELDS),
	    UNKNOWN_ERROR(Messages.Error.Common.UNKNOWN_ERROR)
	    ;
		
		/**
		 * Instance variable for holding the error message.
		 */
	    private final String message;

	    /**
	     * @param message The message associated with the enum.
	     */
	    private MemberError(final String message) {
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
	private MemberError error;
	
	/***********************************************************/
	// Getters
	/***********************************************************/

	public MemberError getError() {
		return error;
	}
	
	@Override
	public String getMessage() {
		return error.toString();
	}
	
	/***********************************************************/
	// Constructor
	/***********************************************************/
	
	public MemberException(MemberError error) {
		this.error = error;
	}
}
