package sg.edu.nus.iss.universitystore.model.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.StoreConstants;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.model.dao.data.impl.DataFileImpl;

/**
 * Common Data File Access Class
 * 
 * @author Sanjay
 *
 */
public class DataFileAccess {
	
	public DataFileImpl<Category> categoryData;
	public DataFileImpl<StoreKeeper> storeKeeperData;
	
	public DataFileAccess() throws FileNotFoundException, IOException{
		initialize();
	}
	
	private void initialize() throws FileNotFoundException, IOException {
		categoryData = new DataFileImpl<>(StoreConstants.CATEGORY_DAT);
		storeKeeperData = new DataFileImpl<>(StoreConstants.STORE_KEEPER_DAT);
		
	}
	
	public ArrayList<Category> getAllCategories() throws IOException{
		String[] categoryStrList = categoryData.getAll();
		ArrayList<Category> categories = new ArrayList<>();
		
		for(String categoryStr : categoryStrList){
			String[] strSplit = categoryStr.split(",");
			categories.add(new Category(strSplit));
		}
		
		return categories;
	}

}
