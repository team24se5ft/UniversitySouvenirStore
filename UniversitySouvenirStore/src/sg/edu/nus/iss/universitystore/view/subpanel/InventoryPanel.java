package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.view.BasePanel;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;

/**
 * @author Samrat
 *
 */

public class InventoryPanel extends BasePanel{

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
		categoryPanel = new CategoryPanel(delegate);
		productPanel = new ProductPanel(delegate);
		createTabbedPane(categoryPanel, productPanel);
	}
	/***********************************************************/
	// Public Methods
	/***********************************************************/
	/**
	 * Method to set the table data for Category Panel
	 * @param content The table content to displayed as part of category panel.
	 * @param headers The table headers to displayed as part of category panel.
	 */
	public void setCategoryTableData(String[][] content, String[] headers) {
		categoryPanel.updateTable(content, headers);
	}
	
	/**
	 * Method to set the table data for Product Panel
	 * @param content The table content to displayed as part of product panel.
	 * @param headers The table headers to displayed as part of product panel.
	 */
	public void setProductTableData(String[][] content, String[] headers) {
		productPanel.updateTable(content, headers);
	}
	/***********************************************************/
	// Private Methods
	/***********************************************************/

	private void createTabbedPane(JPanel categoryPanel, JPanel productPanel) {
		tabbedPane = new JTabbedPane();
		// TODO - Add image to the tabbed pane?
		tabbedPane.addTab(ViewConstants.PaneHeaders.INV_CATEGORY_PANE, null, categoryPanel,
				ViewConstants.PaneHeaders.INV_CATEGORY_PANE_DESC);
		tabbedPane.addTab(ViewConstants.PaneHeaders.INV_PRODUCT_PANE, null, productPanel,
				ViewConstants.PaneHeaders.INV_PRODUCT_PANE_DESC);
		add(tabbedPane, BorderLayout.CENTER);
	}
}
