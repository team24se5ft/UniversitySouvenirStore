package sg.edu.nus.iss.universitystore.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.exception.StoreException;
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
	private InventoryManager inventoryManager;
	
	/**
	 * Holds the reference to the view associated to show the inventory.
	 */
	private InventoryPanel inventoryPanel;
	/***********************************************************/
	// Constructors
	/***********************************************************/
	
	/**
	 * Inventory Controller Constructor
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws StoreException 
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
	}
	/***********************************************************/
	// Getters & setters
	/***********************************************************/
	public InventoryPanel getInventoryPanel() {
		return inventoryPanel;
	}
}
