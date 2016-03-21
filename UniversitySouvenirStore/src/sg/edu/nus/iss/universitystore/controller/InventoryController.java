package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.Constants;
import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.dialog.CategoryDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ProductDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.ICategoryDialogDelegate;
import sg.edu.nus.iss.universitystore.view.dialog.intf.IProductDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.CategoryPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;

/**
 * @author Samrat
 *
 */
public class InventoryController implements IInventoryDelegate, ICategoryDialogDelegate, IProductDialogDelegate{

	/***********************************************************/
	// Constants
	/***********************************************************/
	public static final String STR_ERROR_MESSAGE_CATEGORY_CODE_OR_NAME_EMPTY = "The category code or name cannot be left empty.";
	public static final String STR_ERROR_FAILED = "The category was not added.";
	public static final String STR_SUCCESS_MESSAGE = "The category was successfully added.";
	public static final String STR_ERROR_CATEGORY_3_DIGIT = "The category code should only consist of three alphabets without any spaces.";
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
	public InventoryController(){
		try {
			inventoryManager = InventoryManager.getInstance();
			arrCategory = inventoryManager.getAllCategories();
			arrProduct = inventoryManager.getAllProducts();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}

		// Initialize the panel
		inventoryPanel = new InventoryPanel(this);

		// Get main frame
		topFrame = (JFrame) SwingUtilities.getWindowAncestor(inventoryPanel);
		// TODO: Setup the various components of the panel with the data retrieved from the manager.
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
		CategoryDialog categoryDialog = new CategoryDialog(topFrame, "Add Category", this);
		categoryDialog.setVisible(true);
	}

	@Override
	public void editCategoryClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategoryClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProductClicked() {
		ProductDialog productDialog = new ProductDialog(topFrame, "Add Product", this);
		productDialog.setVisible(true);

	}

	@Override
	public void editProductClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProductClicked() {
		// TODO Auto-generated method stub

	}
	/***********************************************************/
	// ICategoryDialogDelegate Implementation
	/***********************************************************/
	
	// TODO - Speak to Choo about the validation.
	public void confirmClicked(String categoryCode, String categoryName) {
		// Client side validation
		if(categoryCode.length() > 0 && categoryName.length() > 0) {
			// Category Code client side validation.
			if(categoryCode.length() == 3) {
				try {
					inventoryManager.addCategory(categoryCode, categoryName);
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_SUCCESS, STR_SUCCESS_MESSAGE,
							DialogType.INFORMATION_MESSAGE);
				} catch (Exception e) {
					UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
				}
			} else {
				UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING, STR_ERROR_CATEGORY_3_DIGIT,
						DialogType.WARNING_MESSAGE);
			}
		} else {
			UIUtils.showMessageDialog(inventoryPanel, ViewConstants.ErrorMessages.STR_WARNING, STR_ERROR_MESSAGE_CATEGORY_CODE_OR_NAME_EMPTY,
					DialogType.WARNING_MESSAGE);
		}
	}
	
	/***********************************************************/
	// IProductDialogDelegate Implementation
	/***********************************************************/
	public void confirmClicked(String productName, String productDescription, String quantity, String price, String barcodeNumber, String reorderQuantity, String orderQuantity) {
		//inventoryManager.addProduct(goods);
	}
}
