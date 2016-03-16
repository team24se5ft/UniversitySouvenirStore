package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
	 * The main tabbed pane of the screen. 
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Panel which displays all the functionalities related to the category.
	 */
	private CategoryPanel categoryPanel;

	/**
	 * Panel which displays all the functionalities related to the product.
	 */
	private ProductPanel productPanel;

	/**
	 * Delegate for informing the controller about the various events.
	 */
	private IInventoryDelegate delegate;

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
	public void setCategoryTableData(String[] header, String[] content) {
		// Store the values
		categoryPanel.setTableHeader(header);
		categoryPanel.setTableContent(content);
	}

	public void setProductTableData(String[] header, String[] content) {
		// Store the values
		productPanel.setTableHeader(header);
		productPanel.setTableContent(content);
	}
	/***********************************************************/
	// Private Methods
	/***********************************************************/
	private void createGUI() {
		// TEST
		String[] categoryTableHeaders = new String[]{ "Category Name", "Category Code" };
		String[] categoryTableContent = new String[]{ "Phones", "PHO" };
		categoryPanel = new CategoryPanel(categoryTableContent, categoryTableHeaders,delegate);
		// TEST
		categoryTableHeaders = new String[]{ "Product Name", "Quantity" , "Price"};
		categoryTableContent = new String[]{ "Samsung Galaxy", "65", "999" };
		productPanel = new ProductPanel(categoryTableContent, categoryTableHeaders, delegate);
		createTabbedPane(categoryPanel, productPanel);
	}

	private void createTabbedPane(JPanel categoryPanel, JPanel productPanel) {
		tabbedPane = new JTabbedPane();
		// TODO - Add image to the tabbed pane?
		tabbedPane.addTab("Category", null, categoryPanel,
				"All functionalities related to the categories.");
		tabbedPane.addTab("Product", null, productPanel,
				"All functionalities related to the products.");
		add(tabbedPane, BorderLayout.CENTER);
	}
}
