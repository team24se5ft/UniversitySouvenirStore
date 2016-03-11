package sg.edu.nus.iss.universitystore.model.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.model.dao.data.impl.DataFileImpl;

/**
 * Store Keeper Date Access Class
 * 
 * @author Sanjay
 *
 */
public class StoreKeeperDataFile extends DataFileImpl<StoreKeeper>{

	public StoreKeeperDataFile() throws FileNotFoundException, IOException{
		super("StoreKeeper");
	}
}
