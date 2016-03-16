/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				// TODO Auto-generated method stub

			}
		};
	}

	protected ActionListener editAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}

	protected ActionListener deleteAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public CategoryPanel(String[] tableContent,String[] tableHeaders) {
		// Store the values.
		this.tableContent = tableContent;
		this.tableHeaders = tableHeaders;
		// Start with the GUI.
		setLayout(new BorderLayout());
		add(getScrollPaneWithTable(tableContent, tableHeaders),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
	}
}
