package sg.edu.nus.iss.universitystore.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.exception.StoreException;

public class InitializeTest {
	
	private static InitializeTest instance;

	private static Boolean isJUnit = null;
		
	public InitializeTest(){
		InitializeTest.isJUnit = true;
	}
	
	/***********************************************************/
	// Singleton
	/***********************************************************/
	/**
	 * Get a single instance of Initialize Test
	 * 
	 * @return DataFileManager
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws StoreException 
	 */
	public static InitializeTest getInstance() {
		if (instance == null) {
			synchronized (InventoryManager.class) {
				if (instance == null) {
					instance = new InitializeTest();
				}
			}
		}
		return instance;
	}

	public static boolean isJUnit() {		
		return instance == null ? false : isJUnit;
	}
	
	public static boolean destroyInstance(){
		instance = null;
		return instance == null;
	}
}
