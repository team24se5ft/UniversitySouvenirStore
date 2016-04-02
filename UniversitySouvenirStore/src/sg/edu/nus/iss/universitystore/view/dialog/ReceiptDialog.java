package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.TransactionItem;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;

public class ReceiptDialog extends JDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private JTable productTable;
	private ArrayList<TransactionItem> receiptDataList;
	private String totalString;
	private String loyalString;
	private String cashString;
	private String changeSring;

	private String currentDiscountName;
	private String currentMemberId;

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
	public ReceiptDialog(JFrame parent, ArrayList<TransactionItem> receiptDataList, String[] calculations,
			String currentDiscountPercentage, String currentMemberId) {
		super(parent, ViewConstants.Receipt.RECEIPT_TITLE);
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.setSize(400, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.receiptDataList = receiptDataList;
		this.totalString = calculations[0];
		this.cashString = calculations[1];
		this.changeSring = calculations[2];
		this.loyalString = calculations[3];
		this.currentDiscountName = currentDiscountPercentage;
		this.currentMemberId = currentMemberId;
		initTitlePanel();
		initReceipt();
	}

	/**
	 * init North part of panel
	 */
	private void initTitlePanel() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel(ViewConstants.DialogHeaders.STORE_HEADER);
		titlePanel.add(titleLabel);
		titlePanel.setPreferredSize(new Dimension(400, 30));

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(new JLabel(ViewConstants.DialogHeaders.MEMBER_ID_HEADER + currentMemberId), BorderLayout.WEST);
		northPanel.add(titlePanel);
		northPanel.add(infoPanel);
		this.add(northPanel, BorderLayout.NORTH);

	}

	protected GridBagConstraints getConstraints(int xPosition, int yPosition) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints.gridx = xPosition;
		gridBagConstraints.gridy = yPosition;
		return gridBagConstraints;
	}

	/**
	 * init receipt table
	 */
	private void initReceipt() {
		JPanel receiptHeaderPanel = new JPanel();
		receiptHeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// JLabel receiptHeader = new JLabel(header);
		// receiptHeader.setPreferredSize(new Dimension(300, 60));
		productTable = new JTable();
		productTable.setPreferredSize(new Dimension(400, 320));
		String[][] data = null;
		DefaultTableModel tableModel = new DefaultTableModel(data, null) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		productTable.setModel(tableModel);
		productTable.setPreferredScrollableViewportSize(productTable.getPreferredSize());
		JScrollPane jsp = new JScrollPane(productTable);
		// receiptHeaderPanel.add(receiptHeader,
		// ViewConstants.DialogHeaders.NORTH);
		receiptHeaderPanel.add(jsp, BorderLayout.CENTER);
		this.add(receiptHeaderPanel, BorderLayout.CENTER);

		this.add(getReceiptTailPanel(), BorderLayout.SOUTH);
		updateTable();
	}

	/**
	 * update receipt table
	 */
	public void updateTable() {
		((DefaultTableModel) productTable.getModel()).setDataVector(
				TableDataUtils.getFormattedTransactionItemListForTable(receiptDataList),
				TableDataUtils.getHeadersForTransactionItemTable());
	}

	public JPanel getReceiptTailPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		jPanel.setMaximumSize(new Dimension(100, 50));
		jPanel.add(getAlignPanel(ViewConstants.DialogHeaders.DISCOUNT_NAME_HEADER, currentDiscountName));
		jPanel.add(getAlignPanel(ViewConstants.DialogHeaders.LOYAL_POINT_HEADER, loyalString));
		jPanel.add(getAlignPanel(ViewConstants.DialogHeaders.TOTAL_HEADER, totalString));
		jPanel.add(getAlignPanel(ViewConstants.DialogHeaders.CASH_HEADER, cashString));
		jPanel.add(getAlignPanel(ViewConstants.DialogHeaders.CHANGE_HEADER, changeSring));
		return jPanel;
	}

	public JPanel getAlignPanel(String leftTitle, String rightDigit) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		JLabel leftLabel = new JLabel();
		leftLabel.setText(leftTitle);
		jPanel.add(leftLabel, BorderLayout.WEST);

		JLabel rightLable = new JLabel();
		rightLable.setText(rightDigit);
		jPanel.add(rightLable, BorderLayout.EAST);
		return jPanel;

	}

}
