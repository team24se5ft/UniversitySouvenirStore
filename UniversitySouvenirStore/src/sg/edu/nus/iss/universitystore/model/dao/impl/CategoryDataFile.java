package sg.edu.nus.iss.universitystore.model.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.dao.data.impl.DataFileImpl;

/**
 * Category Date Access Class
 * 
 * @author Sanjay
 *
 */
public class CategoryDataFile extends DataFileImpl<Category>{
	
	public CategoryDataFile() throws FileNotFoundException, IOException{
		super("Category");
	}
	
	public ArrayList<Category> getAllCategories() throws IOException{
		String[] categoryStrList = super.getAll();
		ArrayList<Category> categories = new ArrayList<>();
		
		for(String categoryStr : categoryStrList){
			String[] strSplit = categoryStr.split(",");
			categories.add(new Category(strSplit));
		}
		
		return categories;
	}

}
