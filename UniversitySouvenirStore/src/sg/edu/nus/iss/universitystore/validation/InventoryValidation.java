package sg.edu.nus.iss.universitystore.validation;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.InventoryException.InventoryError;
import sg.edu.nus.iss.universitystore.model.Category;

/**
 * Validations for Inventory
 * 
 * @author Sanjay
 *
 */
public class InventoryValidation extends Validation {

	/**
	 * Category Validations
	 */
	public static class Catgory {

		/**
		 * Checks format of Category Code
		 * 
		 * @param code
		 * @return
		 * @throws InventoryException
		 */
		public static boolean isValidCatgoryCode(String code) throws InventoryException {

			// Checks if Category fields are empty
			if (code.isEmpty() || !isCode(code)) {
				throw new InventoryException(InventoryError.INVALID_CODE);
			}

			// Checks if Category code is of 3 characters length
			if (!code.matches(Constants.Data.Category.Pattern.CODE_MATCH)) {
				throw new InventoryException(InventoryError.INVALID_CODE_LENTH);
			}

			return true;
		}

		/**
		 * Checks if category data is of correct format
		 * 
		 * @param code
		 * @param name
		 * @return
		 * @throws InventoryException
		 */
		public static boolean isValidData(String code, String name) throws InventoryException {

			// Checks if Category fields are empty
			if (code.isEmpty() || !isName(name)) {
				throw new InventoryException(InventoryError.INVALID_CATEGORY_FIELDS);
			}

			return isValidCatgoryCode(code);
		}
	}

	/**
	 * Product Validations
	 */
	public static class Product {

		/**
		 * Validate all Product Data other than product identifier
		 * 
		 * @param name
		 * @param description
		 * @param quantity
		 * @param price
		 * @param reorderThreshold
		 * @param reorderQuantity
		 * @return Boolean
		 * @throws InventoryException
		 */
		public static boolean isValidData(String name, String description, String quantity, String price,
				String reorderThreshold, String reorderQuantity) throws InventoryException {

			if (name.isEmpty() || description.isEmpty() || quantity.isEmpty() || price.isEmpty()
					|| reorderThreshold.isEmpty() || reorderQuantity.isEmpty()) {
				throw new InventoryException(InventoryError.INVALID_PRODUCT_FIELDS);
			}

			if (!isName(name)) {
				throw new InventoryException(InventoryError.INVALID_NAME);
			}

			if (!isNumber(quantity)) {
				throw new InventoryException(InventoryError.INVALID_QUANTITY);
			}

			if (!isFloat(price)) {
				throw new InventoryException(InventoryError.INVALID_PRICE);
			}

			if (!isNumber(reorderThreshold)) {
				throw new InventoryException(InventoryError.INVALID_REORDER_QUANTITY);
			}

			if (!isNumber(reorderQuantity)) {
				throw new InventoryException(InventoryError.INVALID_REORDER_THRESHOLD);
			}

			return true;
		}

		/**
		 * Validate all Product Data and Category
		 * 
		 * @param category
		 * @param name
		 * @param description
		 * @param quantity
		 * @param price
		 * @param reorderThreshold
		 * @param reorderQuantity
		 * @return
		 * @throws InventoryException
		 */
		public static boolean isValidData(String categoryCode, String name, String description, String quantity,
				String price, String reorderThreshold, String reorderQuantity) throws InventoryException {
			return InventoryValidation.Catgory.isValidCatgoryCode(categoryCode)
					&& isValidData(name, description, quantity, price, reorderThreshold, reorderQuantity);
		}

	}

}
