package sg.edu.nus.iss.universitystore.data;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.intf.UniversityStoreJUnit;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;
import sg.edu.nus.iss.universitystore.messages.Messages;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Goods;
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
	private String categoryCode1, categoryCode2, categoryCode3, invalidCatgryCode1, invalidCatgryCode2,
			invalidCatgryCode3;
	/**
	 * Category Names
	 */
	private String categoryName1, categoryName2, categoryName3, invalidCatgryName1, invalidCatgryName2,
			invalidCatgryName3;

	/**
	 * Goods
	 */
	private Goods goods1, goods2, goods3;
	/**
	 * Goods Name
	 */
	private String goodsName1, goodsName2, goodsName3;
	/**
	 * Goods Description
	 */
	private String goodsDescription1, goodsDescription2, goodsDescription3;
	/**
	 * Goods Quality
	 */
	private Integer goodsQuantity1, goodsQuantity2, goodsQuantity3;
	/**
	 * Goods Price
	 */
	private Double goodsPrice1, goodsPrice2, goodsPrice3;
	/**
	 * Goods Reorder Threshold
	 */
	private Integer goodsReorderThreshold1, goodsReorderThreshold2, goodsReorderThreshold3;
	/**
	 * Goods Reorder Quantity
	 */
	private Integer goodsReorderQuantity1, goodsReorderQuantity2, goodsReorderQuantity3;
	/**
	 * Invalid Product Identifier
	 */
	private String invalidProdID1, invalidProdID2;
	/**
	 * Products
	 */
	private Product product1, product2;
	/**
	 * Product Name
	 */
	private String prodName1, prodName2;
	/**
	 * Product Description
	 */
	private String prodDescription1, prodDescription2;
	/**
	 * Product Quality
	 */
	private Integer prodQuantity1, prodQuantity2;
	/**
	 * Product Price
	 */
	private Double prodPrice1, prodPrice2;
	/**
	 * Product Reorder Threshold
	 */
	private Integer prodReorderThreshold1, prodReorderThreshold2;
	/**
	 * Product Reorder Quantity
	 */
	private Integer prodReorderQuantity1, prodReorderQuantity2;
	

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

		// Goods Set 1
		goodsName1 = "Nike Cloths";
		goodsDescription1 = "Nike Cloths L";
		goodsQuantity1 = 50;
		goodsPrice1 = 50.00;
		goodsReorderQuantity1 = 40;
		goodsReorderThreshold1 = 10;
		goods1 = new Goods(category1, goodsName1, goodsDescription1, goodsQuantity1.intValue(),
				goodsPrice1.doubleValue(), goodsReorderThreshold1.intValue(), goodsReorderQuantity1.intValue());

		// Goods Set 2
		goodsName2 = "Nike Cloths";
		goodsDescription2 = "Nike Cloths XL";
		goodsQuantity2 = 30;
		goodsPrice2 = 60.00;
		goodsReorderQuantity2 = 20;
		goodsReorderThreshold2 = 10;
		goods2 = new Goods(category1, goodsName2, goodsDescription2, goodsQuantity2.intValue(),
				goodsPrice2.doubleValue(), goodsReorderThreshold2.intValue(), goodsReorderQuantity2.intValue());

		// Goods Set 3
		goodsName3 = "Nike Shoes";
		goodsDescription3 = "Nike Running Shoes Size 9.5";
		goodsQuantity3 = 80;
		goodsPrice3 = 100.00;
		goodsReorderQuantity3 = 55;
		goodsReorderThreshold3 = 25;
		goods3 = new Goods(category2, goodsName3, goodsDescription3, goodsQuantity3.intValue(),
				goodsPrice3.doubleValue(), goodsReorderThreshold3.intValue(), goodsReorderQuantity3.intValue());

		// Invalid Product
		invalidProdID1 = "BOA/15";
		invalidProdID2 = "BOAZ/15";
		
		// Product Set 1
		prodName1 = "Nike Cloths";
		prodDescription1 = "Nike Cloths L";
		prodQuantity1 = 10;
		prodPrice1 = 50.00;
		prodReorderQuantity1 = 40;
		prodReorderThreshold1 = 10;
		

		// Product Set 2
		prodName2 = "Nike Cloths";
		prodDescription2 = "Nike Cloths XL";
		prodQuantity2 = 5;
		prodPrice2 = 60.00;
		prodReorderQuantity2 = 20;
		prodReorderThreshold2 = 10;
		/*product2 = new Product(category1, prodName2, prodDescription2, prodQuantity2.intValue(),
				prodPrice2.doubleValue(), prodReorderThreshold2.intValue(), prodReorderQuantity2.intValue());*/

	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		InventoryManager.deleteInstance();

		// Destroy all category content;
		category1 = category2 = null;
		categoryCode1 = categoryCode2 = categoryCode3 = invalidCatgryCode1 = invalidCatgryCode2 = invalidCatgryCode3 = null;
		categoryName1 = categoryName2 = categoryName3 = invalidCatgryName1 = invalidCatgryName2 = invalidCatgryName3 = null;

		// Destroy all goods content;
		goods1 = goods2 = goods3 = null;
		goodsName1 = goodsName2 = goodsName3 = null;
		goodsDescription1 = goodsDescription2 = goodsDescription3 = null;
		goodsQuantity1 = goodsQuantity2 = goodsQuantity3 = null;
		goodsPrice1 = goodsPrice2 = goodsPrice3 = null;
		goodsReorderThreshold1 = goodsReorderThreshold2 = goodsReorderThreshold3 = null;
		goodsReorderQuantity1 = goodsReorderQuantity2 = goodsReorderQuantity3 = null;

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
			InventoryManager inventoryManager = InventoryManager.getInstance();

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
		} catch (StoreException exception) {
			Assert.assertEquals(exception.getMessage(), Messages.Error.Category.INVALID_CODE_LENGTH);
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
			Product newProduct = inventoryManager.addProduct(goods1);
			Assert.assertTrue(newProduct != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 1);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct.getIdentifier()));

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Testing the following: 
	 * 1. Additions of 2 categories 
	 * 2. Additions of 3 products
	 * 3. Update a product
	 * 4. Check if Reorder threshold has been reached
	 */
	@Test
	public void testAddThreeProduct() {
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
			Assert.assertTrue(inventoryManager.addCategory(categoryCode2, categoryName2));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.VENDOR_DAT, 3));

			// Add New Product
			Product newProduct1 = inventoryManager.addProduct(goods1);
			Product newProduct2 = inventoryManager.addProduct(goods2);
			Product newProduct3 = inventoryManager.addProduct(goods3);
			Assert.assertTrue(newProduct1 != null);
			Assert.assertTrue(newProduct2 != null);
			Assert.assertTrue(newProduct3 != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 3);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct1.getIdentifier()));
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct2.getIdentifier()));
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct3.getIdentifier()));
			
			Product updatedProduct = new Product(newProduct1.getIdentifier(), prodName1, prodDescription1, prodQuantity1.toString(),
					prodPrice1.toString(), prodReorderThreshold1.toString(), prodReorderQuantity1.toString());
			Assert.assertTrue(inventoryManager.updateProduct(updatedProduct));
			
			ArrayList<Product> productsBlwThrshld = inventoryManager.getProductsBelowThreshold();
			Assert.assertEquals(productsBlwThrshld.size(), 1);
			Assert.assertEquals(productsBlwThrshld.get(0),updatedProduct);
			

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
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
			Product newProduct = inventoryManager.addProduct(goods1);
			Assert.assertTrue(newProduct == null);
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 0);

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
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
			Product newProduct = inventoryManager.addProduct(goods1);
			Assert.assertTrue(newProduct != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 1);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct.getIdentifier()));
			Assert.assertFalse(inventoryManager.isValidProduct(invalidProdID1));

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
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
			Product newProduct = inventoryManager.addProduct(goods1);
			Assert.assertTrue(newProduct != null);

			// Check Post Conditions
			Assert.assertEquals(inventoryManager.getAllProducts().size(), 1);
			Assert.assertTrue(inventoryManager.isValidProduct(newProduct.getIdentifier()));
			inventoryManager.isValidProduct(invalidProdID2);
			fail(JUnitMessages.Error.JUNIT_FAIL);

		} catch (IOException exception) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (StoreException exception) {
			Assert.assertEquals(Messages.Error.Category.INVALID_CODE_LENGTH, exception.getMessage());
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
		} catch (StoreException exception) {
			Assert.assertEquals(exception.getMessage(), Messages.Error.Product.PRODUCT_ZERO);
		}
	}
}
