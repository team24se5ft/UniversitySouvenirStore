/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;

/**
 * @author Samrat
 *
 */
public class ProductPanel extends BaseTablePanel {

	/***********************************************************/
	// Constants
	/***********************************************************/
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/

	/**
	 * Delegate for calling the controller.
	 */
	private IInventoryDelegate delegate;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public ProductPanel(IInventoryDelegate delegate) {
		// Store the values.
		this.delegate = delegate;
		// Start with the GUI.
		setLayout(new BorderLayout());
		// Initialize the tableview with null data. This will be updated when
		// the data from table is available.
		add(getScrollPaneWithTable(null, null), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);

		// Add the component listener since this class needs to be updated when
		// there is a change on the other fields.
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				// Inform the controller that the panel is visible.
				delegate.onProductPanelVisible();
			}
		});
	}

	/***********************************************************/
	// Abstract Methods Implementation
	/***********************************************************/
	protected ActionListener addAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addProductClicked();
			}
		};
	}

	protected ActionListener editAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					delegate.editProductClicked(table.getSelectedRow());
				} else {
					delegate.rowNotSelected();
				}
			}
		};
	}

	protected ActionListener deleteAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					delegate.deleteProductClicked(table.getSelectedRow());
				} else {
					delegate.rowNotSelected();
				}
			}
		};
	}
}
