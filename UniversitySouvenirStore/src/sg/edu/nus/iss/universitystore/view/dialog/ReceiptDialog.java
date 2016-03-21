package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sg.edu.nus.iss.universitystore.model.Product;

public class ReceiptDialog extends JDialog {
	private List receiptList;
	ArrayList<Product> receiptDataList;

	public ReceiptDialog(JFrame parent, String title, ArrayList<Product> receiptDataList) {
		super(parent, title);
		this.setLayout(new BorderLayout());
		this.setSize(300, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.receiptDataList = receiptDataList;
		initTitlePanel();
		initReceipt();
	}

	private void initTitlePanel() {
		JLabel titleLabel = new JLabel("NUS SOUVENIR STORE");
		titleLabel.setPreferredSize(new Dimension(300, 50));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(titleLabel, "North");
	}

	private void initReceipt() {
		JLabel receiptHeaderLabel = new JLabel();
		receiptHeaderLabel.setLayout(new BorderLayout());
		String header = "Bill\t\tQuantity\t\tPrice";
		JLabel receiptHeader = new JLabel(header);
		receiptHeader.setPreferredSize(new Dimension(300, 60));
		receiptList = new List();
		receiptList.setPreferredSize(new Dimension(300,200));
		for (Product receiptItem : receiptDataList) {
			receiptList.add(receiptItem.getName() + "\t\t" + receiptItem.getPrice());
		}
		receiptHeaderLabel.add(receiptHeader,"North");
		receiptHeaderLabel.add(receiptList,"Center");
		this.add(receiptHeaderLabel, "Center");
	}

}
