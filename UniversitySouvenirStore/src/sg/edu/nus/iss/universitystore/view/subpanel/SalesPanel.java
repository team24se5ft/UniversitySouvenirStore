package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.ISalesDelegate;

public class SalesPanel extends BaseTablePanel {

	private JLabel memberOption;// add memberDialog to check member
	private JTextField LoyalPointOption; // key in loyalPoint
	private JLabel avaiLableLoyalPoint; // show avaliable loyalPoint
	private JComboBox<String> discountOption;

	private JPanel customerInfoPanel;

	private ISalesDelegate delegate;

	private String[] DropDownSelection;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public SalesPanel(ISalesDelegate delegate) {
		this.delegate = delegate;
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initSalePanel();
		// initButtonPanel();

	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/
	private void initSalePanel() {
		initProductTable();
		initCustomerInfoPabel();
	}

	private void initProductTable() {
		String[] headers = { "code", "Name", "price" };
		String data[][] = {};
		add(getScrollPaneWithTable(data, headers), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
	}

	private GridBagConstraints getConstraint(int xPosition, int yPosition) {
		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.fill = GridBagConstraints.BOTH;
		gridBagConstraint.insets = new Insets(10, 20, 10, 20);
		gridBagConstraint.gridx = xPosition;
		gridBagConstraint.gridy = yPosition;
		return gridBagConstraint;
	}

	private void initCustomerInfoPabel() {
		customerInfoPanel = new JPanel();
		Border border = customerInfoPanel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		customerInfoPanel.setBorder(new CompoundBorder(border, margin));
		customerInfoPanel.setBackground(Color.WHITE);
		customerInfoPanel.setLayout(new GridBagLayout());
		customerInfoPanel.setPreferredSize(new Dimension(400, 200));
		customerInfoPanel.setMaximumSize(new Dimension(400, 250));
		customerInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		customerInfoPanel.add(new JLabel("member:"), getConstraint(0, 0));
		memberOption = new JLabel("Public");
		memberOption.setForeground(Color.BLUE);
		customerInfoPanel.add(memberOption,getConstraint(1,0));
		customerInfoPanel.add(new JLabel("discount:"),getConstraint(0,1));
		discountOption = new JComboBox<String>();
		discountOption.setForeground(Color.BLUE);
		customerInfoPanel.add(discountOption,getConstraint(1,1));
		customerInfoPanel.add(new JLabel("AvailableLoyalPoint:"),getConstraint(0,2));
		avaiLableLoyalPoint = new JLabel("0");
		customerInfoPanel.add(avaiLableLoyalPoint,getConstraint(1,2));
		customerInfoPanel.add(new JLabel("loyalPoint:"),getConstraint(0,3));
		LoyalPointOption = new JTextField(5);
		customerInfoPanel.add(LoyalPointOption,getConstraint(1,3));
		add(customerInfoPanel, BorderLayout.NORTH);
		initButtonEvent();
		// FIXME query for eligibility discount
		String[] activeDiscount = { "Holiday", "Halloween", "Spring fesitive" };
		refreshDropDownData(activeDiscount);
	}

	// private void initButtonPanel() {
	// buttonPanel = new JPanel();
	// buttonPanel.setLayout(new FlowLayout());
	//
	// addProductButton = initImageButton("Resources/add_icon.png",
	// ViewConstants.Labels.STR_ADD_SALESPRODUCT);
	// checkOutButton = initImageButton("Resources/cart_icon.png",
	// ViewConstants.Labels.STR_CHECKOUT_SALES);
	// cancelButton = initImageButton("Resources/delete_icon.png",
	// ViewConstants.Labels.STR_CANCEL_SALES);
	// // addProductButton = new JButton(new
	// // ImageIcon("Resources/add_icon.png"));
	// // checkOutButton = new JButton(new
	// // ImageIcon("Resources/edit_icon.png"));
	// // cancelButton = new JButton(new
	// // ImageIcon("Resources/delete_icon.png"));
	// //
	// // // setting border to empty
	// // addProductButton.setBorder(BorderFactory.createEmptyBorder());
	// // checkOutButton.setBorder(BorderFactory.createEmptyBorder());
	// // cancelButton.setBorder(BorderFactory.createEmptyBorder());
	// //
	// // addProductButton.setPreferredSize(new Dimension(70, 70));
	// // checkOutButton.setPreferredSize(new Dimension(70, 70));
	// // cancelButton.setPreferredSize(new Dimension(70, 70));
	// // buttonPanel.add(addProductButton);
	// buttonPanel.add(addProductButton);
	// buttonPanel.add(checkOutButton);
	// buttonPanel.add(cancelButton);
	// add(buttonPanel, "South");
	// initButtonEvent();
	// }

	// /**
	// * imageButton init here
	// *
	// * @param imageUrl
	// * @param btnText
	// * @return
	// */
	// private JButton initImageButton(String imageUrl, String btnText) {
	// ImageIcon icon = new ImageIcon(imageUrl);
	// JButton btn = new JButton(btnText);
	// btn.setIcon(icon);
	// btn.setHorizontalTextPosition(JLabel.CENTER);
	// btn.setVerticalTextPosition(JLabel.BOTTOM);
	// btn.setBorderPainted(false);
	// btn.setContentAreaFilled(false);
	// btn.setFocusPainted(false);
	// btn.setOpaque(false);
	// return btn;
	// }

	private void refreshDropDownData(String[] str) {
		for (int i = 0; i < str.length; i++) {
			discountOption.addItem(str[i]);
		}
	}

	/**
	 * all the SalePanel button`s event init here
	 */
	private void initButtonEvent() {
		memberOption.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				delegate.LoginMember();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public void onLoginMember(String memberName) {
		memberOption.setText(memberName);
	}

	public void onAddProduct(Product product) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { product.getIdentifier(), product.getName(), product.getPrice() });
	}

	public void onCancel() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (table.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	@Override
	protected ActionListener addAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.AddProduct();
			}
		};
	}

	@Override
	protected ActionListener editAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.CheckOut();
			}
		};
	}

	@Override
	protected ActionListener deleteAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.Cancel();
			}
		};
	}

}
