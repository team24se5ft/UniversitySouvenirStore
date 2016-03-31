package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.InventoryException.InventoryError;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Goods;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.Vendor;
import sg.edu.nus.iss.universitystore.utility.DateUtils;
import sg.edu.nus.iss.universitystore.validation.InventoryValidation;

/**
 * Manager Class used to handle Inventory
 * 
 * @author Sanjay
 *
 */
public class InventoryManager {

	/**
	 * Category Arguments
	 */
	public enum CategoryArg {
		CODE(0), NAME(1);

		private int position;

		private CategoryArg(int position) {
			this.position = position;
		}
	}

	/**
	 * Product Arguments
	 */
	public enum ProductArg {
		IDENTIFIER(0), NAME(1), QUANTITY(2), PRICE(3), REORDERTHRESHOLD(4), REORDERQUANTITY(5), DESCRIPTION(6);

		private int position;

		private ProductArg(int position) {
			this.position = position;
		}
	}

	/**
	 * Vendor Arguments
	 */
	public enum VendorArg {
		NAME(0), DECRIPTION(1);

		private int position;

		private VendorArg(int position) {
			this.position = position;
		}
	}

	/***********************************************************/
	// Instance Variables
	/***********************************************************/

	/**
	 * Instance of Inventory Manager
	 */
	private static InventoryManager instance;

	/**
	 * Product ID which is generated
	 */
	private static Integer productID;

	/**
	 * Category Data
	 */
	private DataFile<Category> categoryData;
	/**
	 * Product Data
	 */
	private DataFile<Product> productData;
	/**
	 * Vendor Map
	 */
	private HashMap<String, DataFile<Vendor>> vendorMap;

	/***********************************************************/
	// Singleton
	/***********************************************************/

	/**
	 * Get a single instance of Data File Manager
	 * 
	 * @return DataFileManager
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public static InventoryManager getInstance() throws InventoryException {
		if (instance == null) {
			synchronized (InventoryManager.class) {
				if (instance == null) {
					instance = new InventoryManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Delete instance of Data File Manager
	 */
	public static void deleteInstance() {
		instance = null;
	}

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Inventory Manager Constructor
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public InventoryManager() throws InventoryException {
		productID = (productID == null) ? Constants.Data.Product.INITIALIZED_COUNT : productID;
		initialize();
	}

	/***********************************************************/
	// Private Methods for Constructors
	/***********************************************************/

	/**
	 * Initialize Data used for inventory Category, Product and Vendor
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	private void initialize() throws InventoryException {
		try {
			categoryData = new DataFile<>(Constants.Data.FileName.CATEGORY_DAT);
			productData = new DataFile<>(Constants.Data.FileName.PRODUCT_DAT);
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}

		initializeVendors();
		if(productID == Constants.Data.Product.INITIALIZED_COUNT){
			initializeProductCounter();			
		}
	}

	/**
	 * Initializes Data for Vendor
	 * 
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	private void initializeVendors() throws InventoryException {
		vendorMap = new HashMap<>();

		// Get all categories list
		ArrayList<Category> categoriesList = getAllCategories();

		for (Category category : categoriesList) {
			addVendorDataFile(category.getCode());
		}

	}
	
	/**
	 * Initializes Product Counter based on the highest value in the Product
	 * Data File
	 * 
	 * @throws InventoryException 
	 */
	private void initializeProductCounter() throws InventoryException  {
		ArrayList<Product> productList = getAllProducts();

		for (Product product : productList) {
			int rowCount = Integer.parseInt(product.getIdentifier().replaceAll(Constants.Data.Product.Pattern.ID_MATCH,
					Constants.Data.Product.Pattern.COUNT_REPLACE));
			if(rowCount > productID)
				productID = rowCount;
		}
	}

	/***********************************************************/
	// Public Methods for Category
	/***********************************************************/

	/**
	 * (3.6.b) Get All Categories from Data File
	 * 
	 * @return
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public ArrayList<Category> getAllCategories() throws InventoryException {
		ArrayList<Category> categoryList = new ArrayList<>();
		String[] categoriesStrList;
		try {
			categoriesStrList = categoryData.getAll();
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}

		for (String categoryStr : categoriesStrList) {

			String[] categoryStrSplt = categoryStr.split(Constants.Data.FILE_SEPTR);
			
			// If line in Data file is empty, skip line
			if (!isValidCategoryData(categoryStrSplt))
				continue;

			categoryList.add(new Category(categoryStrSplt[CategoryArg.CODE.ordinal()],
					categoryStrSplt[CategoryArg.NAME.ordinal()]));
		}

		return categoryList;
	}

	/**
	 * (3.6.a, 3.8.a) Add new Category Add new Vendor for new Category
	 * 
	 * @param category
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public boolean addCategory(String categoryCode, String categoryName) throws InventoryException {
		if(!InventoryValidation.Catgory.isValidData(categoryCode, categoryName))
			return false;
		
		Category category = new Category(categoryCode, categoryName);

		// Check if category code already exists
		if (hasCategory(categoryCode)) {
			throw new InventoryException(InventoryError.CATEGORY_ALREADY_PRESENT);
		}else {
			try {
				categoryData.add(category);
			} catch (IOException ioExp) {
				throw new InventoryException(InventoryError.UNKNOWN_ERROR);
			}
		
			// Add Vendor Data file
			addVendorDataFile(category.getCode());
		}

		return true;
	}

	/**
	 * (3.6.e) Delete a Category
	 * 
	 * @param category
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public boolean deleteCategory(String categoryCode) throws InventoryException {
		boolean status = false;

		if (hasCategory(categoryCode)) {
			Category category = findCategory(categoryCode);
			
			try{
				if (deleteAllVendors(categoryCode)) {
					status = categoryData.delete(category.toString());
				}
			} catch (IOException ioExp) {
				throw new InventoryException(InventoryError.UNKNOWN_ERROR);
			}
		}

		return status;
	}

	/**
	 * (3.6.f) Update a Category
	 * 
	 * @param oldCategory
	 *            The category object that needs to be updated.
	 * @param updatedCategory
	 *            The new category object.
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public void updateCategory(Category oldCategory, Category updatedCategory) throws InventoryException {
		// Check if the category exists
		if (hasCategory(oldCategory.getCode())) {
			// Get existing Vendor list
			ArrayList<Vendor> existingVendorLst = getAllVendors(oldCategory.getCode());
			// First, delete the category
			deleteCategory(oldCategory.getCode());
			// Next up, add the new category
			addCategory(updatedCategory.getCode(), updatedCategory.getName());
			// Add Vendors to new category
			for (Vendor vendor : existingVendorLst) {
				addVendor(updatedCategory.getCode(), vendor.getName(), vendor.getDescription());
			}
		}
	}

	/**
	 * (3.9.a)Find Category
	 * 
	 * @param categoryCode
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public Category findCategory(String categoryCode) throws InventoryException {
		ArrayList<Category> categoryList = getAllCategories();
		Category categoryFound = null;

		for (Category category : categoryList) {
			if (category.getCode().equals(categoryCode))
				categoryFound = category;
		}

		return categoryFound;
	}

	/**
	 * (3.9.b) Checks if Category exists
	 * 
	 * @param categoryCode
	 * @return
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public boolean hasCategory(String categoryCode) throws InventoryException {
		return InventoryValidation.Catgory.isValidCatgoryCode(categoryCode) && findCategory(categoryCode) != null;
	}

	/**
	 * Checks if category content is valid
	 * 
	 * @param categoryList
	 * @return Boolean
	 * @throws InventoryException
	 */
	private boolean isValidCategoryData(String[] categoryList) {
		boolean status = false;
		try {
			if(InventoryValidation.Catgory.isValidData(categoryList[CategoryArg.CODE.ordinal()], categoryList[CategoryArg.NAME.ordinal()])){
				status = true;
			}
		} catch (InventoryException e) {
			status = false;
		}
		return status;
	}

	/***********************************************************/
	// Public Methods for Product
	/***********************************************************/

	/**
	 * (3.5.a, 3.8.b) Get All Products from Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Product> getAllProducts() throws InventoryException {
		ArrayList<Product> productList = new ArrayList<>();
		String[] productStrList;
		try {
			productStrList = productData.getAll();
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}

		for (String productStr : productStrList) {
			
			String[] productStrSpltLst = splitProductData(productStr);

			// If line in Data file is empty, skip line
			if (!isValidProductData(productStrSpltLst))
				continue;			

			productList.add(new Product(productStrSpltLst[ProductArg.IDENTIFIER.ordinal()],
					productStrSpltLst[ProductArg.NAME.ordinal()], productStrSpltLst[ProductArg.DESCRIPTION.ordinal()],
					productStrSpltLst[ProductArg.QUANTITY.ordinal()], productStrSpltLst[ProductArg.PRICE.ordinal()],
					productStrSpltLst[ProductArg.REORDERTHRESHOLD.ordinal()],
					productStrSpltLst[ProductArg.REORDERQUANTITY.ordinal()]));
		}

		return productList;
	}

	/**
	 * (3.5.i) Add Product for vendor
	 * 
	 * @param goods
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public Product addProduct(Goods goods) throws InventoryException {

		return addProduct(goods.getCategory().getCode(), goods.getName(), goods.getDescription(),
				String.valueOf(goods.getQuantity()), String.valueOf(goods.getPrice()),
				String.valueOf(goods.getReorderThreshold()), String.valueOf(goods.getReorderQuantity()));
	}

	/**
	 * (3.5.b, 3.5.c.2) Add Product
	 * 
	 * @param goods
	 * @throws InventoryException 
	 */
	public Product addProduct(String categoryCode, String name, String description, String quantity, String price,
			String reorderThreshold, String reorderQuantity) throws InventoryException {

		if (!hasCategory(categoryCode))
			return null;

		StringBuffer productID = new StringBuffer();
		productID.append(categoryCode);
		productID.append(Constants.Data.ID_SEPTR);
		productID.append(generateProductID());

		Product product = new Product(productID.toString(), name, description, quantity, price, reorderThreshold,
				reorderQuantity);

		try {
			return productData.add(product) ? product : null;
		} catch (IOException inExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}
	}

	/**
	 * (3.5.d) Find Product
	 * 
	 * @param productID
	 * @return Product found
	 * @throws InventoryException 
	 */
	public Product findProduct(String productID) throws InventoryException {
		ArrayList<Product> productList = getAllProducts();
		if(productList.size() == Constants.Data.Product.PRODUCT_ZERO)
			throw new InventoryException(InventoryError.PRODUCT_ZERO);
		
		Product productFound = null;

		for (Product product : productList) {
			if (product.getIdentifier().equals(productID)) {
				productFound = product;
				break;
			}
		}

		return productFound;
	}

	/**
	 * (3.5.e) Check if the product id entered is valid.
	 * 
	 * @param productID
	 * @return
	 * @throws IOException
	 * @throws StoreException
	 * @throws InventoryException 
	 */
	public boolean isValidProduct(String productID) throws InventoryException {
		boolean status = false;

		String categoryCode = productID.replaceAll(Constants.Data.Product.Pattern.ID_MATCH,
				Constants.Data.Product.Pattern.CATEGORY_REPLACE);
		if (hasCategory(categoryCode)) {
			status = findProduct(productID) != null;
		}

		return status;
	}

	/**
	 * (3.5.f) Delete a Product from the store
	 * 
	 * @param product
	 * @return
	 * @throws InventoryException 
	 */
	public boolean deleteProduct(Product product) throws InventoryException {
		boolean status = false;

		if (!isValidProduct(product.getIdentifier()))
			throw new InventoryException(InventoryError.PRODUCT_NOT_AVAILABLE);

		product = findProduct(product.getIdentifier());
		try {
			if (productData.delete(product.toString())) {
				status = true;
			}
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}

		return status;
	}

	/**
	 * (3.5.g) Update details of the product
	 * 
	 * @param newProduct
	 * @return
	 * @throws InventoryException 
	 */
	public boolean updateProduct(Product newProduct) throws InventoryException {
		boolean status = false;

		if (!isValidProduct(newProduct.getIdentifier()))
			throw new InventoryException(InventoryError.PRODUCT_NOT_AVAILABLE);

		Product existingProduct = findProduct(newProduct.getIdentifier());
		if (deleteProduct(existingProduct)) {
			try {
				status = productData.add(newProduct);
			} catch (IOException ioExp) {
				throw new InventoryException(InventoryError.UNKNOWN_ERROR);
			}
		}

		return status;
	}

	/**
	 * (3.3.a, 3.3.b) Get List of Products below Threshold
	 * 
	 * @return List of Products
	 * @throws InventoryException 
	 */
	public ArrayList<Product> getProductsBelowThreshold() throws InventoryException {
		ArrayList<Product> productList = getAllProducts();
		if (productList.size() == Constants.Data.Product.PRODUCT_ZERO)
			throw new InventoryException(InventoryError.PRODUCT_ZERO);

		// List of Products below Threshold
		ArrayList<Product> blwTheshProdList = new ArrayList<>();

		for (Product product : productList) {
			// Check if Quantity is less than Reorder Threshold
			if (product.getQuantity() <= product.getReorderThreshold())
				blwTheshProdList.add(product);
		}

		return blwTheshProdList;
	}
	
	/**
	 * Checks if Data is Valid
	 * 
	 * @param productList
	 * @return Boolean
	 */
	public boolean isValidProductData(String[] productList) {
		boolean status = false;

		try {
			if (productList.length == Constants.Data.Product.DATA_SPLT_LENGTH
					&& InventoryValidation.Product.isValidData(productList[ProductArg.NAME.ordinal()],
							productList[ProductArg.DESCRIPTION.ordinal()], productList[ProductArg.QUANTITY.ordinal()],
							productList[ProductArg.PRICE.ordinal()], productList[ProductArg.REORDERTHRESHOLD.ordinal()],
							productList[ProductArg.REORDERQUANTITY.ordinal()])) {
				String categoryCode = productList[ProductArg.IDENTIFIER.ordinal()].replaceAll(
						Constants.Data.Product.Pattern.ID_MATCH, Constants.Data.Product.Pattern.CATEGORY_REPLACE);
				status = hasCategory(categoryCode);
			}
		} catch (InventoryException inventoryExp) {
			status = false;
		}

		return status;
	}
	
	/**
	 * Splits Row of Data File into a list of Strings
	 * 
	 * @param line
	 * @return Boolean
	 */
	private String[] splitProductData(String productRow) {

		return DateUtils.extractContent(productRow, Constants.Data.Product.Pattern.LINE_MATCH,
				Constants.Data.Product.Pattern.DESCRIPTION_REPLACE,
				Constants.Data.Product.Pattern.OTHER_CNTNT_REPLACE);
	}

	/***********************************************************/
	// Private Methods for Product
	/***********************************************************/

	/**
	 * (3.5.c.1) Generate Product ID
	 * 
	 * @return Product ID
	 */
	private int generateProductID() {
		return ++productID;
	}

	/***********************************************************/
	// Public Methods for Vendor
	/***********************************************************/

	/**
	 * (3.3.d) Get All Vendors from Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Vendor> getAllVendors(String categoryCode) throws InventoryException {
		ArrayList<Vendor> vendorList = new ArrayList<>();
		DataFile<Vendor> vendorData = vendorMap.get(categoryCode);
		String[] vendorStrList;
		try {
			vendorStrList = vendorData.getAll();
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}

		for (String vendorStr : vendorStrList) {

			// If line in Data file is empty, skip line
			if (vendorStr.isEmpty())
				continue;

			String[] vendorStrSplt = vendorStr.split(Constants.Data.FILE_SEPTR);

			vendorList.add(
					new Vendor(vendorStrSplt[VendorArg.NAME.ordinal()], vendorStrSplt[VendorArg.DECRIPTION.ordinal()]));
		}

		return vendorList;
	}
	/**
	 * Add New Vendor Data File
	 * 
	 * @param categoryCode
	 * @throws InventoryException 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void addVendorDataFile(String categoryCode) throws InventoryException {		
		try {
			vendorMap.put(categoryCode,
					new DataFile<Vendor>(Constants.Data.FileName.VENDOR_DAT + categoryCode));
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}
	}

	/**
	 * (3.10.a) Add a Vendor
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @param vendorDescription
	 * @return Status
	 * @throws IOException
	 */
	public boolean addVendor(String categoryCode, String vendorName, String vendorDescription) throws InventoryException {
		if (!hasCategory(categoryCode)) {
			throw new InventoryException(InventoryError.CATEGORY_NOT_AVAILABLE);
		}
		
		DataFile<Vendor> vendorList = vendorMap.get(categoryCode);
		if (vendorList == null) {
			throw new InventoryException(InventoryError.CATEGORY_UNKNOWN_ERROR);
		}
		
		try {
			return vendorList.add(new Vendor(vendorName, vendorDescription));
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}
	}

	/**
	 * (3.10.b) Delete all Vendors
	 * 
	 * @param categoryCode
	 * @return
	 * @throws InventoryException 
	 */
	public boolean deleteAllVendors(String categoryCode) throws InventoryException {
		boolean status = false;
		if (!hasCategory(categoryCode)) {
			throw new InventoryException(InventoryError.CATEGORY_NOT_AVAILABLE);
		}

		DataFile<Vendor> vendorData = vendorMap.get(categoryCode);

		if (vendorData != null) {
			status = vendorData.delete() && vendorMap.remove(categoryCode, vendorData);
		}

		return status;
	}

	/**
	 * (3.10.c) Delete a Vendor from a category
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @return
	 * @throws IOException
	 */
	public boolean deleteVendor(String categoryCode, String vendorName) throws InventoryException {
		boolean status = false;
		if (!hasCategory(categoryCode)) {
			throw new InventoryException(InventoryError.CATEGORY_NOT_AVAILABLE);
		}

		DataFile<Vendor> vendorData = vendorMap.get(categoryCode);
		Vendor vendor = findVendor(categoryCode, vendorName);		
		if (vendorData == null || vendor == null) {
			throw new InventoryException(InventoryError.CATEGORY_UNKNOWN_ERROR);
		}
		
		try {
			status = vendorData.delete(vendor.toString());
		} catch (IOException ioExp) {
			throw new InventoryException(InventoryError.UNKNOWN_ERROR);
		}

		return status;
	}

	/**
	 * (3.10.d) Find Vendor
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @return Vendor as per Category Code and Vendor Name, returns null if not found.
	 * @throws InventoryException
	 */
	public Vendor findVendor(String categoryCode, String vendorName) throws InventoryException {
		ArrayList<Vendor> vendorList = getAllVendors(categoryCode);
		Vendor vendorFound = null;

		for (Vendor vendor : vendorList) {
			if (vendor.getName().equals(categoryCode)) {
				vendorFound = vendor;
				break;
			}
		}

		return vendorFound;
	}

	/**
	 * (3.3.c.1) Get Vendors based on Product
	 * 
	 * @param product
	 * @return List of Vendors
	 * @throws IOException
	 */
	public ArrayList<Vendor> getVendorBasedOnProduct(Product product) throws InventoryException {
		ArrayList<Vendor> vendorList = new ArrayList<>();
		// TODO: GOOD to have a check if category is valid

		String categoryCode = product.getIdentifier().split(Constants.Data.ID_SEPTR)[0];

		return getAllVendors(categoryCode);
	}

}
