package sg.edu.nus.iss.universitystore.controller;

import java.io.IOException;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.Constants;
import sg.edu.nus.iss.universitystore.data.DataFileManager;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
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

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private LoginPanel loginPanel;
	private DataFileManager dataFileManager;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public LoginController() {
		loginPanel = new LoginPanel();
		loginPanel.setLoginListener(this);

		// Instantiate Data File Manager
		dataFileManager = DataFileManager.getInstance();

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
			// Validate Credentials
			if (dataFileManager.isValidCredentials(storeKeeper)) {
				UIUtils.navigateToDashboard(loginPanel);
			} else {
				// UIUtils.showMessageDialog(loginPanel, Constants.STR_WARNING,
				// STR_INCORRECT_LOGIN_MESSAGE, DialogType.WARNING_MESSAGE);
				UIUtils.showMessageDialog(loginPanel, Constants.STR_WARNING, STR_INCORRECT_LOGIN_MESSAGE,
						DialogType.WARNING_MESSAGE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

}
