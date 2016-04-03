package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.messages.Messages;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.validation.InventoryValidation;
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
		} catch (InventoryException inventoryExp) {
			UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
					inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
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
		CategoryDialog categoryDialog = new CategoryDialog(topFrame, ViewConstants.Labels.STR_ADD_CATEGORY) {

			private static final long serialVersionUID = 1L;

			// The callback implementation
			@Override
			public boolean categoryCallback(String categoryCode, String categoryName) {
				try {
					if (InventoryValidation.Catgory.isValidData(categoryCode.toUpperCase(), categoryName)) {
						inventoryManager.addCategory(categoryCode.toUpperCase(), categoryName);
						// Show the success dialog
						UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.SUCCESS,
								ViewConstants.Controller.SUCCESS_MESSAGE, DialogType.INFORMATION_MESSAGE);
						// Update the table
						arrCategory = inventoryManager.getAllCategories();
						inventoryPanel.setCategoryTableData(
								TableDataUtils.getFormattedCategoryListForTable(arrCategory),
								TableDataUtils.getHeadersForCategoryTable());
						// Dismiss the add category dialog
						return true;
					}
				} catch (InventoryException inventoryExp) {
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
							inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
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
		CategoryDialog categoryDialog = new CategoryDialog(topFrame, ViewConstants.Labels.STR_EDIT_CATEGORY) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean categoryCallback(String categoryCode, String categoryName) {
				try {
					if (InventoryValidation.Catgory.isValidData(categoryCode.toUpperCase(), categoryName)) {
						// If the value is valid Update the value in the dB
						Category updatedCategory = new Category(categoryCode.toUpperCase(), categoryName);
						inventoryManager.updateCategory(category, updatedCategory);
						// Update the local copy
						arrCategory = inventoryManager.getAllCategories();
						// Update table
						inventoryPanel.setCategoryTableData(
								TableDataUtils.getFormattedCategoryListForTable(arrCategory),
								TableDataUtils.getHeadersForCategoryTable());
						// Dismiss the dialog OR show a success dialog
						return true;
					}
				} catch (InventoryException inventoryExp) {
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
							inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
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
		// The category cannot have less than five items
		if(arrCategory.size() > Constants.Data.Category.CATEGORY_MINIMUM_COUNT) {
			ConfirmationDialog confirmationDialog = new ConfirmationDialog(topFrame,
					ViewConstants.Labels.STR_DELETE_CATEGORY, ViewConstants.Controller.InventoryController.DEL_CAT_CONF) {

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
					} catch (InventoryException inventoryExp) {
						UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
								inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
					}
					// Remove the dialog
					return true;
				}
			};
			confirmationDialog.setVisible(true);
		}else {
			UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
					Messages.Error.Category.CATEGORY_COUNT_LESS_THAN_EXPECTED, DialogType.ERROR_MESSAGE);
		}
		
	}

	/***********************************************************/
	// CRUD operations for Products
	/***********************************************************/

	@Override
	public void addProductClicked() {
		try {
			if(inventoryManager.getAllCategories().size() == 0) {
				UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR, Messages.Error.Controller.CAT_BEFORE_PROD,
						DialogType.ERROR_MESSAGE);
			}else {
				// Add a new Product Dialog
				ProductDialog productDialog = new ProductDialog(topFrame,
						ViewConstants.Controller.InventoryController.ADD_PRODUCT) {
					private static final long serialVersionUID = 1L;

					@Override
					public boolean productCallback(String categoryCode, String name, String description, String quantity,
							String price, String barCode, String reorderThreshold, String reorderQuantity) {
						try {
							if (InventoryValidation.Product.isValidData(categoryCode, name, description, quantity, price, barCode,
									reorderThreshold, reorderQuantity)) {
								// Add the new product
								inventoryManager.addProduct(categoryCode, name, description, quantity, price, barCode, reorderThreshold,
										reorderQuantity);
								// Show the success dialog
								UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.SUCCESS,
										ViewConstants.Controller.SUCCESS_MESSAGE, DialogType.INFORMATION_MESSAGE);
								// Update the local copy
								arrProduct = inventoryManager.getAllProducts();
								// Update the table
								inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
										TableDataUtils.getHeadersForProductTable());
								return true;
							}
						} catch (InventoryException inventoryExp) {
							UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
									inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
						}
						// Hide the dialog after execution.
						return false;
					}
				};
				// Set the list of categories that need to be displayed.
				productDialog.setCategoryCodeList(getCategoryCode(arrCategory));

				// Show the dialog.
				productDialog.setVisible(true);
			}
		} catch (InventoryException inventoryExp) {
			UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
					inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
		}
		
		
	}

	@Override
	public void editProductClicked(int index) {
		// Get the object at the index
		Product product = arrProduct.get(index);

		ProductDialog productDialog = new ProductDialog(topFrame,
				ViewConstants.Controller.InventoryController.EDIT_PRODUCT) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean productCallback(String productID, String name, String description, String quantity,
					String price, String barCode, String reorderThreshold, String reorderQuantity) {
				try {
					if (InventoryValidation.Product.isValidData(name, description, quantity, price, barCode,
							reorderThreshold, reorderQuantity)) {
						Product updatedProduct = new Product(productID, name, description, quantity, price,
								barCode, reorderThreshold, reorderQuantity);
						inventoryManager.updateProduct(updatedProduct);
						// Update the local copy
						arrProduct = inventoryManager.getAllProducts();
						// Update table
						// Update the table
						inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
								TableDataUtils.getHeadersForProductTable());
						// Dismiss the dialog OR show a success dialog
						return true;
					}
				} catch (InventoryException inventoryExp) {
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
							inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
				}
				return false;
			}
		};
		// Set all the values now
		productDialog.setProductName(product.getName());
		productDialog.setProductDescription(product.getDescription());
		productDialog.setProductPrice(String.valueOf(product.getPrice()));
		productDialog.setProductBarCode(String.valueOf(product.getBarCode()));
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
		// Minium number of products to be retained.
		if(arrProduct.size() > Constants.Data.Product.PRODUCT_MINIMUM_COUNT) {
			ConfirmationDialog confirmationDialog = new ConfirmationDialog(topFrame,
					ViewConstants.Controller.InventoryController.DEL_PRODUCT,
					ViewConstants.Controller.InventoryController.DEL_PROD_CONF) {

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
					} catch (InventoryException inventoryExp) {
						UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
								inventoryExp.getMessage(), DialogType.ERROR_MESSAGE);
					}
					// Remove the dialog
					return true;
				}
			};
			confirmationDialog.setVisible(true);
		}else {
			UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
					Messages.Error.Product.PRODUCT_COUNT_LESS_THAN_EXPECTED, DialogType.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void rowNotSelected() {
		// Display message for error.
		UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR,
				ViewConstants.Controller.PLEASE_SELECT_ROW, DialogType.WARNING_MESSAGE);
	}

	@Override
	public void onProductPanelVisible() {
		try {
			arrProduct = inventoryManager.getAllProducts();
			// Update the table
			inventoryPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
					TableDataUtils.getHeadersForProductTable());
		} catch (InventoryException e) {
			UIUtils.showMessageDialog(inventoryPanel, ViewConstants.StatusMessage.ERROR, e.getMessage(),
					DialogType.ERROR_MESSAGE);
		}
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
