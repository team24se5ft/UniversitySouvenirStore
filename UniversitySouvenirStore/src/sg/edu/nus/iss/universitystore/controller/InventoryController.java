package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
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
	public static final String STR_SUCCESS_MESSAGE = "The category was successfully added.";
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
			inventoryManager = InventoryManager.getInstance();
			arrCategory = inventoryManager.getAllCategories();
			System.out.println(arrCategory);
			arrProduct = inventoryManager.getAllProducts();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
		// Initialize the panel associated with this controller
		inventoryPanel = new InventoryPanel(this);

		// Update Inventory Panel with retrieved data
		inventoryPanel.setCategoryTableData(TableDataUtils.getFormattedCategoryListForTable(arrCategory),
				TableDataUtils.getHeadersForCategoryTable());
		inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
				TableDataUtils.getHeadersForProductTable());

		// Get main frame
		topFrame = (JFrame) SwingUtilities.getWindowAncestor(inventoryPanel);
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

	@Override
	public void addProductClicked() {
		ProductDialog productDialog = new ProductDialog(topFrame, "Add Product") {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean productCallback(String name, String description, String quantity, String price,
					String barcodeNumber, String reorderThreshold, String reorderQuantity) {
				try {
					// Add the new product
					inventoryManager.addProduct("LPC", name, description, quantity, price, reorderThreshold,
							reorderQuantity);
					// Update the local copy
					arrProduct = inventoryManager.getAllProducts();
					// Update the table
					inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
							TableDataUtils.getHeadersForProductTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				return true;
			}
		};
		productDialog.setVisible(true);
	}

	@Override
	public void editProductClicked(int index) {
		// TODO Auto-generated method stub

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
}
