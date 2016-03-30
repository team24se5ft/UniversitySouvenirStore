package sg.edu.nus.iss.universitystore.constants;

/**
 * @author Samrat
 *
 */
public class ViewConstants {

	public static final class Common {
		// Default Font
		public static final String DEFAULT_FONT = "Verdana";
	}

	public static final class Fonts {
		// Fonts for LoginPanel
		public static final String VANI_FONT = "Vani";
		public static final String TAHOMA_FONT = "Tahoma";
	}

	public static final class Labels {
		// Labels for LoginPanel
		public static final String STR_USER_LABEL = "Username:";
		public static final String STR_PASSWORD_LABEL = "Password: ";
		public static final String STR_LOGIN = "Login";
		
		// Labels for Inventory
		public static final String STR_ADD_CATEGORY = "Add Category";
		public static final String STR_EDIT_CATEGORY = "Edit Category";
		public static final String STR_DELETE_CATEGORY = "Delete Category";

		// Labels for Discount
		public static final String STR_ADD_DISCOUNT = "Add Discount";
		public static final String STR_EDIT_DISCOUNT = "Edit Discount";
		public static final String STR_DELETE_DISCOUNT = "Delete Discount";
		public static final String STR_MEMBER = "Member";
		public static final String STR_PUBLIC = "Public";

		// Labels for Sales
		public static final String STR_ADD_SALESPRODUCT = "Add Product";
		public static final String STR_CHECKOUT_SALES = "Check Out";
		public static final String STR_CANCEL_SALES = "Cancel";

		// Labels for Member
		public static final String STR_ADD_MEMBER = "Add Member";
		public static final String STR_EDIT_MEMBER = "Edit Member";
		public static final String STR_DELETE_MEMBER = "Delete Member";

		// Base Dialog
		public static final String STR_CONFIRM = "Confirm";
		public static final String STR_CANCEL = "Cancel";
	}

	public static final class StatusMessage {

		// Error
		public final static String ERROR = "Error";
		// Warning
		public final static String WARNING = "Warning";
		// Success
		public final static String SUCCESS = "Success";
	}
	
	public class PanelHeaders{
		//Login Panel Headers
		public static final String BACKGRND_IMG="Resources/main_background.jpg";
		public static final String LOGIN_ICON_IMG="Resources/login_icon.jpg";
		
		//Dashboard Panel Headers
		public static final String LOGOUT_CONFIRM_MSG="Are you sure you wish to logout?";
		
		//Dashboard Options Panel Headers
		public static final String SALE_ICON_IMG="Resources/sale_icon.png";
		public static final String INVENTORY_ICON_IMG="Resources/inventory_icon.png";
		public static final String MEMBER_ICON_IMG="Resources/member_icon.png";
		public static final String DISCOUNT_ICON_IMG="Resources/discount_icon.png";
		public static final String REPORT_ICON_IMG="Resources/report_icon.png";
		public static final String LOGOUT_ICON_IMG="Resources/logout_icon.png";
		public static final String ANNOUNCEMENT_DEMO="03/03: Linc Pen stock low.\n\n27/02: Member A001482497 successfully created.";
		
		//Base Table Panel Headers
		public static final String ADD_ICON_IMG="Resources/add_icon.png";
		public static final String EDIT_ICON_IMG="Resources/edit_icon.png";
		public static final String DELETE_ICON_IMG="Resources/delete_icon.png";
		
	}
	
	public class DialogHeaders{
		
		//Category Dialog Headers
		public static final String CATEGORY_CODE="Category Code:";
		public static final String CATEGORY_NAME="Category Name:";
		
		//Discount Dialog Headers
		public static final String DISC_CODE="Discount Code";
		public static final String OFF_PERC="Percent Off";
		public static final String TYPE="Type";
		public static final String DESCRIPTION="Description";
		public static final String START_DATE="Start Date (yyyy-MM-dd):";
		public static final String PERIOD="Period";
		
		//Member Dialog Headers
		public static final String MEMBER_ID="Member ID";
		public static final String MEMBER_NAME="Member Name";
		public static final String LOYALTY_POINTS="Loyalty Points";
		
		//Member Scan Dialog Headers
		public static final String MEMBER_CODE="Member Code";
		
		//Product Dialog Headers
		public static final String CATEGORY="Category :";
		public static final String PROD_NAME="Product Name :";
		public static final String PROD_DESC="Product Description :";
		public static final String PROD_QUANTITY="Product Quantity :";
		public static final String PROD_PRICE="Product Price :";
		public static final String BARCODE_NO="Bar Code Number :";
		public static final String REORDER_QUANTITY="Reorder Quantity (Threshold):";
		public static final String ORDER_QUANTITY="Order Quantity :";
		
		//Product Scan Dialog Headers
		public static final String PROD_CODE="Product Code :";
		
		//Receipt Dialog Headers
		public static final String STORE_HEADER="NUS SOUVENIR STORE";
		public static final String HEADER="Bill\t\tQuantity\t\tPrice";
		public static final String NORTH="North";
		public static final String CENTER="Center";
	}
	
	
	public class TableHeaders {
		// Discount Table Headers
		public static final String DISCOUNT_CODE = "Code";
		public static final String DISCOUNT_PERCENTAGE = "Percentage";
		public static final String DISCOUNT_TYPE = "Type";
		public static final String DISCOUNT_DESCRIPTION = "Description";
		public static final String DISCOUNT_STARTDATE = "StartDate";
		public static final String DISCOUNT_PERIOD = "Period";

		// Member Table Headers
		public static final String MEMBER_NAME = "Name";
		public static final String MEMBER_ID = "Member ID";
		public static final String MEMBER_POINTS = "Loyalty Points";
	}

	public static final class PaneHeaders {
		
		//Inventory Pane Headers
		public static final String INV_CATEGORY_PANE="Category";
		public static final String INV_CATEGORY_PANE_DESC="All functionalities related to the categories.";
		public static final String INV_PRODUCT_PANE="Product";
		public static final String INV_PRODUCT_PANE_DESC="All functionalities related to the products.";
		
		//Report Pane Headers
		public static final String REP_CATEGORY_PANE="Category";
		public static final String REP_CATEGORY_PANE_DESC="Get all categories.";
		public static final String REP_PRODUCT_PANE="Product";
		public static final String REP_PRODUCT_PANE_DESC="Get all products.";
		public static final String REP_MEMBER_PANE="Member";
		public static final String REP_MEMBER_PANE_DESC="Get all members.";
	}

	// Dashboard options
	public static final class MenuOptions {
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
		// Menu Bar for Apple
		public final static String APPLE_MENUBAR = "apple.laf.useScreenMenuBar";
		// Empty String
		public final static String STR_EMPTY = "";
	}

	public static final class SalesPanel {
		public static final String MEMBER_OPTION_LABEL = "PUBLIC";
		public static final String NONE_DISCOUNT = "none discount";
		public static final String MEMBER_LABEL = "member:";
		public static final String DISCOUNT_LABEL = "discount:";
		public static final String DISCOUNT_PERCENTAGE_LABEL = "off:";
		public static final String AVAILABLE_LOYALPOINT_LABEL = "AvailableLoyalPoint:";
		
		public static final String LOYALPOINT_LABEL = "loyalPoint:";
		public static final String TOTAL_LABEL = "Total:";
		public static final String CASH_LABEL = "Cash:";
		public static final String CHANGE_LABEL = "Change:";
		
		
	}

	public static final class ValidationMessage {
		// all uncatch exception use this failed
		public static final String VALIDATION_Failed = "some unexception problem occur!";
		public static final String MEMBER_NotExist = "the input member ID is not exist!";
		public static final String PRODUCT_ID_NotExist = "the input product ID is not exist!";
		
	}
	
	public static final class Controller {
		public static final String PLEASE_SELECT_ROW = "Please select a row of the table for completing this operation.";
		public static final String SUCCESS_MESSAGE = "Successfully added.";
		public static final class Discount {			
			public static final String DELETE_DISCOUNT = "Do u really want to delete discount: ";
		}
	}
}
