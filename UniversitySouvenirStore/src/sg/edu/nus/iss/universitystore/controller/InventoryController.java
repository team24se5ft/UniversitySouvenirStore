package sg.edu.nus.iss.universitystore.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.exception.StoreException;

public class InventoryController {
	
	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private InventoryManager inventoryManager;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	/**
	 * Inventory Controller Constructor
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws StoreException 
	 */
	public InventoryController() throws FileNotFoundException, IOException, StoreException{
		inventoryManager = InventoryManager.getInstance();
	}

}
