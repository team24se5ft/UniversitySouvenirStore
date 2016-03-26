package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.ISalesDelegate;

/**
 * 
 * @author linby
 *
 */
public class SalesPanel extends BaseTablePanel {

	private JLabel memberOption;// add memberDialog to check member
	private JTextField LoyalPointOption; // key in loyalPoint
	private JLabel discountOption; // show current discount
	JLabel availableLabel;
	JLabel avaiLableLoyalPoint;
	JLabel loyalPointLabel;

	private JPanel customerInfoPanel;

	private ISalesDelegate delegate;

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

	/**
	 * salePanel include productTable and customerInfo
	 */
	private void initSalePanel() {
		initProductTable();
		initCustomerInfoPanel();
	}

	/**
	 * init productTable here
	 */
	private void initProductTable() {
		String[] headers = { "Code", "Name", "Price" };
		String data[][] = {};
		add(getScrollPaneWithTable(data, headers), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
	}

	/**
	 * extract gridbaglayout constraints here
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @return
	 */
	private GridBagConstraints getConstraint(int xPosition, int yPosition) {
		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.fill = GridBagConstraints.BOTH;
		gridBagConstraint.ipadx = 80;
		gridBagConstraint.insets = new Insets(10, 20, 10, 20);
		gridBagConstraint.gridx = xPosition;
		gridBagConstraint.gridy = yPosition;
		return gridBagConstraint;
	}

	/**
	 * customer info include
	 */
	private void initCustomerInfoPanel() {
		customerInfoPanel = new JPanel();
		Border border = customerInfoPanel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		customerInfoPanel.setBorder(new CompoundBorder(border, margin));
		customerInfoPanel.setBackground(Color.WHITE);
		customerInfoPanel.setLayout(new GridBagLayout());
		customerInfoPanel.setPreferredSize(new Dimension(500, 200));
		customerInfoPanel.setMaximumSize(new Dimension(500, 250));
		customerInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		memberOption = new JLabel(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL);
		discountOption = new JLabel();
		discountOption.setForeground(Color.BLUE);
		discountOption.setPreferredSize(new Dimension(100, 50));

		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.MEMBER_LABEL), getConstraint(0, 0));
		customerInfoPanel.add(memberOption, getConstraint(1, 0));
		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.DISCOUNT_LABEL), getConstraint(0, 1));
		customerInfoPanel.add(discountOption, getConstraint(1, 1));

		availableLabel = new JLabel(ViewConstants.SalesPanel.AVAILABLE_LOYALPOINT_LABEL);
		avaiLableLoyalPoint = new JLabel("0");
		loyalPointLabel = new JLabel(ViewConstants.SalesPanel.LOYALPOINT_LABEL);
		LoyalPointOption = new JTextField(5);

		customerInfoPanel.add(availableLabel, getConstraint(0, 2));
		customerInfoPanel.add(avaiLableLoyalPoint, getConstraint(1, 2));
		customerInfoPanel.add(loyalPointLabel, getConstraint(0, 3));
		customerInfoPanel.add(LoyalPointOption, getConstraint(1, 3));
		add(customerInfoPanel, BorderLayout.NORTH);
		initButtonEvent();
		toggleLoyalPanel(false);// set loyal panel unavailable in the beginning
	}

	public void toggleLoyalPanel(boolean flag) {
		if (flag) {
			availableLabel.setVisible(true);
			avaiLableLoyalPoint.setVisible(true);
			loyalPointLabel.setVisible(true);
			LoyalPointOption.setVisible(true);
		} else {
			availableLabel.setVisible(false);
			avaiLableLoyalPoint.setVisible(false);
			;
			loyalPointLabel.setVisible(false);
			LoyalPointOption.setVisible(false);
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
				delegate.MemberIdentification();
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
	public void onMemberIdentification(String memberName,String discountCode,String availableloyalPoint) {
		//TODO show highest discount,show availableloyalPoint,show MemberName
		memberOption.setText(memberName);
		discountOption.setText(discountCode);
		avaiLableLoyalPoint.setText(availableloyalPoint);
		toggleLoyalPanel(true);
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
