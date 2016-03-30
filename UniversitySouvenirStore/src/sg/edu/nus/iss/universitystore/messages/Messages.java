package sg.edu.nus.iss.universitystore.messages;

import sg.edu.nus.iss.universitystore.constants.Constants;

public final class Messages {
	public static final class Error {
		public static final class Common {
			public static final String UNKNOWN_ERROR = "Something went wrong! Please try again.";
			public static final String INVALID_NUMBER = "Please Enter a Valid Positive Number. For example, \"123\"";
			public static final String INVALID_FLOAT = "Please Enter a Valid Positive Number with a maximum of two decimals. For example, \"50.0\" or \"30.25\"";
			public static final String INVALID_DATE = "Please Enter a Valid Date. Eg. 2000-10-20";
			public static final String INVALID_CODE = "Code must consist of only alphabets and in Capitals";
			public static final String INVALID_NAME = "Name must can contain alphabets, numbers and Spaces only";
			public static final String EMPTY_FIELDS = "All fields are mandatory.";
		}

		public static final class Category {
			public static final String INVALID_CODE = new StringBuffer().append(Common.INVALID_CODE)
					.append(Constants.Common.NEW_LINE).append("Code must be 3 characters long only").toString();
			public static final String INVALID_NAME = new StringBuffer().append("Category ").append(Common.INVALID_NAME)
					.toString();
			public static final String CATEGORY_ALREADY_PRESENT = "The Category Code already exists!";
			public static final String CATEGORY_NOT_AVAILABLE = "The Category Code doesn't exist";
			public static final String CATEGORY_UNKNOWN_ERROR = "Category Code not added correctly. "
					+ Common.UNKNOWN_ERROR;
			public static final String INVALID_CATEGORY_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(Category.INVALID_CODE).append(Constants.Common.NEW_LINE)
					.append(INVALID_NAME).toString();
		}

		public static final class Product {
			public static final String INVALID_NAME = new StringBuffer().append("Product ").append(Common.INVALID_NAME)
					.toString();
			public static final String INVALID_NUMBER = new StringBuffer()
					.append("For Quantity, Reorder Threshold and Reorder Quantity").append(Constants.Common.NEW_LINE)
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_FLOAT = new StringBuffer().append("For Price ")
					.append(Common.INVALID_FLOAT).toString();
			public static final String INVALID_PRODUCT_FIELDS = new StringBuffer().append(Common.EMPTY_FIELDS)
					.append(Constants.Common.NEW_LINE).append(INVALID_NAME).append(Constants.Common.NEW_LINE)
					.append(INVALID_NUMBER).append(Constants.Common.NEW_LINE).append(INVALID_FLOAT).toString();
			public static final String PRODUCT_ZERO = "No products have been added to the store.";
			public static final String PRODUCT_NOT_AVAILABLE = "The Product Identifier doesn't exist";
		}

		public static final class Login {
			public static final String EMPTY_USERNAME = "You have not entered a Username!";
			public static final String EMPTY_PASSWORD = "You have not entered a Password!";
			public static final String EMPTY_USERNAME_AND_PASSWORD = "You have not entered a Username and Password!";
			public static final String INVALID_CREDENTIALS = "Invalid Username and//or password. Please check your details.";
		}

		public static final class Member {
			public static final String EMPTY_MEMBER_NAME = "You have not entered a Member Name!";
			public static final String EMPTY_MEMBER_ID = "You have not entered a Member ID!";
			public static final String EMPTY_NAME_AND_ID = "You have not entered a Member Name and Member ID!";
			public static final String INVALID_MEMBER_NAME = "Please enter a valid member name!";
			public static final String MEMBER_IDENTIFIER_ALREADY_PRESENT = "The Member ID you entered already exists!";
			public static final String MEMBER_NOT_PRESENT_IN_FILE = "The Member does not exist!";
		}

		public static final class Discount {
			public static final String DISCOUNT_NOT_PRESENT_IN_FILE = "The Discount does not exist!";
			public static final String DISCOUNT_ALREADY_PRESENT = "The Discount Code already exists!";
			public static final String INVALID_PERIOD = new StringBuffer().append("Period: ")
					.append(Common.INVALID_NUMBER).toString();
			public static final String INVALID_START_DATE = new StringBuffer().append("Start Date: ")
					.append(Common.INVALID_DATE).toString();
			public static final String INVALID_PERCENTAGE = new StringBuffer().append("Percentage: ")
					.append(Common.INVALID_FLOAT).toString();
			public static final String INVALID_CODE = "Code should only contain alphabet characters!";
			public static final String INVALID_ELIGIBILITY = "Eligibility should be selected as \"M\" or \"A\"";
			public static final String INVALID_PERIOD_RANGE = "Period should be between 0 and 365";
			public static final String INVALID_PERCENTAGE_RANGE = "Percentage should be bewteen 1 and 100";
		}
		
		public static final class Transaction {
			public static final String INVALID_DISCOUNT_ID = "Invalid discount Id.";
			public static final String INVALID_MEMBER_ID = "Invalid member Id.";
			public static final String REQUESTED_QUANTITY_MORE_THAN_AVAILABLE = "Requested quantity of one or more items more than that available in the store";
			public static final String UNABLE_TO_UPDATE_LOYALTY_POINTS = "Something went wrong while updating loyalty points.";
		}
	}
}
