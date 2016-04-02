package sg.edu.nus.iss.universitystore.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Data Constants
 * 
 * @author Sanjay
 *
 */
public final class Constants {

	public static final class DateTime {
		/**
		 * System Date
		 */
		public static final LocalDate CURRENT_DATE = LocalDate.now();
	}

	public static final class Common {
		/**
		 * Empty String
		 */
		public static final String EMPTY_STR = "";
		/**
		 * New Line Character
		 */
		public static final String NEW_LINE = "\n";

		public static final class Pattern {
			/**
			 * Positive Number Regex
			 */
			public static final String NUMBER_MATCH = "\\d+";
			/**
			 * Positive Float Regex
			 */
			public static final String FLOAT_MATCH = "\\d+(\\.\\d+)?";
			/**
			 * yyyy-MM-dd Regex
			 */
			public static final String DATE_MATCH = "\\d{4}\\-\\d{2}\\-\\d{2}";
			/**
			 * Code Regex
			 */
			public static final String CODE_MATCH = "[A-Z_]+";
			/**
			 * Name Regex
			 */
			public static final String NAME_MATCH = "[a-zA-Z0-9 ]+";
		}

		/**
		 * Date Time Formatter yyyy-MM-dd
		 */
		public static final DateTimeFormatter YYYY_MM_DD_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

	public static final class Data {
		/**
		 * Folder Locations for Junits and Normal Data operations
		 */
		public enum FILE_FOLDER {
			TEST, DATA;
		}
		/**
		 * Directory Separators for WIN '\' and MAC '/'
		 */
		public static final String FILE_PATH_SEPTR = System.getProperty("os.name").toUpperCase().startsWith("WIN")
				? "\\" : "//";
		/**
		 * Data File Extension
		 */
		public static final String FILE_EXT = ".dat";
		/**
		 * Date File Line Separator
		 */
		public static final String FILE_SEPTR = ",";
		/**
		 * Data File Path for normal data operations
		 */
		public static final String FILE_PATH = System.getProperty("user.dir") + FILE_PATH_SEPTR
				+ FILE_FOLDER.DATA.toString().toLowerCase() + FILE_PATH_SEPTR;
		/**
		 * Product Id Spearator
		 */
		public static final String ID_SEPTR = "/";
		/**
		 * Data File Path for running JUnits
		 */
		public static final String TEST_FILE_PATH = System.getProperty("user.dir") + FILE_PATH_SEPTR
				+ FILE_FOLDER.TEST.toString().toLowerCase() + FILE_PATH_SEPTR
				+ FILE_FOLDER.DATA.toString().toLowerCase() + FILE_PATH_SEPTR;

		public static final class FileName {
			/**
			 * File Name for Category
			 */
			public static final String CATEGORY_DAT = "Category";
			/**
			 * File Name for Discount
			 */
			public static final String DISCOUNT_DAT = "Discount";
			/**
			 * File Name for Member
			 */
			public static final String MEMBER_DAT = "Member";
			/**
			 * File Name for Product
			 */
			public static final String PRODUCT_DAT = "Product";
			/**
			 * File Name for Store Keeper
			 */
			public static final String STORE_KEEPER_DAT = "StoreKeeper";
			/**
			 * File Name for Transaction
			 */
			public static final String TRANSACTION_DAT = "Transaction";
			/**
			 * File Name for Vendor
			 */
			public static final String VENDOR_DAT = "Vendor";

		}

		public static final class Pattern {
			/**
			 * Regex for Data File Line Separator
			 */
			public static final String FILE_SEPTR_MATCH = "\\" + FILE_SEPTR;
			/**
			 * Description Regex
			 */
			public static final String DESCRIPTION_MATCH = "(\\\"(?!\\\")(.*?)\\\"" + FILE_SEPTR_MATCH + ")";
		}

		public static final class Category {
			/**
			 * Minimum Count for Delete Row
			 */
			public static final int CATEGORY_MINIMUM_COUNT = 5;

			public static final class Pattern {
				/**
				 * Category Code Regex
				 */
				public static final String CODE_MATCH = "[A-Z]{3}";
				/**
				 * Category Name Regex
				 */
				public static final String NAME_MATCH = Common.Pattern.NAME_MATCH;
				/**
				 * Category Data Line Regex 
				 */
				public static final String LINE_MATCH = "^" + CODE_MATCH + Data.Pattern.FILE_SEPTR_MATCH + NAME_MATCH
						+ "$";
			}
		}

		public static final class Product {
			/**
			 * Minimum Count for Delete Row
			 */
			public static final int PRODUCT_MINIMUM_COUNT = 10;

			public static final class Pattern {
				/**
				 * Product Code Regex
				 */
				public static final String ID_MATCH = "(\\w+)" + ID_SEPTR + "(\\d+)";
				/**
				 * Product Category Code Regex
				 */
				public static final String CATEGORY_REPLACE = "$1";
				/**
				 * Product Count Regex
				 */
				public static final String COUNT_REPLACE = "$2";
				/**
				 * Product Code Regex
				 */
				public static final String NAME_MATCH = Common.Pattern.NAME_MATCH;
				public static final String QUANTITY_MATCH = Common.Pattern.NUMBER_MATCH;
				public static final String PRICE_MATCH = Common.Pattern.FLOAT_MATCH;
				public static final String BARCODE_MATCH = Common.Pattern.NUMBER_MATCH;
				public static final String REORDER_THRESHOLD_MATCH = Common.Pattern.NUMBER_MATCH;
				public static final String REORDER_QUANTITY_MATCH = Common.Pattern.NUMBER_MATCH;
				public static final String LINE_MATCH = "^(" + ID_MATCH + Data.Pattern.FILE_SEPTR_MATCH + NAME_MATCH
						+ Data.Pattern.FILE_SEPTR_MATCH + ")" + Data.Pattern.DESCRIPTION_MATCH + "(" + QUANTITY_MATCH
						+ Data.Pattern.FILE_SEPTR_MATCH + PRICE_MATCH + Data.Pattern.FILE_SEPTR_MATCH + BARCODE_MATCH
						+ Data.Pattern.FILE_SEPTR_MATCH + REORDER_THRESHOLD_MATCH + Data.Pattern.FILE_SEPTR_MATCH
						+ REORDER_QUANTITY_MATCH + ")$";
				public static final String DESCRIPTION_REPLACE = "$5";
				public static final String OTHER_CNTNT_REPLACE = "$1$6";
			}

			public static final int PRODUCT_ZERO = 0;
			public static final int DATA_SPLT_LENGTH = 8;
			public static final int INITIALIZED_COUNT = 0;
		}

		public static final class Member {
			public static final int LOYALTY_NEW_MEMBER = -1;
			public static final int MEMBER_MINIMUM_COUNT = 10;

			public static final class Pattern {
				public static final String MEMBER_ID_MATCH = "^[a-zA-Z0-9]+$";
				public static final String LOYALTY_POINTS_MATCH = "^((-1)|\\d+)$";
			}
		}

		public static final class Discount {
			public static final String ALWAYS = "ALWAYS";
			public static final int ALWAYS_VAL = -1;
			public static final int DISCOUNT_MINIMUM_COUNT = 5;

			public static final class Pattern {
				public static final String CODE_MATCH = Common.Pattern.CODE_MATCH;
				public static final String START_DATE_MATCH = "((" + ALWAYS + ")|\\d{4}-\\d{2}-\\d{2})";
				public static final String PERIOD_MATCH = "((" + ALWAYS + ")|" + Common.Pattern.NUMBER_MATCH + ")";
				public static final String PERCENTAGE_MATCH = "\\d{1,3}(\\.\\d{1,2})?";
				public static final String ELIGIBILITY_MATCH = "[A|M]";
				public static final String LINE_MATCH = "^(" + CODE_MATCH + Data.Pattern.FILE_SEPTR_MATCH + ")"
						+ Data.Pattern.DESCRIPTION_MATCH + "(" + START_DATE_MATCH + Data.Pattern.FILE_SEPTR_MATCH
						+ PERIOD_MATCH + Data.Pattern.FILE_SEPTR_MATCH + PERCENTAGE_MATCH
						+ Data.Pattern.FILE_SEPTR_MATCH + ELIGIBILITY_MATCH + ")$";
				public static final String DESCRIPTION_REPLACE = "$3";
				public static final String OTHER_CNTNT_REPLACE = "$1$4";
			}

			public static final class Eligibility {
				public static final String MEMBER = "M";
				public static final String ALL = "A";
			}

			public static final class Member {
				public static final class New {
					public static final String CODE = "MEMBER_FIRST";
				}

				public static final class Existing {
					public static final String CODE = "MEMBER_SUBSEQ";
				}

				public static final class Public {
					public static final String CODE = "PUBLIC";
					public static final String DESCRIPTION = "No Discount applicable for Non-Member";
					public static final int PERIOD = 0;
					public static final float DEFAULT_DISCOUNT = 0;
				}
			}
		}

		public static final class Transaction {
			public static final int CURRENCY_TO_LOYALTY_POINTS_CONVERSION_RATE = 10;
			public static final int LOYALTY_POINTS_TO_CURRENCY_CONVERSION_RATE = 1;
			public static final String STR_PRODUCTS_BELOW_THRESHOLD = "The following products are below threshold quantity:";
		}
	}

	public static final class TableData {
		// Common
		public static final int FIRST_COLUMN = 0;
		public static final int SECOND_COLUMN = 1;
		public static final int THIRD_COLUMN = 2;
		public static final int FOURTH_COLUMN = 3;
		public static final int FIFTH_COLUMN = 4;
		public static final int SIXTH_COLUMN = 5;
		public static final int SEVENTH_COLUMN = 6;
		public static final int EIGHTH_COLUMN = 7;

		// Category
		public static final int NUMBER_OF_CATEGORY_TABLE_COLUMNS = 2;
		public static final String STR_CATEGORY_CODE = "Code";
		public static final String STR_CATEGORY_NAME = "Name";

		// Product
		public static final int NUMBER_OF_PRODUCT_TABLE_COLUMNS = 8;
		public static final String STR_PRODUCT_CODE = "Code";
		public static final String STR_PRODUCT_NAME = "Name";
		public static final String STR_PRODUCT_DESCRIPTION = "Description";
		public static final String STR_PRODUCT_QUANTITY = "Quantity";
		public static final String STR_PRODUCT_PRICE = "Price";
		public static final String STR_PRODUCT_BARCODE = "BarCode";
		public static final String STR_PRODUCT_REORDER_THRESHOLD = "Reorder Threshold";
		public static final String STR_PRODUCT_REORDER_QUANTITY = "Reorder Quantity";

		// Discount
		public static final int NUMBER_OF_DISCOUNT_TABLE_COLUMNS = 6;
		public static final String STR_DISCOUNT_CODE = "Code";
		public static final String STR_DISCOUNT_DESCRIPTION = "Description";
		public static final String STR_DISCOUNT_START_DATE = "Start Date";
		public static final String STR_DISCOUNT_PERIOD = "Period";
		public static final String STR_DISCOUNT_PERCENTAGE = "Rate (%)";
		public static final String STR_DISCOUNT_ELIGIBILITY = "Eligibility";

		// Member
		public static final int NUMBER_OF_MEMBER_TABLE_COLUMNS = 3;
		public static final String STR_MEMBER_IDENTIFIER = "Identifier";
		public static final String STR_MEMBER_NAME = "Name";
		public static final String STR_MEMBER_LOYALTY_POINTS = "Loyalty Points";
		// TransactionITEM
		public static final int NUMBER_OF_TRANSACTION_ITEM_TABLE_COLUMNS = 6;
		public static final String STR_TRANSACTIONITEM_PRODUCT_IDENTIFIER = "Product Id";
		public static final String STR_TRANSACTIONITEM_PRODUCT_NAME = "Product Name";
		public static final String STR_TRANSACTIONITEM_PURCHASE_QUANTITY = "Purchased Quantity";
		public static final String STR_TRANSACTIONITEM_PRICE = "Price";
		public static final String STR_TRANSACTIONITEM_TOTAL = "Total";
		// Transaction
		public static final int NUMBER_OF_TRANSACTION_TABLE_COLUMNS = 4;
		public static final String STR_TRANSACTION_PRODUCT_IDENTIFIER = "Product Id";
		public static final String STR_TRANSACTION_PRODUCT_NAME = "Product Name";
		public static final String STR_TRANSACTION_PRODUCT_DESCRIPTION = "Product Description";
		public static final String STR_TRANSACTION_MEMBER_IDENTIFIER = "Member Id";
		public static final String STR_TRANSACTION_PURCHASE_QUANTITY = "Purchased Quantity";
		public static final String STR_TRANSACTION_DATE = "Date";
	}
}
