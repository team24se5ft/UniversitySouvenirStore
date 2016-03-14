package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		String[] headers = { "name", "percentage", "type", "description" };
		String[] content = { "Holiday", "20%", "member", "holiday celebration" };
		String data[][] = { content };
		DefaultTableModel model = new DefaultTableModel(data, headers) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DiscountTable = new JTable(model);
		DiscountTable.setRowHeight(40);
		DiscountTable.setBorder(BorderFactory.createEtchedBorder());
		DiscountTable.setGridColor(Color.BLACK);
		DiscountTable.setIntercellSpacing(new Dimension(1, 1));
		JScrollPane JSP = new JScrollPane(DiscountTable);
		add(JSP, "Center");
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
		initButtonEvent();
	}

	private void initButtonEvent() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) DiscountTable.getModel();
				model.removeRow(DiscountTable.getSelectedRow());
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// after comfirm dialog
				DefaultTableModel model = (DefaultTableModel) DiscountTable.getModel();
				model.addRow(new Object[] { "HelloWorld", "100%", "public", "free whole day!!!" });
			}
		});

	}

}
