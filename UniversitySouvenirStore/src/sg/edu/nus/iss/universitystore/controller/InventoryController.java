package sg.edu.nus.iss.universitystore.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import sg.edu.nus.iss.universitystore.data.InventoryManager;

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
	 */
	public InventoryController() throws FileNotFoundException, IOException{
		inventoryManager = InventoryManager.getInstance();
	}

}
