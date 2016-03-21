package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.view.dialog.MemberScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ProductScanDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ReceiptDialog;
import sg.edu.nus.iss.universitystore.view.intf.ISalesDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.SalesPanel;

public class SalesController implements ISalesDelegate {
	private SalesPanel salesPanel;
	private ArrayList<Product> productList=new ArrayList<Product>();
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
	 * after click addProduct btn,the function should generate a dialog for product code input
	 */
	@Override
	public void AddProduct() {
		productDialog= new ProductScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "scanProduct"){

			@Override
			public boolean onProductScanResult(String productCode) {
				//TODO need to do product validation here
				if (true) {
					//add query product entity
					Product product=new Product(productCode,"a product","it`s called a product","100","20","10","50");
					productList.add(product);
					//data input product
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
		//generate a receipt
		ReceiptDialog dialog = new ReceiptDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "Receipt", productList);
		dialog.setVisible(true);
	}

	@Override
	public void Cancel() {
		productList.clear();
		salesPanel.onCancel();
	}

	@Override
	public void LoginMember() {
		// TODO Auto-generated method stub
		memberDialog = new MemberScanDialog((JFrame) SwingUtilities.getWindowAncestor(salesPanel), "scanMember"){

			@Override
			public boolean onMemberLogin(String MemberCode) {
				//TODO need to do member validation here
				if (true) {
					//add query member name
					salesPanel.onLoginMember(MemberCode);
					memberDialog.dispose();
					memberDialog.setVisible(false);
				}
				return true;
			}
			
		};
		memberDialog.setVisible(true);
	}

	@Override
	public void ChoseDiscount() {
		// TODO Auto-generated method stub

	}

}
