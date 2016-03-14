package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DiscountPanel extends JPanel {
	private JTable DiscountTable;

	private JButton btnActive;
	private JButton btnStop;
	private JButton btnAdd;
	private JButton btnDelete;

	private JPanel buttonPanel;

	public DiscountPanel() {
		setBackground(Color.WHITE);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initDiscountTable();
		initButtonPanel();
		// add(new JLabel("discount"));
	}

	private void initDiscountTable() {
		String[] headers = { "title1", "title2", "title3" };
		Object[][] cellData = { { "row1-col1", "row1-col2", "row1-col3" }, { "row2-col1", "row2-col2", "row2-col3" } };

		DefaultTableModel model = new DefaultTableModel(cellData, headers) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DiscountTable = new JTable(model);
		DiscountTable.getTableHeader().setVisible(true);
		add(DiscountTable, "Center");
	}

	private void initButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		btnActive = new JButton("Active");
		btnActive.setPreferredSize(new Dimension(120, 40));
		btnStop = new JButton("Stop");
		btnStop.setPreferredSize(new Dimension(120, 40));
		btnAdd = new JButton("Add");
		btnAdd.setPreferredSize(new Dimension(120, 40));
		btnDelete = new JButton("Delete");
		btnDelete.setPreferredSize(new Dimension(120, 40));
		buttonPanel.add(btnActive);
		buttonPanel.add(btnStop);
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnDelete);
		add(buttonPanel, "South");
	}

}
