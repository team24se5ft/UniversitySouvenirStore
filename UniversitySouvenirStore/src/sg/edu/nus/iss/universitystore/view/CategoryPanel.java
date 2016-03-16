/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	/*
	 * Array for storing the category table headers.
	 */
	private String[] tableHeaders;

	/*
	 * Array for storing the category content.
	 */
	private String[] tableContent;
	
	/**
	 * Delegate for calling the controller.
	 */
	private IInventoryDelegate delegate;
	
	/***********************************************************/
	// Getters & Setters
	/***********************************************************/
	public void setTableHeader(String[] tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

	public void setTableContent(String[] tableContent) {
		this.tableContent = tableContent;
	}

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
	public CategoryPanel(String[] tableContent,String[] tableHeaders, IInventoryDelegate delegate) {
		// Store the values.
		this.tableContent = tableContent;
		this.tableHeaders = tableHeaders;
		this.delegate = delegate;
		// Start with the GUI.
		setLayout(new BorderLayout());
		add(getScrollPaneWithTable(this.tableContent, this.tableHeaders),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
	}
}
