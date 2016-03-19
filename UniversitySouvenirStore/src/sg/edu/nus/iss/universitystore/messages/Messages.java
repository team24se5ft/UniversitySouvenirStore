package sg.edu.nus.iss.universitystore.messages;

public final class Messages {
	public static final class Error {
		public static final class Category {
			public static final String INVALID_CODE_LENGTH = "Category code length must be only 3";
			public static final String INVALID_CHARACTERS = "Category code must contain only characters";
		}
		public static final class Product {
			public static final String PRODUCT_ZERO = "No products have been added to the store";
		}
	}
}
