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
	}
}
