package sg.edu.nus.iss.universitystore.constants;

public final class Constants {

	public static final class Common {
		public static final String EMPTY_STR = "";
		public static final String NEW_LINE = "\n";
	}
	
	public static final class Data {
		
		// TODO: Is this the best solution
		public enum FILE_FOLDER { TEST,DATA;}
		
		// TODO: Come up with better solution
		public static final String FILE_PATH_SEPTR = System.getProperty("os.name").toUpperCase().startsWith("WIN")?"\\":"//";
		public static final String FILE_EXT = ".dat";
		public static final String FILE_SEPTR = ",";
		public static final String FILE_FLDR = "data";
		public static final String FILE_PATH = System.getProperty("user.dir") + 
				FILE_PATH_SEPTR + FILE_FOLDER.DATA.toString().toLowerCase() + FILE_PATH_SEPTR;
		
		public static final String ID_SEPTR = "/";
		
		public static final String TEST_FILE_PATH = System.getProperty("user.dir") + 
				FILE_PATH_SEPTR + FILE_FOLDER.TEST.toString().toLowerCase() + 
				FILE_PATH_SEPTR + FILE_FOLDER.DATA.toString().toLowerCase() + FILE_PATH_SEPTR;
		
		public static final class FileName {
			public static final String CATEGORY_DAT = "Category";
			public static final String DISCOUNT_DAT = "Discount";
			public static final String MEMBER_DAT = "Member";
			public static final String PRODUCT_DAT = "Product";
			public static final String STORE_KEEPER_DAT = "StoreKeeper";
			public static final String TRANSACTION_DAT = "Transaction";
			public static final String VENDOR_DAT = "Vendor";
			
		}
	}
}
