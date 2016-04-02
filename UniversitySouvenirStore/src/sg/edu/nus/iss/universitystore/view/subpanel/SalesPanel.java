package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.ISalesDelegate;

/**
 * 
 * @author linby
 *
 */
public class SalesPanel extends BaseTablePanel {
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private JButton memberOption;// add memberDialog to check member
	private JLabel discountOption; // show current discount
	JLabel discountPercentage; // show current discount percentage
	JLabel availableLabel;
	JLabel avaiLableLoyalPoint;

	private JLabel totalText; // automatic calculate the total cost
	private JTextField cashText; // key in received cash
	private JTextField LoyalPointText; // key in limit loyalPoint
	private JLabel ChangeText; // automatic calculate the changes

	private JPanel customerInfoPanel;

	private ISalesDelegate delegate;

	// used for changeMember tip switch
	private String memberText = ViewConstants.SalesPanel.MEMBER_OPTION_LABEL;

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
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				// Inform the controller that the panel is visible.
				delegate.onSalesPanelVisible();
			}
		});
		// Update the middle button
		ImageIcon imageIcon = new ImageIcon("Resources/checkout_icon.png");
		Image img = imageIcon.getImage();
		Image newimg = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		btnEdit.setIcon(newIcon);
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
		// FIXME determine product columns
		// String[] headers = { "Code", "Name", "Price" };
		String data[][] = {};
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jpanel.add(getScrollPaneWithTable(data, TableDataUtils.getHeadersForTransactionItemTable()),
				BorderLayout.CENTER);
		jpanel.add(getCalculationPanel(), BorderLayout.SOUTH);
		add(jpanel, BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
		initCalculationEvent();
	}

	/**
	 * 1.set loyalPoint and cash can only be input as digit 2.make loyalPoint
	 * not over avaliablePoint
	 */
	private void initCalculationEvent() {
		LoyalPointText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				// only digit
				if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9) {
					e.consume();
					return;
				}
				// calculate whether loyalPoint will over available Point
				int loyalPoint = LoyalPointText.getText().isEmpty() ? e.getKeyChar() - 48
						: Integer.valueOf(String.valueOf(LoyalPointText.getText() + e.getKeyChar()));
				int availableLoyalPoint = avaiLableLoyalPoint.getText().isEmpty() ? 0
						: Integer.valueOf(avaiLableLoyalPoint.getText());
				if (availableLoyalPoint < loyalPoint) {
					UIUtils.showMessageDialog(SalesPanel.this, ViewConstants.StatusMessage.ERROR,
							ViewConstants.SalesPanel.INSUFFICIENT_LOYALTY_POINTS, DialogType.ERROR_MESSAGE);
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				refreshCalculation();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		cashText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				// only digit
				if (keyChar < KeyEvent.VK_0 && keyChar != KeyEvent.VK_PERIOD
						|| keyChar > KeyEvent.VK_9 && keyChar != KeyEvent.VK_PERIOD) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				refreshCalculation();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * calculate the changeText and refreshUI
	 * 
	 * @param cashText
	 * @param totalText
	 * @param LoyalPointText
	 */
	private void refreshCalculation() {
		float cash = cashText.getText().isEmpty() ? 0 : Float.valueOf(cashText.getText());
		float total = totalText.getText().isEmpty() ? 0 : Float.valueOf(totalText.getText());
		float loyal = LoyalPointText.getText().isEmpty() ? 0 : Float.valueOf(LoyalPointText.getText());
		double change = cash + loyal - Constants.Data.Transaction.LOYALTY_POINTS_TO_CURRENCY_CONVERSION_RATE * total;
		BigDecimal b = new BigDecimal(change);
		double result = b.setScale(3, RoundingMode.HALF_UP).doubleValue();
		if (result < 0) {
			ChangeText.setForeground(Color.red);
		} else {
			ChangeText.setForeground(new Color(45, 117, 69));// dark green color
		}
		ChangeText.setText(String.valueOf(result));
	}

	/**
	 * show total cash loyalPoint change
	 * 
	 * @return
	 */
	private JPanel getCalculationPanel() {
		totalText = new JLabel("0.0");
		cashText = new JTextField();
		LoyalPointText = new JTextField();
		ChangeText = new JLabel("0.0");

		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridBagLayout());
		jpanel.add(new JLabel(ViewConstants.SalesPanel.TOTAL_LABEL), getConstraint(0, 0));
		jpanel.add(new JLabel(ViewConstants.SalesPanel.CASH_LABEL), getConstraint(2, 0));
		jpanel.add(new JLabel(ViewConstants.SalesPanel.LOYALPOINT_LABEL), getConstraint(0, 1));
		jpanel.add(new JLabel(ViewConstants.SalesPanel.CHANGE_LABEL), getConstraint(2, 1));

		jpanel.add(totalText, getConstraint(1, 0));
		jpanel.add(cashText, getConstraint(3, 0));
		jpanel.add(LoyalPointText, getConstraint(1, 1));
		jpanel.add(ChangeText, getConstraint(3, 1));
		return jpanel;
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
		gridBagConstraint.ipadx = 60;
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
		customerInfoPanel.setPreferredSize(new Dimension(500, 190));
		customerInfoPanel.setMaximumSize(new Dimension(500, 190));
		customerInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		memberOption = new JButton(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL);
		memberOption.setPreferredSize(new Dimension(80, 40));
		discountOption = new JLabel(ViewConstants.SalesPanel.NONE_DISCOUNT);

		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.MEMBER_LABEL), getConstraint(0, 0));
		customerInfoPanel.add(memberOption, getConstraint(1, 0));
		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.DISCOUNT_LABEL), getConstraint(0, 1));
		customerInfoPanel.add(discountOption, getConstraint(1, 1));

		discountPercentage = new JLabel("0%");

		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.DISCOUNT_PERCENTAGE_LABEL), getConstraint(0, 2));
		customerInfoPanel.add(discountPercentage, getConstraint(1, 2));

		availableLabel = new JLabel(ViewConstants.SalesPanel.AVAILABLE_LOYALPOINT_LABEL);
		avaiLableLoyalPoint = new JLabel("0.0");

		customerInfoPanel.add(availableLabel, getConstraint(0, 3));
		customerInfoPanel.add(avaiLableLoyalPoint, getConstraint(1, 3));

		add(customerInfoPanel, BorderLayout.NORTH);
		initButtonEvent();
		toggleLoyalPanel(false);// set loyal panel unavailable in the beginning
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
				delegate.memberIdentification();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				memberOption.setText(memberText);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				memberOption.setText(ViewConstants.SalesPanel.CHANGE_MEMBER);
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

	/**
	 * only showing after memberIdentificaiton
	 * 
	 * @param flag
	 */
	private void toggleLoyalPanel(boolean flag) {
		if (flag) {
			availableLabel.setVisible(true);
			avaiLableLoyalPoint.setVisible(true);
		} else {
			availableLabel.setVisible(false);
			avaiLableLoyalPoint.setVisible(false);
		}
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public void onMemberIdentification(String memberName, String availableloyalPoint) {
		// TODO show highest discount,show availableloyalPoint,show MemberName
		memberOption.setText(memberName);
		avaiLableLoyalPoint.setText(availableloyalPoint);
		memberText = memberName;
		toggleLoyalPanel(true);
	}

	/**
	 * for discount input in
	 * 
	 * @param discountCode
	 * @param off
	 */
	public void onSetDiscount(String discountCode, String off) {
		discountOption.setText(discountCode);
		discountPercentage.setText(off);
		refreshCalculation();
	}

	/**
	 * show total cost
	 * 
	 * @param total
	 */
	public void setTotal(float total) {
		totalText.setText(String.valueOf(total));
		refreshCalculation();
	}

	/**
	 * gain total for receipt
	 * 
	 * @return
	 */
	public String[] getTotal() {
		String[] total = { totalText.getText(), cashText.getText().isEmpty() ? "0.0" : cashText.getText(),
				ChangeText.getText(), LoyalPointText.getText().isEmpty() ? "0" : LoyalPointText.getText() };
		return total;
	}

	public void clear() {
		totalText.setText("0.0");
		cashText.setText("");
		ChangeText.setText("0.0");
		LoyalPointText.setText("");
	}

	/**
	 * check whether the customer pay enough money
	 * 
	 * @return
	 */
	public boolean checkOUTable() {
		if (Float.valueOf(ChangeText.getText()) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	protected ActionListener addAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addProduct();
			}
		};
	}

	@Override
	protected ActionListener editAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.checkOut();
			}
		};
	}

	@Override
	protected ActionListener deleteAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0) {
					delegate.rowNotSelected();
				} else {
					delegate.cancel(table.getSelectedRow());
				}
			}
		};
	}

}
