package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
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

	private LoginManager() {
		try {
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a single instance of Data File Manager
	 * 
	 * @return DataFileManager
	 */
	public static LoginManager getInstance() {
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
	public boolean isValidCredentials(StoreKeeper enteredCredentials) throws IOException {
		ArrayList<StoreKeeper> credentials = getLoginCredentials();
		for (StoreKeeper credentailsData : credentials) {
			if (credentailsData.equals(enteredCredentials))
				return true;
		}
		return false;
	}

	/**
	 * Get All Login credentials
	 * 
	 * @return All Store Keeper Credentials
	 * @throws IOException
	 */
	private ArrayList<StoreKeeper> getLoginCredentials() throws IOException {
		String[] storeKprStrList = storeKeeperData.getAll();
		ArrayList<StoreKeeper> storeKeeper = new ArrayList<>();

		for (String strKprStr : storeKprStrList) {
			
			if(strKprStr.isEmpty())
			continue;
			
			storeKeeper.add(new StoreKeeper(strKprStr.split(Constants.Data.FILE_SEPTR)));
		}

		return storeKeeper;
	}

}
