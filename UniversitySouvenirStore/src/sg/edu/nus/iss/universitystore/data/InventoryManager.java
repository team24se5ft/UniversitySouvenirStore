package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.messages.Messages;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Goods;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.Vendor;
import sg.edu.nus.iss.universitystore.utility.CommonUtils.MessageTitleType;
import sg.edu.nus.iss.universitystore.utility.CommonUtils.MessageType;

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
		IDENTIFIER(0), NAME(1), DESCRIPTION(2), QUANTITY(3), PRICE(4), REORDERTHRESHOLD(5), REORDERQUANTITY(6);

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
	 */
	public static InventoryManager getInstance() throws FileNotFoundException, IOException, StoreException {
		if (instance == null) {
			synchronized (InventoryManager.class) {
				if (instance == null) {
					instance = new InventoryManager();
				}
			}
		}
		return instance;
	}

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
	 */
	public InventoryManager() throws FileNotFoundException, IOException, StoreException {
		productID = (productID == null) ? 1 : productID;
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
	 */
	private void initialize() throws FileNotFoundException, IOException, StoreException {
		categoryData = new DataFile<>(Constants.Data.FileName.CATEGORY_DAT);
		productData = new DataFile<>(Constants.Data.FileName.PRODUCT_DAT);

		initializeVendors();
	}

	/**
	 * Initializes Data for Vendor
	 * 
	 * @throws IOException
	 * @throws StoreException
	 */
	private void initializeVendors() throws IOException, StoreException {
		vendorMap = new HashMap<>();

		// Get all categories list
		ArrayList<Category> categoriesList = getAllCategories();

		for (Category category : categoriesList) {
			vendorMap.put(category.getCode(),
					new DataFile<Vendor>(Constants.Data.FileName.VENDOR_DAT + category.getCode()));
		}

	}

	/***********************************************************/
	// Category
	/***********************************************************/

	/**
	 * (3.6.b) Get All Categories from Data File
	 * 
	 * @return
	 * @throws IOException
	 * @throws StoreException
	 */
	public ArrayList<Category> getAllCategories() throws IOException, StoreException {
		ArrayList<Category> categoryList = new ArrayList<>();
		String[] categoriesStrList = categoryData.getAll();

		for (String categoryStr : categoriesStrList) {

			// If line in Data file is empty, skip line
			if (categoryStr.isEmpty())
				continue;

			String[] categoryStrSplt = categoryStr.split(Constants.Data.FILE_SEPTR);

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
	 */
	public boolean addCategory(String categoryCode, String categoryName) throws IOException, StoreException {
		Category category = new Category(categoryCode, categoryName);

		// Check if category code already exists
		if (hasCategory(categoryCode))
			return false;
		categoryData.add(category);

		// Add Vendor Data file
		vendorMap.put(category.getCode(),
				new DataFile<Vendor>(Constants.Data.FileName.VENDOR_DAT + category.getCode()));

		return true;
	}

	/**
	 * (3.6.e) Delete a Category
	 * 
	 * @param category
	 * @throws IOException
	 * @throws StoreException
	 */
	public boolean deleteCategory(String categoryCode) throws IOException, StoreException {
		// TODO: Good to Have
		boolean status = false;

		if (hasCategory(categoryCode)) {
			Category category = findCategory(categoryCode);

			if (categoryData.delete(category.toString())) {
				status = deleteAllVendors(categoryCode);
			}
		}

		return status;
	}

	/**
	 * (3.6.f) Update a Category
	 * 
	 * @param oldCategory The category object that needs to be updated.
	 * @param updatedCategory The new category object.
	 * @throws StoreException
	 */
	public void updateCategory(Category oldCategory, Category updatedCategory) throws IOException, StoreException {
		// Check if the category exists
		if(hasCategory(oldCategory.getCode())) {
			// First, delete the category
			deleteCategory(oldCategory.getCode());
			// Next up, add the new category
			addCategory(updatedCategory.getCode(), updatedCategory.getName());
		}
	}

	/**
	 * (3.9.a)Find Category
	 * 
	 * @param categoryCode
	 * @throws IOException
	 * @throws StoreException
	 */
	public Category findCategory(String categoryCode) throws IOException, StoreException {
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
	 */
	public boolean hasCategory(String categoryCode) throws IOException, StoreException {
		isValidCategory(categoryCode);

		return findCategory(categoryCode) != null;
	}

	private void isValidCategory(String categoryCode) throws StoreException {
		if (categoryCode.length() != 3)
			throw new StoreException(MessageTitleType.ERROR, Messages.Error.Category.INVALID_CODE_LENGTH,
					MessageType.ERROR_MESSAGE);
		else if (!categoryCode.matches("^[a-zA-Z]{3}$"))
			throw new StoreException(MessageTitleType.ERROR, Messages.Error.Category.INVALID_CHARACTERS,
					MessageType.ERROR_MESSAGE);
	}

	/***********************************************************/
	// Product
	/***********************************************************/

	/**
	 * (3.5.a, 3.8.b) Get All Products from Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Product> getAllProducts() throws IOException {
		ArrayList<Product> productList = new ArrayList<>();
		String[] productStrList = productData.getAll();

		for (String productStr : productStrList) {

			// If line in Data file is empty, skip line
			if (productStr.isEmpty())
				continue;

			String[] productStrSplt = productStr.split(Constants.Data.FILE_SEPTR);

			productList.add(new Product(productStrSplt[ProductArg.IDENTIFIER.ordinal()],
					productStrSplt[ProductArg.NAME.ordinal()], productStrSplt[ProductArg.DESCRIPTION.ordinal()],
					productStrSplt[ProductArg.QUANTITY.ordinal()], productStrSplt[ProductArg.PRICE.ordinal()],
					productStrSplt[ProductArg.REORDERTHRESHOLD.ordinal()],
					productStrSplt[ProductArg.REORDERQUANTITY.ordinal()]));
		}

		return productList;
	}

	/**
	 * (3.5.i) Add Product for vendor
	 * 
	 * @param goods
	 * @throws IOException
	 * @throws StoreException
	 */
	public Product addProduct(Goods goods) throws IOException, StoreException {

		return addProduct(goods.getCategory().getCode(), goods.getName(), goods.getDescription(),
				String.valueOf(goods.getQuantity()), String.valueOf(goods.getPrice()),
				String.valueOf(goods.getReorderThreshold()), String.valueOf(goods.getReorderQuantity()));
	}
	
	/**
	 * (3.5.b, 3.5.c.2) Add Product
	 * 
	 * @param goods
	 * @throws IOException
	 * @throws StoreException
	 */
	public Product addProduct(String categoryCode, String name, String description, String quantity, String price,
			String reorderThreshold, String reorderQuantity) throws IOException, StoreException {

		if (!hasCategory(categoryCode))
			return null;

		StringBuffer productID = new StringBuffer();
		productID.append(categoryCode);
		productID.append(Constants.Data.ID_SEPTR);
		productID.append(generateProductID());

		Product product = new Product(productID.toString(), name, description, quantity, price, reorderThreshold,
				reorderQuantity);

		return productData.add(product) ? product : null;
	}

	/**
	 * (3.5.c.1) Generate Product ID
	 * 
	 * @return Product ID
	 */
	private int generateProductID() {
		// TODO: Generate Random Number
		return productID++;
	}

	/**
	 * (3.5.d) Find Product
	 * 
	 * @param productID
	 * @return Product found
	 * @throws IOException
	 */
	public Product findProduct(String productID) throws IOException {
		ArrayList<Product> productList = getAllProducts();
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
	 */
	public boolean isValidProduct(String productID) throws IOException, StoreException {
		boolean status = false;

		String categoryCode = productID.replaceAll(Constants.Data.Product.Pattern.ID_MATCH,
				Constants.Data.Product.Pattern.ID_REPLACE);
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
	 * @throws IOException
	 * @throws StoreException
	 */
	public boolean deleteProduct(Product product) throws IOException, StoreException {
		boolean status = false;
		
		if(!isValidProduct(product.getIdentifier()))
			return false;
		
		product = findProduct(product.getIdentifier());
		if(productData.delete(product.toString())){
			status = true;
		}
		
		return status;
	}
	
	/**
	 * (3.5.g) Update details of the product
	 * 
	 * @param newProduct
	 * @return
	 * @throws IOException
	 * @throws StoreException
	 */
	public boolean updateProduct(Product newProduct) throws IOException, StoreException {
		boolean status = false;
		
		if(!isValidProduct(newProduct.getIdentifier()))
			return status;
		
		Product existingProduct = findProduct(newProduct.getIdentifier());
		if(deleteProduct(existingProduct)){
			status = productData.add(newProduct);
		}
		
		return status;
	}

	/**
	 * (3.3.a, 3.3.b) Get List of Products below Threshold
	 * 
	 * @return List of Products
	 * @throws IOException
	 * @throws StoreException 
	 */
	public ArrayList<Product> getProductsBelowThreshold() throws IOException, StoreException {
		ArrayList<Product> productList = getAllProducts();
		if(productList.size() == 0)
			throw new StoreException(Messages.Error.Product.PRODUCT_ZERO);
		
		// List of Products below Threshold
		ArrayList<Product> blwTheshProdList = new ArrayList<>();

		for (Product product : productList) {
			// Check if Quantity is less than Reorder Threshold
			if (product.getQuantity() <= product.getReorderThreshold())
				blwTheshProdList.add(product);
		}

		return blwTheshProdList;
	}

	/***********************************************************/
	// Vendor
	/***********************************************************/

	/**
	 * (3.3.d) Get All Vendors from Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Vendor> getAllVendors(String categoryCode) throws IOException {
		ArrayList<Vendor> vendorList = new ArrayList<>();
		DataFile<Vendor> vendorData = vendorMap.get(categoryCode);
		String[] vendorStrList = vendorData.getAll();

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
	 * (3.10.a) Add a Vendor
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @param vendorDescription
	 * @return Status
	 */
	public boolean addVendor(String categoryCode, String vendorName, String vendorDescription) {
		boolean status = false;
		// TODO: Good to have - Add a vendor to vendor list
		return false;
	}

	/**
	 * (3.10.b) Delete all Vendors
	 * 
	 * @param categoryCode
	 * @return
	 */
	public boolean deleteAllVendors(String categoryCode) {
		boolean status = false;

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
	public boolean deleteVendor(String categoryCode, String vendorName) throws IOException {
		boolean status = false;

		DataFile<Vendor> vendorData = vendorMap.get(categoryCode);
		Vendor vendor = findVendor(categoryCode, vendorName);

		if (vendorData != null && vendor != null) {
			status = vendorData.delete(vendor.toString());
		}

		return status;
	}

	/**
	 * (3.10.d) Find Vendor
	 * 
	 * @param categoryCode
	 * @throws IOException
	 */
	public Vendor findVendor(String categoryCode, String vendorName) throws IOException {
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
	public ArrayList<Vendor> getVendorBasedOnProduct(Product product) throws IOException {
		ArrayList<Vendor> vendorList = new ArrayList<>();
		// TODO: GOOD to have a check if category is valid

		String categoryCode = product.getIdentifier().split(Constants.Data.ID_SEPTR)[0];

		return getAllVendors(categoryCode);
	}

}
