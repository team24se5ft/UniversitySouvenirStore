/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.IInventoryDelegate;

/**
 * @author Samrat
 *
 */
public class CategoryPanel extends BaseTablePanel{

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
	// Abstract Methods Implementation
	/***********************************************************/
	protected ActionListener addAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addCategoryClicked();
			}
		};
	}

	protected ActionListener editAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.editCategoryClicked();
			}
		};
	}

	protected ActionListener deleteAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.deleteCategoryClicked();
			}
		};
	}

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public CategoryPanel(IInventoryDelegate delegate) {
		// Store the values.
		this.delegate = delegate;
		// Start with the GUI.
		setLayout(new BorderLayout());
		// Initialize the tableview with null data. This will be updated when the data from table is available.
		add(getScrollPaneWithTable(null, null),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
	}
}
