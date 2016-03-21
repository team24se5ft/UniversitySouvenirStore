package sg.edu.nus.iss.universitystore.constants;

/**
 * @author Samrat
 *
 */
public class ViewConstants {
	
	public static final class Common {
		//Default Font
		public static final String DEFAULT_FONT = "Verdana";
	}

	public static final class Fonts {
		//Fonts for LoginPanel
		public static final String VANI_FONT = "Vani"; 
		public static final String TAHOMA_FONT = "Tahoma";
	}
	
	
	public static final class Labels {
		//Labels for LoginPanel
		public static final String STR_USER_LABEL = "Username:";
		public static final String STR_PASSWORD_LABEL = "Password: ";
		public static final String STR_LOGIN = "Login";
		
		//Labels for Discount
		public static final String STR_ADD_DISCOUNT = "Add Discount";
		public static final String STR_EDIT_DISCOUNT = "Edit Discount";
		public static final String STR_DELETE_DISCOUNT = "Delete Discount";

		//Labels for Sales
		public static final String STR_ADD_SALESPRODUCT = "Add Product";
		public static final String STR_CHECKOUT_SALES = "Check Out";
		public static final String STR_CANCEL_SALES = "Cancel";
		
		//Labels for Member
		public static final String STR_ADD_MEMBER = "Add Member";
		public static final String STR_EDIT_MEMBER = "Edit Member";
		public static final String STR_DELETE_MEMBER = "Delete Member";
		
		// Base Dialog
		public static final String STR_CONFIRM = "Confirm";
		public static final String STR_CANCEL = "Cancel";
	}
	
	public static final class ErrorMessages {
		
		//Warning
		public final static String STR_WARNING = "Warning";
		// Success
		public final static String STR_SUCCESS = "Success";
	}
	
	public class TableHeaders {
		//Discount Table Headers
		public static final String DISCOUNT_CODE = "Code";
		public static final String DISCOUNT_PERCENTAGE = "Percentage";
		public static final String DISCOUNT_TYPE = "Type";
		public static final String DISCOUNT_DESCRIPTION = "Description";
		public static final String DISCOUNT_STARTDATE = "StartDate";
		public static final String DISCOUNT_PERIOD = "Period";
		
		//Member Table Headers
		public static final String MEMBER_NAME = "Name";
		public static final String MEMBER_ID = "Member ID";
		public static final String MEMBER_POINTS = "Loyalty Points";
	}
	
	public static final class PaneHeaders {
		
	}
	
	//Dashboard options
	public static final class MenuOptions{
		public static final String STR_SALES = "Sales";
		public static final String STR_INVENTORY = "Inventory";
		public static final String STR_MEMBER = "Member";
		public static final String STR_DISCOUNT = "Discount";
		public static final String STR_REPORTS = "Reports";
		public static final String STR_LOGOUT = "Logout";
	}
	
	public static final class MainFrame {
		public static final class MenuBar {
			public static final String FILE = "File";
			public static final String EDIT = "Edit";
			public static final String SOURCE = "Source";
			public static final String EXIT = "Exit";
			public static final String TRUE = "true";
		}
		public final static String APP_NAME = "NUS STORE MANAGER";
		// Window width in pixels
		public final static int WINDOW_WIDTH = 800;
		// Window height in pixels
		public final static int WINDOW_HEIGHT = 600;
		//Menu Bar for Apple
		public final static String APPLE_MENUBAR = "apple.laf.useScreenMenuBar";
		//Empty String
		public final static String STR_EMPTY = "";
	}
}
