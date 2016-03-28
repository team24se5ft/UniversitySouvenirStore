/**
 * 
 */
package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.messages.Messages;

/**
 * @author Samrat
 *
 */
public class UniversityStoreLoginException extends Exception {
	
	/***********************************************************/
	// Enum listing all possible errors possible during login
	/***********************************************************/
	public enum LoginError {
	    EMPTY_USERNAME(Messages.Error.Login.EMPTY_USERNAME),
	    EMPTY_PASSWORD(Messages.Error.Login.EMPTY_PASSWORD),
	    EMPTY_USERNAME_AND_PASSWORD(Messages.Error.Login.EMPTY_USERNAME_AND_PASSWORD),
	    INVALID_CREDENTIALS(Messages.Error.Login.INVALID_CREDENTIALS),
	    UNKNOWN_ERROR(Messages.Error.Common.UNKNOWN_ERROR)
	    ;
		
		/**
		 * Instance variable for holding the error message.
		 */
	    private final String message;

	    /**
	     * @param message The message associated with the enum.
	     */
	    private LoginError(final String message) {
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
	private LoginError error;
	
	/***********************************************************/
	// Getters
	/***********************************************************/

	public LoginError getError() {
		return error;
	}
	
	@Override
	public String getMessage() {
		return error.toString();
	}
	
	/***********************************************************/
	// Constructor
	/***********************************************************/
	
	public UniversityStoreLoginException(LoginError error) {
		this.error = error;
	}
}
