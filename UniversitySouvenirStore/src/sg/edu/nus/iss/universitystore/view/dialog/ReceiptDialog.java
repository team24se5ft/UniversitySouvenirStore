package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.Product;

public class ReceiptDialog extends JDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private List receiptList;
	ArrayList<Product> receiptDataList;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	/**
	 * @param parent
	 *            Parent component for this dialog
	 * @param title
	 *            dialog title
	 * @param receiptDataList
	 *            product data List
	 */
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

	/**
	 * init North part of panel
	 */
	private void initTitlePanel() {
		JLabel titleLabel = new JLabel(ViewConstants.DialogHeaders.STORE_HEADER);
		titleLabel.setPreferredSize(new Dimension(300, 50));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(titleLabel, ViewConstants.DialogHeaders.NORTH);
	}

	/**
	 * init receipt table
	 */
	private void initReceipt() {
		JLabel receiptHeaderLabel = new JLabel();
		receiptHeaderLabel.setLayout(new BorderLayout());
		String header = ViewConstants.DialogHeaders.HEADER;
		JLabel receiptHeader = new JLabel(header);
		receiptHeader.setPreferredSize(new Dimension(300, 60));
		receiptList = new List();
		receiptList.setPreferredSize(new Dimension(300, 200));
		for (Product receiptItem : receiptDataList) {
			receiptList.add(receiptItem.getName() + "\t\t" + receiptItem.getPrice());
		}
		receiptHeaderLabel.add(receiptHeader, ViewConstants.DialogHeaders.NORTH);
		receiptHeaderLabel.add(receiptList, ViewConstants.DialogHeaders.CENTER);
		this.add(receiptHeaderLabel, ViewConstants.DialogHeaders.CENTER);
	}

}
