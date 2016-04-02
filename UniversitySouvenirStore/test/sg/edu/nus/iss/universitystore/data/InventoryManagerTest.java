package sg.edu.nus.iss.universitystore.data;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.InventoryException.InventoryError;
import sg.edu.nus.iss.universitystore.intf.UniversityStoreJUnit;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.utility.JUnitUtility;

public class InventoryManagerTest extends UniversityStoreJUnit {

	/**
	 * Categories
	 */
	private Category category1, category2;
	/**
	 * Category Codes
	 */
	private String categoryCode1, categoryCode2, invalidCatgryCode1, invalidCatgryCode2,
			invalidCatgryCode3;
	/**
	 * Category Names
	 */
	private String categoryName1, categoryName2, invalidCatgryName2,
			invalidCatgryName3;

	
	/**
	 * Product Name
	 */
	private String productName1, productName2, productName3;
	/**
	 * Product Description
	 */
	private String productDescription1, productDescription2, productDescription3;
	/**
	 * Product Quality
	 */
	private Integer productQuantity1, productQuantity2, productQuantity3;
	/**
	 * Product Price
	 */
	private Double productPrice1, productPrice2, productPrice3;
	/**
	 * Product Bar Code
	 */
	private Integer productBarCode1, productBarCode2, productBarCode3, invalidProductBarCode;
	/**
	 * Product Reorder Threshold
	 */
	private Integer productReorderThreshold1, productReorderThreshold2, productReorderThreshold3;
	/**
	 * Product Reorder Quantity
	 */
	private Integer productReorderQuantity1, productReorderQuantity2, productReorderQuantity3;
	/**
	 * Invalid Product Identifier
	 */
	private String invalidProdID1, invalidProdID2;

	/**
	 * Product Name
	 */
	private String prodName1;
	/**
	 * Product Description
	 */
	private String prodDescription1;
	/**
	 * Product Quality
	 */
	private Integer prodQuantity1;
	/**
	 * Product Price
	 */
	private Double prodPrice1;
	/**
	 * Product Barcode
	 */
	private String prodBarCode1;
	/**
	 * Product Reorder Threshold
	 */
	private Integer prodReorderThreshold1;
	/**
	 * Product Reorder Quantity
	 */
	private Integer prodReorderQuantity1;

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
		invalidCatgryCode2 = "BOAZ";
		invalidCatgryName2 = "Boa Snakes";
		invalidCatgryCode3 = "BO1";
		invalidCatgryName3 = "Boa Snakes";

		// Product Set 1
		productName1 = "Nike Cloths";
		productDescription1 = "Nike Cloths L";
		productQuantity1 = 50;
		productPrice1 = 50.00;
		productBarCode1 = 1;
		productReorderQuantity1 = 40;
		productReorderThreshold1 = 10;

		// Product Set 2
		productName2 = "Nike Cloths";
		productDescription2 = "Nike Cloths XL";
		productQuantity2 = 30;
		productPrice2 = 60.00;
		productBarCode2 = 11;
		productReorderQuantity2 = 20;
		productReorderThreshold2 = 10;

		// Product Set 3
		productName3 = "Nike Shoes";
		productDescription3 = "Nike Running Shoes Size 9.5";
		productQuantity3 = 80;
		productPrice3 = 100.00;
		productBarCode3 = 111;
		productReorderQuantity3 = 55;
		productReorderThreshold3 = 25;

		// Invalid Product
		invalidProdID1 = "BOA/15";
		invalidProdID2 = "BOAZ/15";
		invalidProductBarCode = 1234;

		// Product Set 4
		prodName1 = "Nike Cloths";
		prodDescription1 = "Nike Cloths L";
		prodQuantity1 = 10;
		prodPrice1 = 50.00;
		prodBarCode1 = "4321";
		prodReorderQuantity1 = 40;
		prodReorderThreshold1 = 10;

	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		InventoryManager.deleteInstance();

		// Destroy all category content;
		category1 = category2 = null;
		categoryCode1 = categoryCode2 = invalidCatgryCode1 = invalidCatgryCode2 = invalidCatgryCode3 = null;
		categoryName1 = categoryName2 = invalidCatgryName2 = invalidCatgryName3 = null;

		// Destroy all product content;
		productName1 = productName2 = productName3 = null;
		productDescription1 = productDescription2 = productDescription3 = null;
		productQuantity1 = productQuantity2 = productQuantity3 = null;
		productPrice1 = productPrice2 = productPrice3 = null;
		productBarCode1 = productBarCode2 = productBarCode3 = invalidProductBarCode = null;
		productReorderThreshold1 = productReorderThreshold2 = productReorderThreshold3 = null;
		productReorderQuantity1 = productReorderQuantity2 = productReorderQuantity3 = null;

		invalidProdID1 = invalidProdID2 = null;
	}

	/***********************************************************/
	// Category
	/***********************************************************/

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
			InventoryManager inventoryManager = InventoryManager.getInstance();

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
		} catch (InventoryException inventoryExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Test the following functionalities: 1. Addition of One New Category Twice
	 * 2. Addition of another Category 3. Check for invalid Category 4. Delete a
	 * category 5. Delete an invalid category
	 */
	@Test
	public void testCategoryFunctionalities() {
		InventoryManager inventoryManager = null;
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			inventoryManager = InventoryManager.getInstance();
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}

		try {
			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add new category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
		} catch (InventoryException inventoryExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}

		try {
			// Add the same category
			Assert.assertFalse(inventoryManager.addCategory(categoryCode1, categoryName1));
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.CATEGORY_ALREADY_PRESENT.toString(), inventoryExp.getMessage());
		}

		try {
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

		} catch (InventoryException inventoryExp) {
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
			InventoryManager inventoryManager = InventoryManager.getInstance();

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
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.INVALID_CODE_LENTH.toString(), inventoryExp.getMessage());
		}
	}

	/**
	 * Addition of Invalid Category due to incorrect characters
	 */
	@Test
	public void testAddInvalidCategoryCharacters() {
		try {
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			InventoryManager inventoryManager = InventoryManager.getInstance();

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
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.INVALID_CODE.toString(), inventoryExp.getMessage());
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
			InventoryManager inventoryManager = InventoryManager.getInstance();

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
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.INVALID_CODE_LENTH.toString(), inventoryExp.getMessage());
		}
	}

	/***********************************************************/
	// Product
	/***********************************************************/

	/**
	 * Addition of new Product
	 */
	@Test
	public void testAddOneProduct() {
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			InventoryManager inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));

			// Add New Product
			Product newProduct = inventoryManager.addProduct(category1.getCode(), productName1, productDescription1,
					String.valueOf(productQuantity1), String.valueOf(productPrice1), String.valueOf(productBarCode1),
					String.valueOf(productReorderThreshold1), String.valueOf(productReorderQuantity1));
			Assert.assertTrue(newProduct != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 1);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct.getIdentifier()));

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Testing the following: 1. Additions of 2 categories 2. Additions of 3
	 * products 3. Update a product 4. Check if Reorder threshold has been
	 * reached
	 */
	@Test
	public void testAddThreeProduct() {
		InventoryManager inventoryManager = null;
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			Assert.assertTrue(inventoryManager.addCategory(categoryCode2, categoryName2));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 3));

			// Add New Product
			Product newProduct1 = inventoryManager.addProduct(category1.getCode(), productName1, productDescription1,
					String.valueOf(productQuantity1), String.valueOf(productPrice1), String.valueOf(productBarCode1),
					String.valueOf(productReorderThreshold1), String.valueOf(productReorderQuantity1));
			Product newProduct2 = inventoryManager.addProduct(category1.getCode(), productName2, productDescription2,
					String.valueOf(productQuantity2), String.valueOf(productPrice2), String.valueOf(productBarCode2),
					String.valueOf(productReorderThreshold2), String.valueOf(productReorderQuantity2));
			Product newProduct3 = inventoryManager.addProduct(category2.getCode(), productName3, productDescription3,
					String.valueOf(productQuantity3), String.valueOf(productPrice3), String.valueOf(productBarCode3),
					String.valueOf(productReorderThreshold3), String.valueOf(productReorderQuantity3));
			Assert.assertTrue(newProduct1 != null);
			Assert.assertTrue(newProduct2 != null);
			Assert.assertTrue(newProduct3 != null);

			// Check Post Conditions
			Assert.assertEquals(3, inventoryManager.getAllProducts().size());
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct1.getIdentifier()));
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct2.getIdentifier()));
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct3.getIdentifier()));

			Product updatedProduct = new Product(newProduct1.getIdentifier(), prodName1, prodDescription1,
					prodQuantity1.toString(), prodPrice1.toString(), prodBarCode1, prodReorderThreshold1.toString(),
					prodReorderQuantity1.toString());
			Assert.assertTrue(inventoryManager.updateProduct(updatedProduct));

			ArrayList<Product> productsBlwThrshld = inventoryManager.getProductsBelowThreshold();
			Assert.assertEquals(productsBlwThrshld.size(), 1);
			Assert.assertEquals(productsBlwThrshld.get(0), updatedProduct);

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Addition of new Product with invalid category
	 */
	@Test
	public void testAddOneProductWithInvalidCategory() {
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			InventoryManager inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Product
			Product newProduct = inventoryManager.addProduct(category1.getCode(), productName1, productDescription1,
					String.valueOf(productQuantity1), String.valueOf(productPrice1), String.valueOf(productBarCode1),
					String.valueOf(productReorderThreshold1), String.valueOf(productReorderQuantity1));
			Assert.assertTrue(newProduct == null);
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 0);

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Find Invalid Product
	 */
	@Test
	public void testFindInvalidProduct() {
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			InventoryManager inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));

			// Add New Product
			Product newProduct = inventoryManager.addProduct(category1.getCode(), productName1, productDescription1,
					String.valueOf(productQuantity1), String.valueOf(productPrice1), String.valueOf(productBarCode1),
					String.valueOf(productReorderThreshold1), String.valueOf(productReorderQuantity1));
			Assert.assertTrue(newProduct != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 1);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct.getIdentifier()));
			Assert.assertFalse(inventoryManager.isValidProduct(invalidProdID1));

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Find Invalid Product ID Format
	 */
	@Test
	public void testFindInvalidProductFormat() {
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			InventoryManager inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));

			// Add New Product
			Product newProduct = inventoryManager.addProduct(category1.getCode(), productName1, productDescription1,
					String.valueOf(productQuantity1), String.valueOf(productPrice1), String.valueOf(productBarCode1),
					String.valueOf(productReorderThreshold1), String.valueOf(productReorderQuantity1));
			Assert.assertTrue(newProduct != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 1);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct.getIdentifier()));
			inventoryManager.isValidProduct(invalidProdID2);
			fail(JUnitMessages.Error.JUNIT_FAIL);

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.INVALID_CODE_LENTH.toString(), inventoryExp.getMessage());
		}
	}

	/**
	 * Check threshold should throw a exception when no products are found in
	 * store
	 */
	@Test
	public void testNoProductThresholdCheck() {
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			InventoryManager inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));

			inventoryManager.getProductsBelowThreshold();
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.PRODUCT_ZERO.toString(), inventoryExp.getMessage());
		}
	}
	
	@Test
	public void testProductBarCodeExists() {
		try {
			// Copy Test File Product.dat
			JUnitUtility.copyFile(Constants.Data.FileName.CATEGORY_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			InventoryManager inventoryManager = InventoryManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.CATEGORY_DAT, 1));
			Assert.assertTrue(inventoryManager.getAllCategories().size() == 1);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.PRODUCT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 1));

			// Add New Category
			Assert.assertTrue(inventoryManager.addCategory(categoryCode1, categoryName1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 2));

			// Add New Product
			Product newProduct = inventoryManager.addProduct(category1.getCode(), productName1, productDescription1,
					String.valueOf(productQuantity1), String.valueOf(productPrice1), String.valueOf(prodBarCode1),
					String.valueOf(productReorderThreshold1), String.valueOf(productReorderQuantity1));
			// Add another product with same Barcode as before
			Product newProduct2 = inventoryManager.addProduct(category1.getCode(), productName2, productDescription2,
					String.valueOf(productQuantity2), String.valueOf(productPrice2), String.valueOf(prodBarCode1),
					String.valueOf(productReorderThreshold2), String.valueOf(productReorderQuantity2));
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (InventoryException inventoryExp) {
			Assert.assertEquals(InventoryError.PRODUCT_BAR_CODE_EXISTS.toString(), inventoryExp.getMessage());
		}
	}
	
	
}
