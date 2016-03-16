package sg.edu.nus.iss.universitystore.data;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.intf.UniversityStoreJUnit;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;
import sg.edu.nus.iss.universitystore.messages.Messages;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.utility.JUnitUtility;

public class InventoryManagerTest extends UniversityStoreJUnit {

	private Category category1, category2;
	private String categoryCode1, categoryCode2, categoryCode3, invalidCatgryCode1, invalidCatgryCode2,
			invalidCatgryCode3;
	private String categoryName1, categoryName2, categoryName3, invalidCatgryName1, invalidCatgryName2,
			invalidCatgryName3;

	@Override
	public void setUp() throws Exception {
		super.setUp();

		// Category Set 1
		categoryCode1 = "CLT";
		categoryName1 = "Cloths";
		category1 = new Category(categoryCode1, categoryName1);

		// Category Set 2
		categoryCode2 = "SHO";
		categoryName2 = "Shoes";
		category2 = new Category(categoryCode2, categoryName2);

		// Invalid Category
		invalidCatgryCode1 = "MCW";
		invalidCatgryName1 = "Microwave";
		invalidCatgryCode2 = "BOAZ";
		invalidCatgryName2 = "Boa Snakes";
		invalidCatgryCode3 = "BO1";
		invalidCatgryName3 = "Boa Snakes";
	}

	/**
	 * Addition of One New Category
	 */
	@Test
	public void testAddOneCategory() {
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			InventoryManager inventoryManager = new InventoryManager();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add new category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));

			// Find Category
			Category category = inventoryManager.findCategory(categoryCode1);

			// Check Post Conditions
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 2);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));
			Assert.assertEquals(category, category1);

		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Test the following functionalities: 
	 * 1. Addition of One New Category Twice
	 * 2. Addition of another Category 
	 * 3. Check for invalid Category 
	 * 4. Delete a category 
	 * 5. Delete an invalid category
	 */
	@Test
	public void testCategoryFunctionalities() {
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			InventoryManager inventoryManager = new InventoryManager();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add new category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			// Add the same category
			Assert.assertFalse(inventoryManager.addCategory(categoryCode1, categoryName1));
			// Add Second category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode2, categoryName2));

			// Find Category
			Category categoryOne = inventoryManager.findCategory(categoryCode1);
			Category categoryTwo = inventoryManager.findCategory(categoryCode2);
			Assert.assertEquals(inventoryManager.findCategory(invalidCatgryCode1), null);

			// Check First Level Post Conditions
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 3);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 3));
			Assert.assertEquals(categoryOne, category1);
			Assert.assertEquals(categoryTwo, category2);

			// Delete valid category
			Assert.assertTrue(inventoryManager.deleteCategory(categoryCode2));
			// Check First Level Post Conditions
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 2);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));

			// Delete invalid category
			Assert.assertFalse(inventoryManager.deleteCategory(categoryCode2));

		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Addition of Invalid Category due to Code length
	 */
	@Test
	public void testAddInvalidCategoryLength() {
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			InventoryManager inventoryManager = new InventoryManager();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add new category
			inventoryManager.addCategory(invalidCatgryCode2, invalidCatgryName2);
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
			Assert.assertEquals(exception.getMessage(), Messages.Error.Category.INVALID_CODE_LENGTH);
		}
	}

	/**
	 * Addition of Invalid Category due to Code length
	 */
	@Test
	public void testAddInvalidCategoryCharacters() {
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			InventoryManager inventoryManager = new InventoryManager();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add new category
			inventoryManager.addCategory(invalidCatgryCode3, invalidCatgryName3);
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
			Assert.assertEquals(exception.getMessage(), Messages.Error.Category.INVALID_CHARACTERS);
		}
	}

	/**
	 * Deletion of Invalid Category due to Code length
	 */
	@Test
	public void testDeleteInvalidCategoryLength() {
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			InventoryManager inventoryManager = new InventoryManager();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add new category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			// Deletion of invalid category
			inventoryManager.deleteCategory(invalidCatgryCode2);
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
			Assert.assertEquals(exception.getMessage(), Messages.Error.Category.INVALID_CODE_LENGTH);
		}
	}

}
