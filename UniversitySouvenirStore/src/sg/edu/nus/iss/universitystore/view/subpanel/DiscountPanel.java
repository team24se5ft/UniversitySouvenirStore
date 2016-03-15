package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DiscountPanel extends JPanel {
	private JTable DiscountTable;


	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	
	private ImageIcon addIcon;
	private ImageIcon editIcon;
	private ImageIcon deleteIcon;
	
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
	    
		//ImageIcons with Label
		addIcon = new ImageIcon("Resources/add_icon.png");
		editIcon = new ImageIcon("Resources/edit_icon.png");
		deleteIcon = new ImageIcon("Resources/delete_icon.png");
		
		btnAdd = new JButton("Add Discount");
		btnAdd.setIcon(addIcon);
		btnAdd.setHorizontalTextPosition(JLabel.CENTER);
		btnAdd.setVerticalTextPosition(JLabel.BOTTOM);
		btnAdd.setBorderPainted(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setFocusPainted(false);
		btnAdd.setOpaque(false);
		
		btnEdit = new JButton("Edit Discount");
		btnEdit.setIcon(editIcon);
		btnEdit.setHorizontalTextPosition(JLabel.CENTER);
		btnEdit.setVerticalTextPosition(JLabel.BOTTOM);
		btnEdit.setBorderPainted(false);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setFocusPainted(false);
		btnEdit.setOpaque(false);
		
		
		btnDelete = new JButton("Delete Discount");
		btnDelete.setIcon(deleteIcon);
		btnDelete.setHorizontalTextPosition(JLabel.CENTER);
		btnDelete.setVerticalTextPosition(JLabel.BOTTOM);
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setFocusPainted(false);
		btnDelete.setOpaque(false);

		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
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
