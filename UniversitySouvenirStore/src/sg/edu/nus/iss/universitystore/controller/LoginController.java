package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.Constants;
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
	//Constants
	/***********************************************************/
	public static final String STR_INCORRECT_LOGIN_MESSAGE = "The username or password you have entered is invalid. Please try again..";
	
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
		//TODO
		//FIXME - Read from file
		//SMRT - Hard Coding now for 
		if(username.equals("admin") && password.equals("admin")){
			//Move to dashboard screen
			UIUtils.navigateToDashboard(loginPanel);
		}
		else {
			UIUtils.showMessageDialog(loginPanel, Constants.STR_WARNING, STR_INCORRECT_LOGIN_MESSAGE, DialogType.WARNING_MESSAGE);
		}
		
		
		StoreKeeper storeKeeper = new StoreKeeper(username, password);
		
		//SMRT - Commented the below lines, waiting for Sanjay to commit the files.
		
		// Accessing Date from StoreKeeper.dat
//		try {
//			// DataFileImpl<StoreKeeper> storeKeeperData = new DataFileImpl<>(StoreConstants.STORE_KEEPER_DAT);
//			DataFileAccess dataFileAccess = new DataFileAccess();
//			// String[] dataStr = storeKeeperData.getAll();
//			String[] dataStr = dataFileAccess.storeKeeperData.getAll();
//			for(String line : dataStr){
//				StoreKeeper storeKeeperDat = new StoreKeeper(line.split(","));
//				if(storeKeeper.equals(storeKeeperDat)){
//					System.out.println("Password Match !!!");
//					break;
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/***********************************************************/
	//Private Methods
	/***********************************************************/
	
}
