package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.DiscountManager;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.data.MemberManager;
import sg.edu.nus.iss.universitystore.data.TransactionManager;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.TransactionException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.TransactionItem;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmationDialog;
import sg.edu.nus.iss.universitystore.view.dialog.MemberScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ProductScanDialog;
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
	
	/**
	 * Reference to Inventory Manager
	 */
	private InventoryManager inventoryManager;
	
	/***********************************************************/
	// Constructors
	/***********************************************************/
	public SalesController() {
		salesPanel = new SalesPanel(this);
		refreshSalesData(ViewConstants.Labels.STR_PUBLIC, false);
		try {
			inventoryManager = InventoryManager.getInstance();
		} catch (InventoryException e) {
			UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR, e.getMessage(),
					DialogType.ERROR_MESSAGE);
		}
		
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
		try {
			if(inventoryManager.getAllProducts().size() == 0) {
				UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR, "No products present in the store.",
						DialogType.ERROR_MESSAGE);
			}else {
				productDialog = new ProductScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "Scan Product") {
					@Override
					public boolean onProductScanResult(String productCode, int quantity) {
						// add query product entity
						addProductByBarCode(productCode, quantity);
						return true;
					}
				};
				productDialog.setVisible(true);
			}
		} catch (Exception e) {
			UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR, e.getMessage(),
					DialogType.ERROR_MESSAGE);
		}
		
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
				if (product.getQuantity() < quantity) {
					// validate quantity before check out
					UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.ERROR,
							ViewConstants.ValidationMessage.PRODUCT_QUANTITY_NOTEnough, DialogType.WARNING_MESSAGE);
					return;
				}
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
				if (currentDiscount != null) {
					total = TransactionManager.getInstance().getTotal(transactionItemList, currentDiscount.getCode());
				} else {
					total = TransactionManager.getInstance().getTotal(transactionItemList, null);
				}

				// modify UI
				salesPanel.updateTable(TableDataUtils.getFormattedTransactionItemListForTable(transactionItemList),
						TableDataUtils.getHeadersForTransactionItemTable());
				salesPanel.setTotal(total);
				// dispose dialog if no TransactionException
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
		// ReceiptDialog dialog = new ReceiptDialog((JFrame)
		// SwingUtilities.getWindowAncestor(salesPanel), "Receipt",
		// transactionItemList);
		// dialog.setVisible(true);
		// FIXME test to finish
		ConfirmationDialog dlg = new ConfirmationDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel),
				"Check OUT", "Do you confirm to check out?") {

			@Override
			protected boolean confirmClicked() {
				if (salesPanel.checkOUTable()) {
					try {
						TransactionManager.getInstance().addTransaction(transactionItemList,
								currentDiscount == null ? null : currentDiscount.getCode(), currentMember == null
										? ViewConstants.Labels.STR_PUBLIC : currentMember.getIdentifier());
						// show receipt here
						// clear salesPanel here
						clearSalesPanel();
					} catch (TransactionException e) {
						e.printStackTrace();
					}
				} else {
					UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.WARNING, "no enough payment",
							DialogType.WARNING_MESSAGE);
				}
				return true;
			}

		};
		dlg.setVisible(true);
	}

	private void clearSalesPanel() {
		// clear transaction item list
		transactionItemList.clear();
		// clear calculation part
		salesPanel.clear();
		// clear table update
		salesPanel.updateTable(TableDataUtils.getFormattedTransactionItemListForTable(transactionItemList),
				TableDataUtils.getHeadersForTransactionItemTable());
		// clear memberInfo part
		refreshSalesData(ViewConstants.Labels.STR_PUBLIC, false);
	}

	/**
	 * click one line
	 */
	@Override
	public void cancel(int row) {
		ConfirmationDialog dlg;
		if (row < 0) {
			dlg = new ConfirmationDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "deleteConfirm",
					"Do you want to remove all the productItem") {

				@Override
				protected boolean confirmClicked() {
					transactionItemList.clear();
					salesPanel.setTotal((float) 0.0);
					salesPanel.updateTable(TableDataUtils.getFormattedTransactionItemListForTable(transactionItemList),
							TableDataUtils.getHeadersForTransactionItemTable());
					return true;
				}
			};
		} else {
			dlg = new ConfirmationDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "deleteConfirm",
					"Do you want to remove productItem:" + transactionItemList.get(row).getProduct().getName()) {

				@Override
				protected boolean confirmClicked() {
					try {
						transactionItemList.remove(row);
						if (currentDiscount != null) {
							salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList,
									currentDiscount.getCode()));
						} else {
							salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList, null));
						}
						salesPanel.updateTable(
								TableDataUtils.getFormattedTransactionItemListForTable(transactionItemList),
								TableDataUtils.getHeadersForTransactionItemTable());
					} catch (TransactionException e) {
						e.printStackTrace();
					}
					return true;
				}
			};
		}
		dlg.setVisible(true);
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
				if (MemberCode.length() == 0) {
					refreshSalesData(ViewConstants.Labels.STR_PUBLIC, true);
				} else {
					refreshSalesData(MemberCode, true);
				}
				return true;
			}

		};
		memberDialog.setVisible(true);
	}

	@Override
	public void onSalesPanelVisible() {
		if (currentMember != null) {
			// check whether member Data changed during the transaction
			refreshSalesData(currentMember.getIdentifier(), true);
		} else {
			// previous customer is not a member,purpose of the code is to check
			// whether discount changed
			refreshSalesData(ViewConstants.Labels.STR_PUBLIC, false);
		}
	}

	/**
	 * refresh memberInfo part and calculation part
	 * 
	 * @param Membercode
	 */
	private void refreshSalesData(String memberCode,boolean isShowTip){
		//set member part
		try{
			Member member = MemberManager.getInstance().getMember(memberCode);
			currentMember = member;
			if(member!=null){
				salesPanel.onMemberIdentification(member.getName(),String.valueOf(member.getLoyaltyPoints()) );
			}else{
				salesPanel.onMemberIdentification(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL,"0");
			}
		}catch(MemberException e){
			salesPanel.onMemberIdentification(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL,"0");
		}
		//set discount part
		try{
			Discount discount;
			if(currentMember!=null){
				discount = DiscountManager.getInstance().getDiscount(memberCode);
			}else{
				discount = DiscountManager.getInstance().getDiscount(ViewConstants.SalesPanel.MEMBER_OPTION_LABEL);
			}
			currentDiscount = discount;
			if(discount!=null){
				salesPanel.onSetDiscount(discount.getCode(), discount.getPercentage()+"%");
				salesPanel.setTotal((TransactionManager.getInstance().getTotal(transactionItemList, discount.getCode())));
			}else{
				salesPanel.onSetDiscount("none discount", "0.0%");
				salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList, null));
			}
		}catch(DiscountException e){
			salesPanel.onSetDiscount("none discount", "0.0%");
			try {
				salesPanel.setTotal(TransactionManager.getInstance().getTotal(transactionItemList, null));
			} catch (TransactionException e1) {
				e1.printStackTrace();
			}
		}catch(TransactionException e){
			if (isShowTip) {
				UIUtils.showMessageDialog(salesPanel, ViewConstants.StatusMessage.WARNING, e.getMessage(),
						DialogType.WARNING_MESSAGE);
			}
			e.printStackTrace();
		}
	}
	

}
