/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.intf;

/**
 * @author Samrat
 *
 */
public interface ILoginDelegate {

	/**
	 * Method called when the login button is clicked.
	 * @param username The username input by the  user.
	 * @param password The password input by the user.
	 */
	public void loginButtonClicked(String username, String password);
}
