package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.LoginException;
import sg.edu.nus.iss.universitystore.exception.LoginException.LoginError;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.validation.LoginValidation;

/**
 * Manager to handle Login for Store Keeper
 * 
 * @author Joshua
 *
 */
public class LoginManager {
	
	/***********************************************************/
	// Enums
	/***********************************************************/

	/**
	 * Login Arguments
	 */
	public enum LoginArg {
		USERNAME(0), PASSWORD(1);

		private int position;

		private LoginArg(int position) {
			this.position = position;
		}
	}

	/***********************************************************/
	// Instance Variables
	/***********************************************************/

	/**
	 * Instance of Login Manager
	 */
	private static LoginManager instance;
	/**
	 * Data File for Store Keeper
	 */
	private DataFile<StoreKeeper> storeKeeperData;

	/***********************************************************/
	// Singleton
	/***********************************************************/

	/**
	 * Get a single instance of Data File Manager
	 * 
	 * @return DataFileManager
	 */
	public static LoginManager getInstance() throws LoginException {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Delete instance of Data File Manager
	 */
	public static void deleteInstance() {
		instance = null;
	}

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Login Manager Constructor
	 * 
	 * @throws LoginException
	 */
	private LoginManager() throws LoginException {
		try {
			initialize();
		} catch (IOException e) {
			throw new LoginException(LoginError.UNKNOWN_ERROR);
		}
	}

	/***********************************************************/
	// Private Methods for Constructors
	/***********************************************************/

	/**
	 * Initialize all Date Files
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		storeKeeperData = new DataFile<>(Constants.Data.FileName.STORE_KEEPER_DAT);
	}

	/***********************************************************/
	// Validation for Login
	/***********************************************************/

	/**
	 * Validates Login Data
	 * 
	 * @param dataList
	 * @return Boolean
	 */
	public boolean isValidLoginData(String[] dataList) {
		try {
			return LoginValidation.isValidData(dataList[LoginArg.USERNAME.ordinal()],
					dataList[LoginArg.PASSWORD.ordinal()]);
		} catch (LoginException e) {
			return false;
		}
	}

	/***********************************************************/
	// Private Methods for Login
	/***********************************************************/

	/**
	 * Get All Login credentials
	 * 
	 * @return All Store Keeper Credentials
	 * @throws LoginException
	 */
	private ArrayList<StoreKeeper> getLoginCredentials() throws LoginException {
		// Catch the exception from parent & inform the controller.
		String[] storeKprStrList;
		try {
			storeKprStrList = storeKeeperData.getAll();
		} catch (IOException e) {
			throw new LoginException(LoginError.UNKNOWN_ERROR);
		}

		ArrayList<StoreKeeper> storeKeeper = new ArrayList<>();
		for (String storKprStr : storeKprStrList) {

			String[] storLprStrSpltLst = storKprStr.split(Constants.Data.FILE_SEPTR);

			if (storLprStrSpltLst.length != 2 || !isValidLoginData(storLprStrSpltLst))
				continue;

			storeKeeper.add(new StoreKeeper(storLprStrSpltLst[LoginArg.USERNAME.ordinal()],
					storLprStrSpltLst[LoginArg.PASSWORD.ordinal()]));
		}

		return storeKeeper;
	}

	/***********************************************************/
	// Public Methods for Login
	/***********************************************************/

	/**
	 * Checks if Entered Credentials are Valid
	 * 
	 * @param enteredCredentials
	 *            Store Keeper Credentials
	 * @return Boolean
	 * @throws LoginException
	 */
	public boolean isValidCredentials(StoreKeeper enteredCredentials) throws LoginException {
		// Check if the data matches as in dB
		ArrayList<StoreKeeper> credentials = getLoginCredentials();
		for (StoreKeeper credentailsData : credentials) {
			if (credentailsData.equals(enteredCredentials)) {
				return true;
			}
		}
		// Throw an exception.
		throw new LoginException(LoginError.INVALID_CREDENTIALS);
	}

}
