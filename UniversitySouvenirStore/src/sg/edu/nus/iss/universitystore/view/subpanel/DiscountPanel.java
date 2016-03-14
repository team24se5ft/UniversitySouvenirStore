package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DiscountPanel extends JPanel {
	private JTable DiscountTable;


	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	
	private BufferedImage addIcon;
	private BufferedImage editIcon;
	private BufferedImage deleteIcon;


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
	
		try {
			addIcon = ImageIO.read(new File("Resources/add_icon.png"));
			editIcon = ImageIO.read(new File("Resources/edit_icon.png"));
			deleteIcon = ImageIO.read(new File("Resources/delete_icon.png"));
			btnAdd = new JButton(new ImageIcon(addIcon));
			btnEdit = new JButton(new ImageIcon(editIcon));
			btnDelete = new JButton(new ImageIcon(deleteIcon));
		} catch (IOException e1) {
				e1.printStackTrace();
		}
		
		//setting border to empty
		btnAdd.setBorder(BorderFactory.createEmptyBorder());
		btnEdit.setBorder(BorderFactory.createEmptyBorder());
		btnDelete.setBorder(BorderFactory.createEmptyBorder());
		
		btnAdd.setPreferredSize(new Dimension(70, 70));
		btnEdit.setPreferredSize(new Dimension(70, 70));
		btnDelete.setPreferredSize(new Dimension(70, 70));

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
