package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.view.LoginPanel;
import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;

/**
 * @author Samrat
 *
 */
public class LoginController implements ILoginDelegate {
	
	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private LoginPanel loginPanel;

	/***********************************************************/
	//Constructors
	/***********************************************************/
	public LoginController() {
		loginPanel = new LoginPanel();
		loginPanel.setLoginListener(this);
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	public JPanel getLoginPanel(){
		return loginPanel;
	}

	/***********************************************************/
	//Interface Implementations
	/***********************************************************/
	@Override
	public void loginButtonClicked(String username, String password) {
		//Validate the credentials here.
		System.out.println(username + "\n" + password);
	}
}
