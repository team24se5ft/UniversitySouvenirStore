/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * @author Samrat
 *
 */
public abstract class BaseTablePanel extends JPanel {

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
	 * Button which deals with adding elements. Will be a part of the bottom
	 * panel.
	 */
	protected JButton btnAdd;
	/**
	 * Button which deals with editing an element. Will be a part of the bottom
	 * panel.
	 */
	protected JButton btnEdit;
	/**
	 * Button which deals with deleting an element. Will be a part of the bottom
	 * panel.
	 */
	protected JButton btnDelete;
	/**
	 * Table for displaying the elements.
	 */
	protected JTable table;
	/**
	 * Table for displaying the elements.
	 */
	protected JPanel buttonPanel;

	/**
	 * The table model used for populating the table.
	 */
	protected DefaultTableModel tableModel;

	/*
	 * Array for storing the category table headers.
	 */
	protected String[] tableHeaders;

	/*
	 * Array for storing the category content.
	 */
	protected String[][] tableContent;

	/***********************************************************/
	// Abstract Methods
	/***********************************************************/
	protected abstract ActionListener addAction();

	protected abstract ActionListener editAction();

	protected abstract ActionListener deleteAction();

	/***********************************************************/
	// Protected Methods
	/***********************************************************/

	/**
	 * Method to create a JScrollPane with a JTable in it. Each time it is
	 * called, a new instance will be created.
	 * 
	 * @param data
	 *            The data that needs to be populated in the table.
	 * @param headers
	 *            The headers of the table.
	 * @return The JScrollPane with an embedded table with all the values
	 *         populated.
	 */
	protected JScrollPane getScrollPaneWithTable(String[][] data, String[] headers) {
		// Store the values for later use
		this.tableContent = data;
		this.tableHeaders = headers;

		// Complete the abstract class & create a new instance
		tableModel = new DefaultTableModel(data, headers) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Create the table
		table = new JTable(tableModel) {

			private static final long serialVersionUID = 1L;

			// Method to override for getting alternate row colors.
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				// Alternate row color
				if (!isRowSelected(row))
					c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);

				return c;
			}
		};
		table.setRowHeight(30);// TODO - Move to constants
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.setGridColor(Color.BLACK);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);

		// Make the header of the table centered
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		// Disable multiple selection
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	/**
	 * Method to create a JPanel which will have the "Add/Edit/Delete"
	 * functionality. This will automatically assign the various button actions
	 * from the abstract methods.
	 * 
	 * @return The panel containing all the three buttons.
	 */
	protected JPanel getButtonPanel() {
		buttonPanel = new JPanel();

		// Initialize the button.
		btnAdd = new JButton(new ImageIcon("Resources/add_icon.png"));
		btnEdit = new JButton();//for round button icon
		btnDelete = new JButton(new ImageIcon("Resources/delete_icon.png"));

		// UI for the buttons.
		btnAdd.setBorder(BorderFactory.createEmptyBorder());
		btnEdit.setBorder(BorderFactory.createEmptyBorder());
		btnDelete.setBorder(BorderFactory.createEmptyBorder());

		btnAdd.setPreferredSize(new Dimension(70, 70));
		btnEdit.setPreferredSize(new Dimension(70, 70));
		btnDelete.setPreferredSize(new Dimension(70, 70));

		// Add the actions.
		btnAdd.addActionListener(addAction());
		btnEdit.addActionListener(editAction());
		btnDelete.addActionListener(deleteAction());

		// Add the buttons to the panel.
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		
		// Update the middle button
		ImageIcon imageIcon = new ImageIcon("Resources/edit_icon.png");
		Image img = imageIcon.getImage();
		Image newimg = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		btnEdit.setIcon(newIcon);

		// Return the panel.
		return buttonPanel;
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * Method to update the UI of the table.
	 * 
	 * @param data
	 *            The data that needs to be populated in the table.
	 * @param headers
	 *            The headers of the table.
	 */
	public void updateTable(String[][] data, String[] headers) {
		// Store the values for later use
		this.tableContent = data;
		this.tableHeaders = headers;
		// Update the UI.
		tableModel.setDataVector(data, headers);
	}

	/**
	 * Method to remove the button panel, if present.
	 */
	public void removeButtonPanel() {
		remove(buttonPanel);
	}
}
