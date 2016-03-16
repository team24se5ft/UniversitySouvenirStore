package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;

/**
 * @author Samrat
 *
 */

public class InventoryPanel extends JPanel{

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
		categoryPanel.add(getScrollPaneWithTable(categoryTableContent, categoryTableHeaders),BorderLayout.CENTER);
		// Add the button
		return categoryPanel;
	}

	/**
	 * Method to create the Product Panel
	 * @return Retrun the product panel after updating the UI.
	 */
	private JPanel createProductPanel() {
		// TEST
		productTableHeaders = new String[]{ "Product Name", "Quantity" , "Price"};
		productTableContent = new String[]{ "Samsung Galaxy", "65", "999" };
		// Get the panel elements ready.
		productPanel = new JPanel();
		productPanel.setLayout(new BorderLayout());
		// Add the table
		productPanel.add(getScrollPaneWithTable(productTableContent, productTableHeaders), BorderLayout.CENTER);
		return productPanel;
	}

	/**
	 * Method to create a JScrollpane containing a JTable.
	 * @param data The data that needs to be populated in the table.
	 * @param headers The headers of the table.
	 * @return The JScrollPane with all the values populated.
	 */
	private JScrollPane getScrollPaneWithTable(String[] data, String[] headers) {

		String tableData[][] = { data };

		// Complete the abstract class & create a new instance
		DefaultTableModel categoryModel = new DefaultTableModel(tableData, headers) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Create the table
		JTable categoryTable = new JTable(categoryModel);
		categoryTable.setRowHeight(30);
		categoryTable.setBorder(BorderFactory.createEtchedBorder());
		categoryTable.setGridColor(Color.BLACK);
		categoryTable.setIntercellSpacing(new Dimension(1, 1));
		// Create the scroll pane
		JScrollPane scrollPane = new JScrollPane(categoryTable);
		return scrollPane;
	}
}
