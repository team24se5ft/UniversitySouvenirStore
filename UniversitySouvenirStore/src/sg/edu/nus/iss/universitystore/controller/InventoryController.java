package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.messages.MessageHandler;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.dialog.CategoryDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmationDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ProductDialog;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;

/**
 * @author Samrat
 *
 */
public class InventoryController implements IInventoryDelegate {

	/***********************************************************/
	// Constants
	/***********************************************************/
	public static final String STR_ERROR_MESSAGE_CATEGORY_CODE_OR_NAME_EMPTY = "The category code or name cannot be left empty.";
	public static final String STR_ERROR_FAILED = "The category was not added.";
	public static final String STR_SUCCESS_MESSAGE = "Successfully added.";
	public static final String STR_ERROR_CATEGORY_3_DIGIT = "The category code should only consist of three alphabets without any spaces.";
	public static final String STR_ERROR_ROW_NOT_SELECTED = "Please select a row of the table for completing this operation.";
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * Instance of the Inventory Manager for retrieving the data from the dB.
	 */
	private InventoryManager inventoryManager;

	/**
	 * Holds the reference to the view associated to show the inventory.
	 */
	private InventoryPanel inventoryPanel;

	/**
	 * The list of all categories.
	 */
	private ArrayList<Category> arrCategory;

	/**
	 * The list of all products.
	 */
	private ArrayList<Product> arrProduct;

	/**
	 * The reference to the main frame on which the current panel is added.
	 */
	private JFrame topFrame;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Inventory Controller Constructor
	 */
	public InventoryController() {
		try {
			// Initialize the instance variables.
			inventoryManager = InventoryManager.getInstance();
			arrCategory = inventoryManager.getAllCategories();
			arrProduct = inventoryManager.getAllProducts();
			
			// Initialize the panel associated with this controller
			inventoryPanel = new InventoryPanel(this);

			// Update Inventory Panel with retrieved data
			inventoryPanel.setCategoryTableData(TableDataUtils.getFormattedCategoryListForTable(arrCategory),
					TableDataUtils.getHeadersForCategoryTable());
			inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
					TableDataUtils.getHeadersForProductTable());

			// Get main frame reference
			topFrame = (JFrame) SwingUtilities.getWindowAncestor(inventoryPanel);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
	}

	/***********************************************************/
	// Getters & setters
	/***********************************************************/
	public InventoryPanel getInventoryPanel() {
		return inventoryPanel;
	}

	/***********************************************************/
	// IInventoryDelegate Implementation
	/***********************************************************/

	/***********************************************************/
	// CRUD operations for Categories
	/***********************************************************/
	@Override
	public void addCategoryClicked() {
		CategoryDialog categoryDialog = new CategoryDialog(topFrame, "Add Category") {

			private static final long serialVersionUID = 1L;

			// The callback implementation
			@Override
			public boolean categoryCallback(String categoryCode, String categoryName) {
				if (categoryCode.length() > 0 && categoryName.length() > 0) {
					// Category Code client side validation.
					if (categoryCode.length() == 3) {
						try {
							inventoryManager.addCategory(categoryCode, categoryName);
							// Show the success dialog
							UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_SUCCESS,
									STR_SUCCESS_MESSAGE, DialogType.INFORMATION_MESSAGE);
							// Update the table
							arrCategory = inventoryManager.getAllCategories();
							inventoryPanel.setCategoryTableData(
									TableDataUtils.getFormattedCategoryListForTable(arrCategory),
									TableDataUtils.getHeadersForCategoryTable());
							// Dismiss the add category dialog
							return true;
						} catch (Exception e) {
							UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING,
									e.getMessage(), DialogType.WARNING_MESSAGE);
						}
					} else {
						UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING,
								STR_ERROR_CATEGORY_3_DIGIT, DialogType.WARNING_MESSAGE);
					}
				} else {
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING,
							STR_ERROR_MESSAGE_CATEGORY_CODE_OR_NAME_EMPTY, DialogType.WARNING_MESSAGE);
				}
				return false;
			}
		};
		// Make the category dialog visible.
		categoryDialog.setVisible(true);
	}

	@Override
	public void editCategoryClicked(int index) {
		// Get the object at the index
		Category category = arrCategory.get(index);
		// Implement an instance of the category dialog
		CategoryDialog categoryDialog = new CategoryDialog(topFrame, "Edit Category") {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean categoryCallback(String categoryCode, String categoryName) {
				// TODO : Do validation here
				try {
					// If the value is valid Update the value in the dB
					Category updatedCategory = new Category(categoryCode, categoryName);
					inventoryManager.updateCategory(category, updatedCategory);
					// Update the local copy
					arrCategory = inventoryManager.getAllCategories();
					// Update table
					inventoryPanel.setCategoryTableData(TableDataUtils.getFormattedCategoryListForTable(arrCategory),
							TableDataUtils.getHeadersForCategoryTable());
					// Dismiss the dialog OR show a success dialog
					return true;
				} catch (Exception e) {
					// TODO: handle exception
				}
				return false;
			}
		};

		// Set the text of the dialog as per the object
		categoryDialog.setCategoryName(category.getName());
		categoryDialog.setCategoryCode(category.getCode());
		// Make the dialog visible.
		categoryDialog.setVisible(true);
	}

	@Override
	public void deleteCategoryClicked(int index) {
		ConfirmationDialog confirmationDialog = new ConfirmationDialog(topFrame, "Delete Category",
				"Do u really want to delete the category?") {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean confirmClicked() {
				Category category = arrCategory.get(index);
				try {
					// Update the backend
					inventoryManager.deleteCategory(category.getCode());
					// Update the local copy
					arrCategory = inventoryManager.getAllCategories();
					// Update the table
					inventoryPanel.setCategoryTableData(TableDataUtils.getFormattedCategoryListForTable(arrCategory),
							TableDataUtils.getHeadersForCategoryTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				// Remove the dialog
				return true;
			}
		};
		confirmationDialog.setVisible(true);
	}

	/***********************************************************/
	// CRUD operations for Products
	/***********************************************************/

	@Override
	public void addProductClicked() {
		// Add a new Product Dialog
		ProductDialog productDialog = new ProductDialog(topFrame, "Add Product") {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean productCallback(String categoryCode, String name, String description, String quantity,
					String price, String barcodeNumber, String reorderThreshold, String reorderQuantity) {
				try {
					// Add the new product
					inventoryManager.addProduct(categoryCode, name, description, quantity, price, reorderThreshold,
							reorderQuantity);
					// Show the success dialog
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_SUCCESS,
							STR_SUCCESS_MESSAGE, DialogType.INFORMATION_MESSAGE);
					// Update the local copy
					arrProduct = inventoryManager.getAllProducts();
					// Update the table
					inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
							TableDataUtils.getHeadersForProductTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				// Hide the dialog after execution.
				return true;
			}
		};
		// Set the list of categories that need to be displayed.
		productDialog.setCategoryCodeList(getCategoryCode(arrCategory));

		// Show the dialog.
		productDialog.setVisible(true);
	}

	@Override
	public void editProductClicked(int index) {
		// Get the object at the index
		Product product = arrProduct.get(index);

		ProductDialog productDialog = new ProductDialog(topFrame, "Edit Product") {

			@Override
			public boolean productCallback(String categoryCode, String name, String description, String quantity,
					String price, String barcodeNumber, String reorderThreshold, String reorderQuantity) {
				try {
					Product updatedProduct = new Product(categoryCode, name, description, quantity, price,
							reorderThreshold, reorderQuantity);
					inventoryManager.updateProduct(updatedProduct);
					// Update the local copy
					arrProduct = inventoryManager.getAllProducts();
					// Update table
					// Update the table
					inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
							TableDataUtils.getHeadersForProductTable());
					// Dismiss the dialog OR show a success dialog
					return true;
				} catch (Exception e) {
					// TODO: handle exception
				}
				// TODO Auto-generated method stub
				return false;
			}
		};
		// Set all the values now
		productDialog.setProductName(product.getName());
		productDialog.setProductDescription(product.getDescription());
		productDialog.setProductPrice(String.valueOf(product.getPrice()));
		productDialog.setProductQuantity(String.valueOf(product.getQuantity()));
		productDialog.setThresholdQuantity(String.valueOf(product.getReorderThreshold()));
		productDialog.setReorderQuantity(String.valueOf(product.getReorderQuantity()));
		
		// Since the user cannot edit the category type, we only pass the
		// current category.
		productDialog.setCategoryCodeList(new String[] { product.getIdentifier() });

		// Show the dialog.
		productDialog.setVisible(true);
	}

	@Override
	public void deleteProductClicked(int index) {
		ConfirmationDialog confirmationDialog = new ConfirmationDialog(topFrame, "Delete Product",
				"Do u really want to delete the product?") {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean confirmClicked() {
				Product product = arrProduct.get(index);
				try {
					// Update the backend
					inventoryManager.deleteProduct(product);
					// Update the local copy
					arrProduct = inventoryManager.getAllProducts();
					// Update the table
					inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
							TableDataUtils.getHeadersForProductTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				// Remove the dialog
				return true;
			}
		};
		confirmationDialog.setVisible(true);
	}

	@Override
	public void rowNotSelected() {
		// Display message for error.
		UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING, STR_ERROR_ROW_NOT_SELECTED,
				DialogType.WARNING_MESSAGE);
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/**
	 * Method to get the list of category code from a list of category objects.
	 * 
	 * @param arrayList
	 *            The arraylist containing category objects.
	 * @return The array containing only category codes.
	 */
	private String[] getCategoryCode(ArrayList<Category> arrayList) {
		String[] codes = new String[arrayList.size()];
		for (int count = 0; count < arrayList.size(); count++) {
			codes[count] = arrayList.get(count).getCode();
		}
		return codes;
	}
}
