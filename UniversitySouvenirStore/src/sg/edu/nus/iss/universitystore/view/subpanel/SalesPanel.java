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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
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
	JLabel discountPercentage; //show current discount percentage
	JLabel availableLabel;
	JLabel avaiLableLoyalPoint;

	private JLabel totalText; // automatic calculate the total cost
	private JTextField cashText; // key in received cash
	private JTextField LoyalPointText; // key in limit loyalPoint
	private JLabel ChangeText; // automatic calculate the changes

	private JPanel customerInfoPanel;

	private ISalesDelegate delegate;

	private String memberText = "PUBLIC";

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
		// FIXME determine product columns
		// String[] headers = { "Code", "Name", "Price" };
		String data[][] = {};
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jpanel.add(getScrollPaneWithTable(data, TableDataUtils.getHeadersForTransactionTable()), BorderLayout.CENTER);
		jpanel.add(getCalculationPanel(), BorderLayout.SOUTH);
		add(jpanel, BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
		initCalculationEvent();
	}

	/**
	 * 1.set loyalPoint and cash can only be input as digit
	 * 
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
				//calculate whether loyalPoint will over available Point
				int loyalPoint = LoyalPointText.getText().isEmpty() ? e.getKeyChar() - 48
						: Integer.valueOf(String.valueOf(LoyalPointText.getText() + e.getKeyChar()));
				int availableLoyalPoint = avaiLableLoyalPoint.getText().isEmpty() ? 0
						: Integer.valueOf(avaiLableLoyalPoint.getText());
				if (availableLoyalPoint < loyalPoint) {
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
				if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9) {
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
		ChangeText.setText(String.valueOf(cash + loyal - total));
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
		jpanel.add(new JLabel("Total:"), getConstraint(0, 0));
		jpanel.add(new JLabel("Cash:"), getConstraint(2, 0));
		jpanel.add(new JLabel("LoyalPoint:"), getConstraint(0, 1));
		jpanel.add(new JLabel("Change:"), getConstraint(2, 1));

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
		customerInfoPanel.setPreferredSize(new Dimension(500, 200));
		customerInfoPanel.setMaximumSize(new Dimension(500, 250));
		customerInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		memberOption = new JButton(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL);
		memberOption.setPreferredSize(new Dimension(80, 40));
		discountOption = new JLabel(ViewConstants.SalesPanel.NONE_DISCOUNT);

		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.MEMBER_LABEL), getConstraint(0, 0));
		customerInfoPanel.add(memberOption, getConstraint(1, 0));
		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.DISCOUNT_LABEL), getConstraint(0, 1));
		customerInfoPanel.add(discountOption, getConstraint(1, 1));

		discountPercentage=new JLabel("0%");
		
		customerInfoPanel.add(new JLabel(ViewConstants.SalesPanel.DISCOUNT_PERCENTAGE_LABEL), getConstraint(0, 2));
		customerInfoPanel.add(discountPercentage, getConstraint(1, 2));
		
		availableLabel = new JLabel(ViewConstants.SalesPanel.AVAILABLE_LOYALPOINT_LABEL);
		avaiLableLoyalPoint = new JLabel("0");

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
				memberOption.setText("change member");
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
	public void onMemberIdentification(String memberName, String discountCode,String off, String availableloyalPoint) {
		// TODO show highest discount,show availableloyalPoint,show MemberName
		memberOption.setText(memberName);
		discountOption.setText(discountCode);
		avaiLableLoyalPoint.setText(availableloyalPoint);
		discountPercentage.setText(off);
		memberText = memberName;
		toggleLoyalPanel(true);
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
				delegate.cancel();
			}
		};
	}

}
