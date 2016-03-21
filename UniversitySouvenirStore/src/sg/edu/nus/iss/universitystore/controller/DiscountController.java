package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmDialog;
import sg.edu.nus.iss.universitystore.view.dialog.DiscountDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.IDiscountDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IDiscountDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.DiscountPanel;

public class DiscountController implements IDiscountDelegate {
	private DiscountPanel discountPanel;
	private ArrayList<Discount> discountList;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DiscountController() {
		discountPanel = new DiscountPanel(this);
		discountList = new ArrayList<Discount>();
		for (int i = 0; i < 5; i++) {
			Discount e=new Discount("Test", "lose my previous code TnT", "06/06", 10, 20, "A");
			discountList.add(e);
		}
		discountPanel.setDiscountTableData(discountList);
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public DiscountPanel getDiscountPanel() {
		return discountPanel;
	}

	@Override
	public void addDiscount() {
		new DiscountDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel), "AddDiscount",
				new IDiscountDialogDelegate() {

					@Override
					public void onDiscountCallBack(String code, String description, String startDate, String period,
							String percentage, String eligibilty) {
						Discount discount = new Discount(code, description, startDate, Integer.valueOf(period),
								Float.valueOf(percentage), eligibilty);
						// TODO dataModify
						discountList.add(discount);
						// UIupdate
						discountPanel.onAddDiscount(discount);
					}

				}, DiscountDialog.ADD_TYPE).setVisible(true);
	}

	// TODO keep a list of discount in the controller and modify according to
	// given row.
	@Override
	public void deleteDiscount(int row) {
		if (row < 0) {
			return;
		}
		new ConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel), "ConfirmDialog",
				"Do u really want to delete row " + (row + 1)) {

			@Override
			protected boolean confirmClicked() {
				// TODO dataModify
				discountList.remove(row);
				// UIupdate
				discountPanel.onRemoveDiscount(row);
				return true;
			}
		}.setVisible(true);
	}

	@Override
	public void updateDiscount(int row) {
		if (row < 0) {
			return;
		}
		DiscountDialog updateDlg=new DiscountDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel), "UpdateDiscount",
				new IDiscountDialogDelegate() {

					@Override
					public void onDiscountCallBack(String code, String description, String startDate, String period,
							String percentage, String eligibilty) {
						Discount discount = new Discount(code, description, startDate, Integer.valueOf(period),
								Float.valueOf(percentage), eligibilty);
						// TODO dataModify
						discountList.remove(row);
						discountList.add(row,discount);
						// UIupdate
						discountPanel.onUpdateDiscount(discount, row);
					}

				}, DiscountDialog.UPDATE_TYPE);
		updateDlg.setDiscountData(discountList.get(row));
		updateDlg.setVisible(true);
	}
}
