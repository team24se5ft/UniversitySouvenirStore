package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Goods;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.Vendor;

/**
 * Manager Class used to handle Inventory
 * 
 * @author Sanjay
 *
 */
public class InventoryManager {

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

	/**
	 * Get a single instance of Data File Manager
	 * 
	 * @return DataFileManager
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static InventoryManager getInstance() throws FileNotFoundException, IOException {
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
	 * Inventory Manager Constructor
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public InventoryManager() throws FileNotFoundException, IOException {
		productID = (productID == null) ? 1 : productID;
		initialize();
	}

	/**
	 * Initialize Data used for inventory Category, Product and Vendor
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		categoryData = new DataFile<>(Constants.Data.FileName.CATEGORY_DAT);
		productData = new DataFile<>(Constants.Data.FileName.PRODUCT_DAT);

		initializeVendors();
	}

	/**
	 * Initializes Data for Vendor
	 * 
	 * @throws IOException
	 */
	private void initializeVendors() throws IOException {
		vendorMap = new HashMap<>();

		// Get all categories list
		ArrayList<Category> categoriesList = getAllCategories();

		for (Category category : categoriesList) {
			vendorMap.put(category.getCode(),
					new DataFile<Vendor>(Constants.Data.FileName.VENDOR_DAT + category.getCode()));
		}

	}

	/**
	 * (3.6.b) Get All Categories from Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Category> getAllCategories() throws IOException {
		ArrayList<Category> categoryList = new ArrayList<>();
		String[] categoriesStrList = categoryData.getAll();

		for (String categoryStr : categoriesStrList) {

			// If line in Data file is empty, skip line
			if (categoryStr.isEmpty())
				continue;

			categoryList.add(new Category(categoryStr.split(Constants.Data.FILE_SEPTR)));
		}

		return categoryList;
	}

	/**
	 * (3.6.a, 3.8.a) Add new Category Add new Vendor for new Category
	 * 
	 * @param category
	 * @throws IOException
	 */
	public void addCategory(Category category) throws IOException {
		categoryData.add(category);

		// TODO: Good to have a check if category code already exists at backend
		vendorMap.put(category.getCode(),
				new DataFile<Vendor>(Constants.Data.FileName.VENDOR_DAT + category.getCode()));
	}

	/**
	 * (3.6.e) Delete a Category
	 * 
	 * @param category
	 */
	public void deleteCategory(Category category) {
		// TODO: Good to Have
	}

	/**
	 * (3.6.f) Update a Category
	 * 
	 * @param category
	 */
	public void updateCategory(Category category) {
		// TODO: Good to Have
	}

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
	 * (3.5.b, 3.5.c.2) Add Product
	 * 
	 * @param goods
	 * @throws IOException
	 */
	public void addProduct(Goods goods) throws IOException {

		StringBuffer productID = new StringBuffer();
		productID.append(goods.getCategory().getCode());
		productID.append(Constants.Data.ID_SEPTR);
		productID.append(generateProductID());

		productData.add(new Product(productID.toString(), goods));
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
	 * (3.3.a, 3.3.b) Get List of Products below Threshold
	 * 
	 * @return List of Products
	 * @throws IOException
	 */
	public ArrayList<Product> getProductsBelowThreshold() throws IOException {
		ArrayList<Product> productList = getAllProducts();
		// List of Products below Threshold
		ArrayList<Product> blwTheshProdList = new ArrayList<>();

		for (Product product : productList) {
			// Check if Quantity is less than Reorder Threshold
			if (product.getQuantity() < product.getReorderThreshold())
				blwTheshProdList.add(product);
		}

		return blwTheshProdList;
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

		for (String vendorStr : vendorMap.get(categoryCode).getAll()) {

			String[] vendorStrSplt = vendorStr.split(Constants.Data.FILE_SEPTR);

			vendorList.add(
					new Vendor(vendorStrSplt[VendorArg.NAME.ordinal()], vendorStrSplt[VendorArg.DECRIPTION.ordinal()]));
		}

		return vendorList;
	}

}
