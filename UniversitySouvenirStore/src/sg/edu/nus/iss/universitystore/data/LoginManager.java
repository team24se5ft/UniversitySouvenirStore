package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.UniversityStoreLoginException;
import sg.edu.nus.iss.universitystore.exception.UniversityStoreLoginException.LoginError;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;

/**
 * Manager to handle Login for Store Keeper
 * 
 * @author Joshua
 *
 */
public class LoginManager {

	private static LoginManager instance;

	private DataFile<StoreKeeper> storeKeeperData;

	private LoginManager() throws UniversityStoreLoginException {
		try {
			initialize();
		} catch (IOException e) { 
			throw new UniversityStoreLoginException(LoginError.UNKNOWN_ERROR);
		}
	}

	/**
	 * Get a single instance of Data File Manager
	 * 
	 * @return DataFileManager
	 */
	public static LoginManager getInstance() throws UniversityStoreLoginException {
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
	 * Initialize all Date Files
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		storeKeeperData = new DataFile<>(Constants.Data.FileName.STORE_KEEPER_DAT);
	}

	/**
	 * Delete instance of Data File Manager
	 */
	public static void deleteInstance() {
		instance = null;
	}

	/**
	 * Checks if Entered Credentials are Valid
	 * 
	 * @param enteredCredentials
	 *            Store Keeper Credentials
	 * @return Boolean
	 * @throws IOException
	 */
	public boolean isValidCredentials(StoreKeeper enteredCredentials) throws UniversityStoreLoginException {
		// Check if valid data has been provided by the user.
		if (enteredCredentials.getUserName().length() == 0 && enteredCredentials.getPassword().length() == 0) {
			throw new UniversityStoreLoginException(LoginError.EMPTY_USERNAME_AND_PASSWORD);
		} else if (enteredCredentials.getUserName().length() == 0) {
			throw new UniversityStoreLoginException(LoginError.EMPTY_USERNAME);
		} else if (enteredCredentials.getPassword().length() == 0) {
			throw new UniversityStoreLoginException(LoginError.EMPTY_PASSWORD);
		}

		// Check if the data matches as in dB
		ArrayList<StoreKeeper> credentials = getLoginCredentials();
		for (StoreKeeper credentailsData : credentials) {
			if(credentailsData.equals(enteredCredentials)) {
				return true;
			}
		}
		// Throw an exception.
		throw new UniversityStoreLoginException(LoginError.INVALID_CREDENTIALS);
	}

	/**
	 * Get All Login credentials
	 * 
	 * @return All Store Keeper Credentials
	 * @throws UniversityStoreLoginException
	 */
	private ArrayList<StoreKeeper> getLoginCredentials() throws UniversityStoreLoginException {
		// Catch the exception from parent & inform the controller.
		String[] storeKprStrList;
		try {
			storeKprStrList = storeKeeperData.getAll();
		} catch (IOException e) {
			throw new UniversityStoreLoginException(LoginError.UNKNOWN_ERROR);
		}

		ArrayList<StoreKeeper> storeKeeper = new ArrayList<>();
		for (String strKprStr : storeKprStrList) {

			if (strKprStr.isEmpty())
				continue;

			storeKeeper.add(new StoreKeeper(strKprStr.split(Constants.Data.FILE_SEPTR)));
		}

		return storeKeeper;
	}

}
