package sg.edu.nus.iss.universitystore.controller;

import java.io.IOException;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.constants.StoreConstants;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.model.dao.data.impl.DataFileImpl;
import sg.edu.nus.iss.universitystore.model.dao.impl.DataFileAccess;
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
		StoreKeeper storeKeeper = new StoreKeeper(username, password);
		
		// Accessing Date from StoreKeeper.dat
		try {
			// DataFileImpl<StoreKeeper> storeKeeperData = new DataFileImpl<>(StoreConstants.STORE_KEEPER_DAT);
			DataFileAccess dataFileAccess = new DataFileAccess();
			// String[] dataStr = storeKeeperData.getAll();
			String[] dataStr = dataFileAccess.storeKeeperData.getAll();
			for(String line : dataStr){
				StoreKeeper storeKeeperDat = new StoreKeeper(line.split(","));
				if(storeKeeper.equals(storeKeeperDat)){
					System.out.println("Password Match !!!");
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
