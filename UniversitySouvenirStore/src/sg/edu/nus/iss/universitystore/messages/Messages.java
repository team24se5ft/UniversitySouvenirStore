package sg.edu.nus.iss.universitystore.messages;

public final class Messages {
	public static final class Error {
		public static final class Common {
			public static final String UNKNOWN_ERROR = "Something went wrong with the system.";
		}
		
		public static final class Category {
			public static final String INVALID_CODE_LENGTH = "Category code length must be only 3";
			public static final String INVALID_CHARACTERS = "Category code must contain only characters";
		}
		public static final class Product {
			public static final String PRODUCT_ZERO = "No products have been added to the store";
		}
		
		public static final class Login {
			public static final String EMPTY_USERNAME = "The user name cannot be empty.";
			public static final String EMPTY_PASSWORD = "The password cannot be empty.";
			public static final String EMPTY_USERNAME_AND_PASSWORD = "The user name and password cannot be empty.";
			public static final String INVALID_CREDENTIALS = "The user name or password is incorrect.";
		}
		
		public static final class Member {
			public static final String EMPTY_MEMBER_NAME = "The member name cannot be empty.";
			public static final String EMPTY_MEMBER_ID = "The member identifier cannot be empty.";
			public static final String EMPTY_USERNAME_AND_PASSWORD = "The member name and member identifier cannot be empty.";
			public static final String INVALID_MEMBER_NAME = "The member name can have only alphabets.";
			public static final String MEMBER_IDENTIFIER_ALREADY_PRESENT = "The member identifier already exists.";
			public static final String MEMBER_NOT_PRESENT_IN_FILE = "The member does not exist.";
		}
	}
}
