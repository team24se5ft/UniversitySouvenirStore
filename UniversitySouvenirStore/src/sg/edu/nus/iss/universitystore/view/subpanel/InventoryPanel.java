package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;

/**
 * @author Samrat
 *
 */

public class InventoryPanel extends BaseTablePanel{

	/***********************************************************/
	// Constants
	/***********************************************************/
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * The main tabbed pane of the screen. Segregates between Show All & Create New options. 
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Panel which displays all the functionalities related to the category.
	 */
	private JPanel categoryPanel;

	/**
	 * Panel which displays all the functionalities related to the product.
	 */
	private JPanel productPanel;

	/**
	 * Delegate for informing the controller about the various events.
	 */
	private IInventoryDelegate delegate;

	/**
	 * Table consisting of all categories.
	 */
	private JTable categoryTable;

	/**
	 * The table consisting of all the products.
	 */
	private JTable productTable;
	/*
	 * Array for storing the category table headers.
	 */
	private String[] categoryTableHeaders;

	/*
	 * Array for storing the category content.
	 */
	private String[] categoryTableContent;

	/*
	 * Array for storing the product table headers.
	 */
	private String[] productTableHeaders;

	/*
	 * Array for storing the product content.
	 */
	private String[] productTableContent; 
	/***********************************************************/
	// Constructors
	/***********************************************************/
	public InventoryPanel(IInventoryDelegate delegate){
		// Set the delegate
		this.delegate = delegate;
		// Set layout of main component
		BorderLayout borderLayout = new BorderLayout();
		// Height & width gap
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		setLayout(borderLayout);

		// Start adding other components
		createGUI();
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public void setCategoryTableData(String[] headers, String[] content) {
		// Store the values
		categoryTableHeaders = headers;
		categoryTableContent = content;
	}

	public void setProductTableData(String[] headers, String[] content) {
		// Store the values
		productTableHeaders = headers;
		productTableContent = content;
	}
	/***********************************************************/
	// Private Methods
	/***********************************************************/
	private void createGUI() {
		createTabbedPane();
	}

	private void createTabbedPane() {
		tabbedPane = new JTabbedPane();
		// TODO - Add image to the tabbed pane?
		tabbedPane.addTab("Category", null, createCategoryPanel(),
				"All functionalities related to the categories.");
		tabbedPane.addTab("Product", null, createProductPanel(),
				"All functionalities related to the products.");
		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Method to create the category panel.
	 * @return Return the category panel after updating the UI.
	 */
	private JPanel createCategoryPanel() {
		// TEST
		categoryTableHeaders = new String[]{ "Category Name", "Category Code" };
		categoryTableContent = new String[]{ "Phones", "PHO" };
		// Get the panel elements ready.
		categoryPanel = new JPanel();
		categoryPanel.setLayout(new BorderLayout());
		// Add the table
		categoryTable = getTable(categoryTableContent, categoryTableHeaders);
		JScrollPane scrollPane = new JScrollPane(categoryTable);
		categoryPanel.add(scrollPane,BorderLayout.CENTER);
		// Add the button panel
		ActionListener addCategory = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};

		ActionListener editCategory = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};

		ActionListener deleteCategory = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
		categoryPanel.add(getButtonPanel(addCategory, editCategory, deleteCategory), BorderLayout.SOUTH);
		return categoryPanel;
	}

	/**
	 * Method to create the Product Panel
	 * @return Return the product panel after updating the UI.
	 */
	private JPanel createProductPanel() {
		// TEST
		productTableHeaders = new String[]{ "Product Name", "Quantity" , "Price"};
		productTableContent = new String[]{ "Samsung Galaxy", "65", "999" };
		// Get the panel elements ready.
		productPanel = new JPanel();
		productPanel.setLayout(new BorderLayout());
		// Add the table
		productTable = getTable(productTableContent, productTableHeaders);
		JScrollPane scrollPane = new JScrollPane(productTable);
		productPanel.add(scrollPane,BorderLayout.CENTER);
		return productPanel;
	}
}
