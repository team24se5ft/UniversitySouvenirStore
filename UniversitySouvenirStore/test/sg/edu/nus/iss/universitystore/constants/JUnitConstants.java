package sg.edu.nus.iss.universitystore.constants;

import sg.edu.nus.iss.universitystore.constants.Constants;

public final class JUnitConstants {
	
	public static final class Common {
		public static final String DEFAULT_PATH = ""; 
	}
	
	public static final class Data {
		public enum FILE_FOLDER { TEST,DATA,BACKUP,INVENTORY, STOREKEEPER;}
		
		public static final String TEST_FILE_PATH = System.getProperty("user.dir") + 
				Constants.Data.FILE_PATH_SEPTR + FILE_FOLDER.TEST.toString().toLowerCase() + 
				Constants.Data.FILE_PATH_SEPTR + FILE_FOLDER.DATA.toString().toLowerCase() + 
				Constants.Data.FILE_PATH_SEPTR;
		
		public static final String BACKUP_FILE_PATH = System.getProperty("user.dir") + 
				Constants.Data.FILE_PATH_SEPTR + FILE_FOLDER.TEST.toString().toLowerCase() + 
				Constants.Data.FILE_PATH_SEPTR + FILE_FOLDER.DATA.toString().toLowerCase() + 
				Constants.Data.FILE_PATH_SEPTR + FILE_FOLDER.BACKUP.toString().toLowerCase() +
				Constants.Data.FILE_PATH_SEPTR;
		
	}
}
