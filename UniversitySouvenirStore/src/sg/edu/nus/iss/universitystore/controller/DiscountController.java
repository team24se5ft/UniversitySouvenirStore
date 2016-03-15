package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.view.dialog.AddDiscountDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.AddDiscountDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IDiscountDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.DiscountPanel;

public class DiscountController implements IDiscountDelegate {
	private DiscountPanel discountPanel;

	public DiscountController() {
		discountPanel = new DiscountPanel(this);
	}

	public DiscountPanel getDiscountPanel() {
		return discountPanel;
	}

	@Override
	public void addDiscount() {
		new AddDiscountDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel), "AddDiscount",
				new AddDiscountDialogDelegate() {

					@Override
					public void onAddDiscount(String code, String description, String startDate, String period,
							String percentage, String eligibilty) {
						Discount discount = new Discount(code, description, startDate, Integer.valueOf(period),
								Float.valueOf(percentage), eligibilty);
						// TODO dataModify
						// UIupdate
						discountPanel.onAddDiscount(discount);
					}

				}).setVisible(true);
		;
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
			protected boolean performOkAction() {
				// TODO dataModify
				// UIupdate
				discountPanel.onRemoveDiscount(row);
				return true;
			}
		}.setVisible(true);
	}
}
