/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Samrat
 *
 */
public class BaseTablePanel extends JPanel{

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
}
