package sg.edu.nus.iss.universitystore.config;

import java.util.ResourceBundle;

public final class Configuration {
	
	private static ResourceBundle rb;
	private static String pathSeptr;
	
	static{
		rb = ResourceBundle.getBundle("config");
		pathSeptr = System.getProperty("os.name").startsWith("WIN")?"//":"\\";
		
	}
	
	/***********************************************************/
	// Data File Configurations
	/***********************************************************/
	/**
	 * Data File Extension
	 */
	public static final String DATA_FILE_EXT =  rb.getString("data.file.extension");
	/**
	 * Data File content separator
	 */
	public static final String DATA_FILE_SEPTR =  rb.getString("data.file.separator");
	/**
	 * Data File Path
	 */
	public static final String DATA_FILE_PATH =  System.getProperty("user.dir") + 
			pathSeptr + rb.getString("data.file.folder") + pathSeptr;
	
	/***********************************************************/
	// Data File Names
	/***********************************************************/
	/**
	 * Category Data File Name
	 */
	public static final String CATEGORY_DAT = rb.getString("data.name.category");
	/**
	 * Discount Data File Name
	 */
	public static final String DISCOUNT_DAT = rb.getString("data.name.discount");
	/**
	 * Product Data File Name
	 */
	public static final String PRODUCT_DAT = rb.getString("data.name.product");
	/**
	 * Member Data File Name
	 */
	public static final String MEMBER_DAT = rb.getString("data.name.member");
	/**
	 * Store Keeper Data File Name
	 */
	public static final String STOR_KEEPER_DAT = rb.getString("data.name.store.keeper");
	/**
	 * Transaction Data File Name
	 */
	public static final String TRANSACTION_DAT = rb.getString("data.name.transaction");
	/**
	 * Vendor Data File Name
	 */
	public static final String VENDOR_DAT = rb.getString("data.name.vendor");
	

}
