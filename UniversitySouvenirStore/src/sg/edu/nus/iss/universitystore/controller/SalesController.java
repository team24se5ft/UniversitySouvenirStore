package sg.edu.nus.iss.universitystore.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.DiscountManager;
import sg.edu.nus.iss.universitystore.data.MemberManager;
import sg.edu.nus.iss.universitystore.exception.MemberNotFound;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.view.dialog.MemberScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ProductScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ReceiptDialog;
import sg.edu.nus.iss.universitystore.view.intf.ISalesDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.SalesPanel;

public class SalesController implements ISalesDelegate {
	private SalesPanel salesPanel;
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<Discount> discountList = new ArrayList<Discount>();
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
	public void AddProduct() {
		productDialog = new ProductScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "scanProduct") {

			@Override
			public boolean onProductScanResult(String productCode) {
				// TODO need to do product validation here
				if (true) {
					// add query product entity
					Product product = new Product(productCode, "a product", "it`s called a product", "100", "20", "10",
							"50");
					productList.add(product);
					// data input product
					salesPanel.onAddProduct(product);
					productDialog.dispose();
					productDialog.setVisible(false);
				}
				return true;
			}

		};
		productDialog.setVisible(true);
	}

	/**
	 * after click checkout,this function will 1.
	 * 
	 */
	@Override
	public void CheckOut() {
		// generate a receipt
		ReceiptDialog dialog = new ReceiptDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "Receipt",
				productList);
		dialog.setVisible(true);
	}

	@Override
	public void Cancel() {
		productList.clear();
		salesPanel.onCancel();
	}

	@Override
	public void MemberIdentification() {
		// TODO Auto-generated method stub
		memberDialog = new MemberScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "scanMember") {

			@Override
			public boolean onMemberIdentification(String MemberCode) {
				// TODO need to do member validation here
				if (true) {
					// TODO query Member
					try {
						Member member = MemberManager.getInstance().getMember(MemberCode);
						if (member == null) {
							UIUtils.showMessageDialog(salesPanel, ViewConstants.ErrorMessages.STR_WARNING,
									ViewConstants.ValidationMessage.memberNotExist, DialogType.WARNING_MESSAGE);
						}else{
							salesPanel.onMemberIdentification(member.getName(),
									DiscountManager.getInstance().getDiscount(MemberCode).getCode(),
									String.valueOf(member.getLoyaltyPoints()));
							memberDialog.dispose();
							memberDialog.setVisible(false);
						}
					} catch (MemberNotFound e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (StoreException e) {
						e.printStackTrace();
					}
				}
				return true;
			}

		};
		memberDialog.setVisible(true);
	}

}
