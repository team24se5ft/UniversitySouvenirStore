package sg.edu.nus.iss.universitystore.constants;

/**
 * Constants for Data Access Objects
 * 
 * @author Sanjay
 *
 */
public final class DataConstants {
	public static final String EXTENSION = ".dat";
	public static final String NEW_LINE = "\n";
	public static final String DIR_SEPARATOR = "\\";
	public static final String DAT_FILE_PATH = System.getProperty("user.dir") + "\\data\\";
	public static final String DAT_FILE_SEPRTR = ",";
	public static final String DAT_FILE_EMPTY = "";
	public static final int LOYALTY_NEW_MEMBER = -1;
	public static final String MEMBER_NOT_FOUND = "Member not found for the given member id";
}
