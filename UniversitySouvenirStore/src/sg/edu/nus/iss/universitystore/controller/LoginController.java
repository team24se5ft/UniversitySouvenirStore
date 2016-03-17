package sg.edu.nus.iss.universitystore.controller;

import java.io.IOException;


import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.Constants;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.LoginPanel;
import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;
import sg.edu.nus.iss.universitystore.data.LoginManager;

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
		loginManager = LoginManager.getInstance();
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
		if (username.length() > 0 && password.length() > 0) {
			try {
				// Validate Credentials
				if (loginManager.isValidCredentials(storeKeeper)) {
					UIUtils.navigateToDashboard(loginPanel);
				} else {
					UIUtils.showMessageDialog(loginPanel, Constants.STR_WARNING, STR_INCORRECT_LOGIN_MESSAGE,
							DialogType.WARNING_MESSAGE);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			UIUtils.showMessageDialog(loginPanel, Constants.STR_WARNING, STR_USERNAME_CANNOT_BE_EMPTY,
					DialogType.WARNING_MESSAGE);
		}
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

}
