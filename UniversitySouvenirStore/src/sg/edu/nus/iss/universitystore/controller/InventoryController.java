package sg.edu.nus.iss.universitystore.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;

/**
 * @author Samrat
 *
 */
public class InventoryController implements IInventoryDelegate{
	
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * Instance of the Inventory Manager for retrieving the data from the dB.
	 */
	private InventoryManager inventoryManager;
	
	/**
	 * Holds the reference to the view associated to show the inventory.
	 */
	private InventoryPanel inventoryPanel;
	
	/**
	 * The list of all categories.
	 */
	private ArrayList<Category> arrCategory;
	
	/**
	 * 
	 */
	private ArrayList<Product> arrProduct;
	
	/***********************************************************/
	// Constructors
	/***********************************************************/
	
	/**
	 * Inventory Controller Constructor 
	 */
	public InventoryController(){
		try {
			inventoryManager = InventoryManager.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
		
		// Initialize the panel
		inventoryPanel = new InventoryPanel(this);
		
		// TODO: Setup the various components of the panel with the data retrieved from the manager.
	}
	
	/***********************************************************/
	// Getters & setters
	/***********************************************************/
	public InventoryPanel getInventoryPanel() {
		return inventoryPanel;
	}

	/***********************************************************/
	// IInventoryDelegate Implementation
	/***********************************************************/
	
	@Override
	public void addCategoryClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCategoryClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategoryClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editProductClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProductClicked() {
		// TODO Auto-generated method stub
		
	}
}
