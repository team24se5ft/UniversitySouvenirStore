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
	 * test space in barcode
	 */
	public void scanBarCode() {
		String productCode = "test";
		addProductByBarCode(productCode, 1);

	}

	/**
	 * AddProduct by barCode in this case
	 * 
	 * @param productCode
	 * @param quantity
	 */
	private void addProductByBarCode(String productCode, int quantity) {
		try {
			Product product = InventoryManager.getInstance().findProduct(productCode);
			if (product == null) {
				UIUtils.showMessageDialog(salesPanel, ViewConstants.ErrorMessages.STR_WARNING,
						ViewConstants.ValidationMessage.PRODUCT_ID_NotExist, DialogType.WARNING_MESSAGE);
			} else {
				// add transaction Item and show
				TransactionItem item = new TransactionItem(product, quantity);
				transactionItemList.add(item);
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
			UIUtils.showMessageDialog(salesPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
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
				try {
					Member member = MemberManager.getInstance().getMember(MemberCode);
					Discount discount = DiscountManager.getInstance().getDiscount(MemberCode);
					if (discount == null) {
						salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList));
						salesPanel.onMemberIdentification(member.getName(), "none discount", "0%",
								String.valueOf(member.getLoyaltyPoints()));
					} else {
						salesPanel.onMemberIdentification(member.getName(), discount.getCode(),
								String.valueOf(discount.getPercentage()+"%"), String.valueOf(member.getLoyaltyPoints()));
						salesPanel.setTotal(
								TransactionManager.getInstance().getTotal(transactionItemList, discount.getCode()));
					}
					memberDialog.dispose();
					memberDialog.setVisible(false);
				} catch (Exception e) {
					UIUtils.showMessageDialog(salesPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
				}
				return true;
			}

		};
		memberDialog.setVisible(true);
	}

}
