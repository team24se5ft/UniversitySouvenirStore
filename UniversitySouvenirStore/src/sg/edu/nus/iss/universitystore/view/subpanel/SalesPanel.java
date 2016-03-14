package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SalesPanel extends JPanel {
	private List productList;

	private JLabel memberOption;// add memberDialog to check member
	private JTextField LoyalPointOption; // key in loyalPoint
	private JLabel avaiLableLoyalPoint; // show avaliable loyalPoint
	private JLabel discountOption;

	private JButton addProductButton;
	private JButton cancelButton;
	private JButton checkOutButton;

	private JPanel buttonPanel;
	private JPanel customerInfoPanel;
	private JPanel saleContentPanel;

	public SalesPanel() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initSalePanel();
		initButtonPanel();

	}

	private void initSalePanel() {
		saleContentPanel = new JPanel();
		productList = new List();
		saleContentPanel.setBackground(Color.WHITE);
		saleContentPanel.setLayout(new BoxLayout(saleContentPanel, BoxLayout.X_AXIS));
//		saleContentPanel.setLayout(new GridLayout(1, 2));
		saleContentPanel.add(productList);
		initCustomerInfoPabel();
		add(saleContentPanel, "Center");
	}

	private void initCustomerInfoPabel() {
		customerInfoPanel = new JPanel();
		customerInfoPanel.setBackground(Color.WHITE);
		customerInfoPanel.setLayout(new GridLayout(6, 6));
		customerInfoPanel.setPreferredSize(new Dimension(400, 300));
		customerInfoPanel.setMaximumSize(new Dimension(400, 300));
		customerInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		customerInfoPanel.add(new JLabel("member:"));
		memberOption = new JLabel("Public");
		memberOption.setForeground(Color.BLUE);
		customerInfoPanel.add(memberOption);
		customerInfoPanel.add(new JLabel("discount:"));
		discountOption = new JLabel("null");
		discountOption.setForeground(Color.BLUE);
		customerInfoPanel.add(discountOption);
		customerInfoPanel.add(new JLabel("AvailableLoyalPoint:"));
		avaiLableLoyalPoint = new JLabel("0");
		customerInfoPanel.add(avaiLableLoyalPoint);
		customerInfoPanel.add(new JLabel("loyalPoint:"));
		LoyalPointOption = new JTextField(5);
		customerInfoPanel.add(LoyalPointOption);
		saleContentPanel.add(customerInfoPanel);
	}

	private void initButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		addProductButton = new JButton("Add Product");
		addProductButton.setPreferredSize(new Dimension(120, 40));
		checkOutButton = new JButton("Check OUT");
		checkOutButton.setPreferredSize(new Dimension(120, 40));
		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(120, 40));
		buttonPanel.add(addProductButton);
		buttonPanel.add(checkOutButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, "South");
	}

}
