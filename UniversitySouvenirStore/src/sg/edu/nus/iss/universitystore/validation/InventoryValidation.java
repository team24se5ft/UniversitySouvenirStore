package sg.edu.nus.iss.universitystore.validation;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.InventoryException.InventoryError;

/**
 * Validations for Inventory
 * 
 * @author Sanjay
 *
 */
public class InventoryValidation extends Validation {

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
			if (code.isEmpty() || !isCode(code) || !isName(name)) {
				throw new InventoryException(InventoryError.INVALID_CATEGORY_FIELDS);
			}

			return isValidCatgoryCode(code);
		}
	}

}
