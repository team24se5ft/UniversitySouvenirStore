package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.view.dialog.CategoryDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.ICategoryDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;

/**
 * @author Samrat
 *
 */
public class InventoryController implements IInventoryDelegate, ICategoryDialogDelegate{

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
		// TODO Auto-generated method stub

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
	public void confirmClicked(String categoryName) {
		try {
			inventoryManager.addCategory(categoryName, categoryName);
			System.out.println(inventoryManager.getAllCategories());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
