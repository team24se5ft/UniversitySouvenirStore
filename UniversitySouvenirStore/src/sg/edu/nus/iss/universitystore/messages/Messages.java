package sg.edu.nus.iss.universitystore.messages;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.Constants.Data;

public final class Messages {
	public static final class Error {
		public static final class Common {
			public static final String UNKNOWN_ERROR = "Something went wrong! Please try again.";
			public static final String INVALID_NUMBER = "Please Enter a Valid Positive Number. For example, \"123\"";
			public static final String INVALID_FLOAT = "Please Enter a Valid Positive Number with a maximum of two decimals. For example, \"50.0\" or \"30.25\"";
			public static final String INVALID_DATE = "Please Enter a Valid Date. Eg. 2000-10-20";
			public static final String INVALID_CODE = "Code must consist of capital alphabets only!";
			public static final String INVALID_NAME = "Name can only contain alphabets, numbers and spaces";
			public static final String EMPTY_FIELDS = "All fields are mandatory.";
			public static final String OTHER_ERROR = "Error in functionality.";
		}

		public static final class Category {
			public static final String INVALID_CODE = new StringBuffer().append("Code must be 3 characters long only")
					.toString();
			public static final String INVALID_NAME = new StringBuffer().append("Category ").append(Common.INVALID_NAME)
					.toString();
			public static final String CATEGORY_ALREADY_PRESENT = "The Category Code already exists!";
			public static final String CATEGORY_NOT_AVAILABLE = "The Category Code doesn't exist";
			public static final String CATEGORY_UNKNOWN_ERROR = "Category Code not added correctly. "
					+ Common.UNKNOWN_ERROR;
			public static final String INVALID_CATEGORY_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(Category.INVALID_CODE).append(Constants.Common.NEW_LINE)
					.append(INVALID_NAME).toString();
			public static final String CATEGORY_COUNT_LESS_THAN_EXPECTED = "The total number of categories cannot be less than 5.";
		}

		public static final class Product {
			public static final String INVALID_NAME = new StringBuffer().append("Product ").append(Common.INVALID_NAME)
					.toString();
			public static final String INVALID_NUMBER = new StringBuffer()
					.append("For Quantity, Reorder Threshold and Reorder Quantity").append(Constants.Common.NEW_LINE)
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_QUANTITY = new StringBuffer().append("Quantity: ")
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_REORDER_QUANTITY = new StringBuffer().append("Reorder Quantity: ")
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_REORDER_THRESHOLD = new StringBuffer().append("Reorder Threshold: ")
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_PRICE = new StringBuffer().append("Price: ").append(Common.INVALID_FLOAT)
					.toString();
			public static final String INVALID_BARCODE = new StringBuffer().append("Bar Code: ")
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_PRODUCT_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(INVALID_NAME).append(Constants.Common.NEW_LINE)
					.append(INVALID_NUMBER).append(Constants.Common.NEW_LINE).append(INVALID_PRICE)
					.append(Constants.Common.NEW_LINE).append(INVALID_BARCODE).append(Constants.Common.NEW_LINE)
					.append(INVALID_REORDER_THRESHOLD).append(Constants.Common.NEW_LINE)
					.append(INVALID_REORDER_QUANTITY).toString();
			public static final String PRODUCT_ZERO = "No products have been added to the store.";
			public static final String PRODUCT_NOT_AVAILABLE = "The Product Identifier doesn't exist";
			public static final String PRODUCT_BAR_CODE_EXISTS = "The Product Bar Code already exists";
			public static final String PRODUCT_COUNT_LESS_THAN_EXPECTED = "The total number of product cannot be less than 10.";
		}

		public static final class Login {
			public static final String EMPTY_USERNAME = "You have not entered a Username!";
			public static final String EMPTY_PASSWORD = "You have not entered a Password!";
			public static final String EMPTY_USERNAME_PASSWORD = "You have not entered a Username and Password!";
			public static final String INVALID_CREDENTIALS = "Invalid Username and//or password. Please check your details.";
		}

		public static final class Member {
			public static final String INVALID_MEMBER_ID = "Member ID should only contain alphabets and numbers";
			public static final String INVALID_MEMBER_NAME = new StringBuffer().append("Member Name: ")
					.append(Common.INVALID_NAME).toString();
			public static final String INVALID_LOYALTY_POINTS = "Loyalty Points has to be -1(new member) or positive value";
			public static final String MEMBER_IDENTIFIER_ALREADY_PRESENT = "The Member ID you entered already exists!";
			public static final String MEMBER_NOT_PRESENT_IN_FILE = "The Member does not exist!";
			public static final String EMPTY_MEMBER_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(INVALID_MEMBER_ID).append(Constants.Common.NEW_LINE)
					.append(INVALID_MEMBER_NAME).append(Constants.Common.NEW_LINE).append(INVALID_LOYALTY_POINTS)
					.toString();
			public static final String ADD_EMPTY_MEMBER_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(INVALID_MEMBER_ID).append(Constants.Common.NEW_LINE)
					.append(INVALID_MEMBER_NAME).toString();
			public static final String MEMBER_COUNT_LESS_THAN_EXPECTED = "The total number of members cannot be less than 10.";
		}

		public static final class Discount {
			public static final String DISCOUNT_NOT_PRESENT_IN_FILE = "The Discount does not exist!";
			public static final String DISCOUNT_ALREADY_PRESENT = "The Discount Code already exists!";
			public static final String INVALID_PERIOD = new StringBuffer().append("Period: ")
					.append(Common.INVALID_NUMBER).append(" or ALWAYS").toString();
			public static final String INVALID_START_DATE = new StringBuffer().append("Start Date: ")
					.append(Common.INVALID_DATE).append(" or ALWAYS").toString();
			public static final String INVALID_PERCENTAGE = new StringBuffer().append("Percentage: ")
					.append(Common.INVALID_FLOAT).toString();
			public static final String INVALID_CODE = new StringBuffer().append(Common.INVALID_CODE).toString();
			public static final String INVALID_ELIGIBILITY = "Eligibility should be selected as \"Member\" or \"Public\"";
			public static final String INVALID_PERCENTAGE_RANGE = "Percentage should be bewteen 1 and 100";
			public static final String NEWMEMBER_PERCENTAGE_ONLY = "Only Percentage can be updated for New Member Discount";
			public static final String EXTNGMEMBER_PERCENTAGE_ONLY = "Only Percentage can be updated for Existing Member Discount";
			public static final String DEFAULT_DISCOUNT_NOT_UPDATABLE = "Default Customer Discount cannot be modified";
			public static final String DEFAULT_DISCOUNT_NOT_DELETABLE = "Default Customer Discount cannot be deleted";
			public static final String START_DATE_PERIOD_ALWAYS = "When Start Date is 'ALWAYS' period must be 'ALWAYS'";
			public static final String EMPTY_DISCOUNT_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(INVALID_CODE).append(Constants.Common.NEW_LINE)
					.append(INVALID_PERCENTAGE).append(Constants.Common.NEW_LINE).append(INVALID_ELIGIBILITY)
					.append(Constants.Common.NEW_LINE).append(INVALID_START_DATE).append(Constants.Common.NEW_LINE)
					.append(INVALID_PERIOD).toString();
			public static final String DISCOUNT_COUNT_LESS_THAN_EXPECTED = "The total number of discounts cannot be less than 5.";
		}

		public static final class Transaction {
			public static final String INVALID_DISCOUNT_ID = "Invalid discount Id.";
			public static final String INVALID_MEMBER_ID = "Invalid member Id.";
			public static final String REQUESTED_QUANTITY_MORE_THAN_AVAILABLE = "Requested quantity of one or more items more than that available in the store";
			public static final String UNABLE_TO_UPDATE_LOYALTY_POINTS = "Something went wrong while updating loyalty points.";
			public static final String INVALID_START_DATE = new StringBuffer().append("Start Date: ")
					.append(Common.INVALID_DATE).toString();
			public static final String INVALID_END_DATE = new StringBuffer().append("End Date: ")
					.append(Common.INVALID_DATE).toString();
			public static final String END_DATE_BEFORE_START_DATE = "End Date must be after Start Date";
		}

		public static final class Controller {
			public static final String NO_PRODUCTS_PRS = "No products present in the store.";
			public static final String CAT_BEFORE_PROD = "Please add a category before adding products.";
		}
	}
}
