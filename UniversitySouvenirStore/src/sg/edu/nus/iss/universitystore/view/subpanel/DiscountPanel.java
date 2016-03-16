package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.view.intf.IDiscountDelegate;
/**
 * 
 * @author linby
 *
 */
public class DiscountPanel extends JPanel {
	private JTable DiscountTable;

	
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	private JPanel buttonPanel;

	private IDiscountDelegate delegate;//to communicate with Discount controller

	/***********************************************************/
	//Constructors
	/***********************************************************/
	public DiscountPanel(IDiscountDelegate delegate) {
		setBackground(Color.WHITE);
		this.delegate = delegate;
		BorderLayout borderLayout = new BorderLayout();
		//set height and width gap
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initDiscountTable();
		initButtonPanel();
		// add(new JLabel("discount"));
	}

	/***********************************************************/
	//Private Methods
	/***********************************************************/
	private void initDiscountTable() {
		//FIXME hardcode need to fix after use formal data
		String[] headers = { "code", "percentage", "type", "description", "startDate", "period" };
		String[] content = { "Holiday", "20.0%", "M", "holiday celebration", "27/06", "18" };
		String data[][] = { content };
		//init table model
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

		// ImageIcons with Label
		btnAdd = initImageButton("Resources/add_icon.png", "Add Discount");
		btnEdit = initImageButton("Resources/edit_icon.png", "Edit Discount");
		btnDelete = initImageButton("Resources/delete_icon.png", "Delete Discount");

		// // Buttons
		// btnAdd = new JButton(new ImageIcon("Resources/add_icon.png"));
		// btnEdit = new JButton(new ImageIcon("Resources/edit_icon.png"));
		// btnDelete = new JButton(new ImageIcon("Resources/delete_icon.png"));
		//
		// // setting border to empty
		// btnAdd.setBorder(BorderFactory.createEmptyBorder());
		// btnEdit.setBorder(BorderFactory.createEmptyBorder());
		// btnDelete.setBorder(BorderFactory.createEmptyBorder());
		//
		// btnAdd.setPreferredSize(new Dimension(70, 70));
		// btnEdit.setPreferredSize(new Dimension(70, 70));
		// btnDelete.setPreferredSize(new Dimension(70, 70));

		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		add(buttonPanel, "South");
		initButtonEvent();
	}

	/**
	 * imageButton init here
	 * 
	 * @param imageUrl
	 * @param btnText
	 * @return
	 */
	private JButton initImageButton(String imageUrl, String btnText) {
		ImageIcon icon = new ImageIcon(imageUrl);
		JButton btn = new JButton(btnText);
		btn.setIcon(icon);
		btn.setHorizontalTextPosition(JLabel.CENTER);
		btn.setVerticalTextPosition(JLabel.BOTTOM);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
		return btn;
	}
	
	/**
	 * all the DiscountPanel button`s event init here
	 */
	private void initButtonEvent() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.deleteDiscount(DiscountTable.getSelectedRow());
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addDiscount();
			}
		});

	}

	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	/**
	 * after confirm in dialog to remove item
	 * @param row which row should be deleted
	 */
	public void onRemoveDiscount(int row) {
		DefaultTableModel model = (DefaultTableModel) DiscountTable.getModel();
		if (DiscountTable.getSelectedRow() != -1) {
			model.removeRow(row);
		}
	}

	/**
	 * after click ok in discountPanel to addDiscount
	 * @param discount
	 */
	public void onAddDiscount(Discount discount) {
		DefaultTableModel model = (DefaultTableModel) DiscountTable.getModel();
		model.addRow(new Object[] { discount.getCode(), discount.getPercentage() + "%", discount.getEligibilty(),
				discount.getDescription(), discount.getStartDate().toString(), discount.getPeriod() });
	}
	
}
