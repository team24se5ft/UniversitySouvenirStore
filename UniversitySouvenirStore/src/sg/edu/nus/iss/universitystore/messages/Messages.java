package sg.edu.nus.iss.universitystore.messages;

public final class Messages {
	public static final class Error {
		public static final class Common {
			public static final String UNKNOWN_ERROR = "Something went wrong! Please try again.";
		}
		
		public static final class Category {
			public static final String INVALID_CODE_LENGTH = "Category Code must be 3 characters long!";
			public static final String INVALID_CHARACTERS = "Category Code must only contain alphabet characters!";
		}
		public static final class Product {
			public static final String PRODUCT_ZERO = "No products have been added to the store.";
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
			public static final String EMPTY_DISCOUNT_FIELDS = "All fields are mandatory.";
			public static final String DISCOUNT_NOT_PRESENT_IN_FILE = "The Discount does not exist!";
			public static final String DISCOUNT_ALREADY_PRESENT = "The Discount Code already exists!";
		}
	}
}
