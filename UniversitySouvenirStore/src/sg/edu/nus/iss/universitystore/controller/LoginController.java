package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.LoginManager;
import sg.edu.nus.iss.universitystore.exception.LoginException;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.validation.LoginValidation;
import sg.edu.nus.iss.universitystore.view.LoginPanel;
import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;

/**
 * @author Samrat
 *
 */
public class LoginController implements ILoginDelegate {

	/***********************************************************/
	// Constants
	/***********************************************************/
	public static final String STR_INCORRECT_LOGIN_MESSAGE = "The username or password you have entered is invalid. Please try again..";
	public static final String STR_USERNAME_CANNOT_BE_EMPTY = "The username or password cannot be empty.";
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private LoginPanel loginPanel;
	private LoginManager loginManager;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public LoginController() {
		// Instantiate the panel & set its listener to this.
		loginPanel = new LoginPanel();
		loginPanel.setDelegate(this);

		// Instantiate Data File Manager
		try {
			loginManager = LoginManager.getInstance();
		} catch (LoginException loginExp) {
			UIUtils.showMessageDialog(loginPanel, ViewConstants.StatusMessage.ERROR,
					loginExp.getMessage(), DialogType.ERROR_MESSAGE);
		}
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public JPanel getLoginPanel() {
		return loginPanel;
	}

	/***********************************************************/
	// Interface Implementations
	/***********************************************************/
	@Override
	public void loginButtonClicked(String username, String password) {
		StoreKeeper storeKeeper = new StoreKeeper(username, password);
		
		try {
			if(LoginValidation.isValidData(username, password) && loginManager.isValidCredentials(storeKeeper)) {
				UIUtils.navigateToDashboard(loginPanel);
			}
		} catch (LoginException loginExp) {
			UIUtils.showMessageDialog(loginPanel, ViewConstants.StatusMessage.ERROR,
					loginExp.getMessage(), DialogType.ERROR_MESSAGE);
		}
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

}
