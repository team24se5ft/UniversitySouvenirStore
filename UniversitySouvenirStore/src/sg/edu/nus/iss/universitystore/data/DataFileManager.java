package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.DataConstants;
import sg.edu.nus.iss.universitystore.constants.StoreConstants;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.data.DataFile;

/**
 * Common Data File Access Class
 * 
 * @author Sanjay
 *
 */
public class DataFileManager {

	private static DataFileManager instance;

	private DataFile<Category> categoryData;
	private DataFile<StoreKeeper> storeKeeperData;

	public DataFileManager() {
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
	public static DataFileManager getInstance() {
		if (instance == null) {
			synchronized (DataFileManager.class) {
				if (instance == null) {
					instance = new DataFileManager();
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
		categoryData = new DataFile<>(StoreConstants.CATEGORY_DAT);
		storeKeeperData = new DataFile<>(StoreConstants.STORE_KEEPER_DAT);

	}

	/**
	 * Get All Categories in Data File
	 * 
	 * @return List of all Categories
	 * @throws IOException
	 */
	public ArrayList<Category> getAllCategories() throws IOException {
		String[] categoryStrList = categoryData.getAll();
		ArrayList<Category> categories = new ArrayList<>();

		for (String categoryStr : categoryStrList) {
			categories.add(new Category(categoryStr.split(DataConstants.DAT_FILE_SEPRTR)));
		}

		return categories;
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
		String[] strKprStrList = storeKeeperData.getAll();
		ArrayList<StoreKeeper> storeKeeper = new ArrayList<>();

		for (String strKprStr : strKprStrList) {
			storeKeeper.add(new StoreKeeper(strKprStr.split(DataConstants.DAT_FILE_SEPRTR)));
		}

		return storeKeeper;
	}

}
