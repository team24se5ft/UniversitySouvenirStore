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
		categoryPanel = new CategoryPanel(null, null,delegate);
		productPanel = new ProductPanel(null, null, delegate);
		createTabbedPane(categoryPanel, productPanel);
	}
	/***********************************************************/
	// Private Methods
	/***********************************************************/

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
