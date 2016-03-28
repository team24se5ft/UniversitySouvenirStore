package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.messages.Messages;

/**
 * @author Samrat
 *
 */
public class DiscountException extends Exception {

	/***********************************************************/
	// Enum listing all possible errors possible during login
	/***********************************************************/
	public enum DiscountError {
	    EMPTY_DISCOUNT_FIELDS(Messages.Error.Discount.EMPTY_DISCOUNT_FIELDS),
	    DISCOUNT_NOT_PRESENT_IN_FILE(Messages.Error.Discount.DISCOUNT_NOT_PRESENT_IN_FILE),
	    DISCOUNT_ALREADY_PRESENT(Messages.Error.Discount.DISCOUNT_ALREADY_PRESENT),
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
	    private DiscountError(final String message) {
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
	private DiscountError error;
	
	/***********************************************************/
	// Getters
	/***********************************************************/

	public DiscountError getError() {
		return error;
	}
	
	@Override
	public String getMessage() {
		return error.toString();
	}
	
	/***********************************************************/
	// Constructor
	/***********************************************************/
	
	public DiscountException(DiscountError error) {
		this.error = error;
	}
}
