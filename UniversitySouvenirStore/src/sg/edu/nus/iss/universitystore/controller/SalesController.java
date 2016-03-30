package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.DiscountManager;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.data.MemberManager;
import sg.edu.nus.iss.universitystore.data.TransactionManager;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.TransactionItem;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.dialog.MemberScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ProductScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ReceiptDialog;
import sg.edu.nus.iss.universitystore.view.intf.ISalesDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.SalesPanel;

public class SalesController implements ISalesDelegate {
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private SalesPanel salesPanel;
	private ArrayList<TransactionItem> transactionItemList = new ArrayList<TransactionItem>();
	private Discount currentDiscount;
	private Member currentMember;
	MemberScanDialog memberDialog;
	ProductScanDialog productDialog;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public SalesController() {
		salesPanel = new SalesPanel(this);
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public SalesPanel getSalesPanel() {
		return salesPanel;
	}

	/**
	 * after click addProduct btn,the function should generate a dialog for
	 * product code input
	 */
	@Override
	public void addProduct() {
		productDialog = new ProductScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "scanProduct") {

			@Override
			public boolean onProductScanResult(String productCode, int quantity) {
				// add query product entity
				addProductByBarCode(productCode, quantity);
				return true;
			}

		};
		productDialog.setVisible(true);
	}

	/**
	 * test space in barcode FIXME no invoke from other code,reserve for scanner
	 * function
	 */
	public void scanBarCode() {
		String productCode = "test";
		addProductByBarCode(productCode, 1);

	}

	/**
	 * AddProduct by barCode
	 * 
	 * @param productCode
	 * @param quantity
	 */
	private void addProductByBarCode(String productCode, int quantity) {
		// FIXME the same Item exist in the list need directly add
		try {
			Product product = InventoryManager.getInstance().findProduct(productCode);
			if (product == null) {
				UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR,
						ViewConstants.ValidationMessage.PRODUCT_ID_NotExist, DialogType.WARNING_MESSAGE);
			} else {
				// add transaction Item and show
				TransactionItem item = new TransactionItem(product, quantity);
				boolean isAdd = true;
				// loop to make sure whether there is repeated scanned
				// product,if repeated then increased
				for (int i = 0; i < transactionItemList.size(); i++) {
					if (transactionItemList.get(i).getProduct().getIdentifier()
							.equals(item.getProduct().getIdentifier())) {
						int old_quantity = transactionItemList.get(i).getQuantity();
						transactionItemList.get(i).setQuantity(old_quantity + quantity);
						isAdd = false;
					}
				}
				if (isAdd) {
					transactionItemList.add(item);
				}
				// calculate total and setText
				float total;
				if (currentDiscount == null) {
					total = TransactionManager.getInstance().getTotal(transactionItemList);
				} else {
					total = TransactionManager.getInstance().getTotal(transactionItemList, currentDiscount.getCode());
				}
				// modify UI
				salesPanel.updateTable(TableDataUtils.getFormattedTransactionListForTable(transactionItemList),
						TableDataUtils.getHeadersForTransactionTable());
				salesPanel.setTotal(total);
				// dispose dialog if no exception
				productDialog.dispose();
				productDialog.setVisible(false);
			}
		} catch (Exception e) {
			UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR, e.getMessage(),
					DialogType.WARNING_MESSAGE);
		}
	}

	/**
	 * after click checkout,this function will 1.
	 * 
	 */
	@Override
	public void checkOut() {
		// generate a receipt
		ReceiptDialog dialog = new ReceiptDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "Receipt",
				transactionItemList);
		dialog.setVisible(true);
		// FIXME test to finish
		if (currentMember != null) {
		}
	}

	/**
	 * click one line
	 */
	@Override
	public void cancel() {
		transactionItemList.clear();
		salesPanel.setTotal((float) 0.0);
		salesPanel.updateTable(TableDataUtils.getFormattedTransactionListForTable(transactionItemList),
				TableDataUtils.getHeadersForProductTable());
	}

	/**
	 * after scan memberCode,show memberName,max discount,available loyalPoint
	 * in customInfo Panel
	 */
	@Override
	public void memberIdentification() {
		// TODO Auto-generated method stub
		memberDialog = new MemberScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "scanMember") {

			@Override
			public boolean onMemberIdentification(String MemberCode) {
				// TODO query Member
				refreshSalesData(MemberCode);
				return true;
			}

		};
		memberDialog.setVisible(true);
	}

	@Override
	public void onSalesPanelVisible() {
		if (currentMember != null) {
			refreshSalesData(currentMember.getIdentifier());
		}
	}

	private void refreshSalesData(String Membercode) {
		try {
			Member member = MemberManager.getInstance().getMember(Membercode);
			currentMember = member;
			Discount discount = DiscountManager.getInstance().getDiscount(Membercode);
			currentDiscount = discount;
			if (discount == null) {
				salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList));
				salesPanel.onMemberIdentification(member.getName(), "none discount", "0%",
						String.valueOf(member.getLoyaltyPoints()));
			} else {
				salesPanel.onMemberIdentification(member.getName(), discount.getCode(),
						String.valueOf(discount.getPercentage() + "%"), String.valueOf(member.getLoyaltyPoints()));
				salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList, discount.getCode()));
			}
		} catch (Exception e) {
			UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR, e.getMessage(),
					DialogType.WARNING_MESSAGE);
			try {
				currentMember = null;
				currentDiscount = null;
				salesPanel.onMemberIdentification(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL, "none discount", "0%",
						"0.0");
				salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList));
			} catch (Exception e1) {
				// if error again then cancel transaction
				cancel();
				// better find stack here
				e1.printStackTrace();
			}
		}
	}

}
