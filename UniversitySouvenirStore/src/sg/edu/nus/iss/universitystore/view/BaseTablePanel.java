/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Samrat
 *
 */
public class BaseTablePanel extends JPanel{

	/***********************************************************/
	// Constants
	/***********************************************************/
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * Button which deals with adding elements. Will be a part of the bottom panel.
	 */
	protected JButton btnAdd;
	/**
	 * Button which deals with editing an element. Will be a part of the bottom panel.
	 */
	protected JButton btnEdit;
	/**
	 * Button which deals with deleting an element. Will be a part of the bottom panel.
	 */
	protected JButton btnDelete;

	/***********************************************************/
	// Protected Methods
	/***********************************************************/
	/**
	 * Method to create a JTable with all the customization.
	 * Each time it is called, a new instance will be created.
	 * @param data The data that needs to be populated in the table.
	 * @param headers The headers of the table.
	 * @return The JTable with all the values populated.
	 */
	protected JTable getTable(String[] data, String[] headers) {

		String tableData[][] = { data };

		// Complete the abstract class & create a new instance
		DefaultTableModel categoryModel = new DefaultTableModel(tableData, headers) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Create the table
		JTable jTable = new JTable(categoryModel);
		jTable.setRowHeight(30);//TODO - Move to constants
		jTable.setBorder(BorderFactory.createEtchedBorder());
		jTable.setGridColor(Color.BLACK);
		jTable.setIntercellSpacing(new Dimension(1, 1));
		return jTable;
	}

	/**
	 * Method to create a JPanel which will have the "Add/Edit/Delete" functionality.
	 * @param add The action listener for triggering the add action.
	 * @param edit The edit listener for triggering the edit action.
	 * @param delete The delete listener for triggering the delete action.
	 * @return The panel containing all the three buttons.
	 */
	protected JPanel getButtonPanel(ActionListener add, ActionListener edit, ActionListener delete) {
		JPanel jPanel = new JPanel();
		
		// Initialize the button.
		btnAdd = new JButton(new ImageIcon("Resources/add_icon.png"));
		btnEdit = new JButton(new ImageIcon("Resources/edit_icon.png"));
		btnDelete = new JButton(new ImageIcon("Resources/delete_icon.png"));

		// UI for the buttons.
		btnAdd.setBorder(BorderFactory.createEmptyBorder());
		btnEdit.setBorder(BorderFactory.createEmptyBorder());
		btnDelete.setBorder(BorderFactory.createEmptyBorder());

		btnAdd.setPreferredSize(new Dimension(70, 70));
		btnEdit.setPreferredSize(new Dimension(70, 70));
		btnDelete.setPreferredSize(new Dimension(70, 70));
		
		// Add the actions.
		btnAdd.addActionListener(add);
		btnEdit.addActionListener(edit);
		btnDelete.addActionListener(delete);
		
		// Add the buttons to the panel.
		jPanel.add(btnAdd);
		jPanel.add(btnEdit);
		jPanel.add(btnDelete);
		
		// Return the panel.
		return jPanel;
	}
}
