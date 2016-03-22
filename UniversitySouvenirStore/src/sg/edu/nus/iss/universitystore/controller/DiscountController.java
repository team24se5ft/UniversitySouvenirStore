package sg.edu.nus.iss.universitystore.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.data.DiscountManager;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmationDialog;
import sg.edu.nus.iss.universitystore.view.dialog.DiscountDialog;
import sg.edu.nus.iss.universitystore.view.intf.IDiscountDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.DiscountPanel;

public class DiscountController implements IDiscountDelegate {
	private DiscountPanel discountPanel;
	private DiscountManager discountManager;
	private ArrayList<Discount> discountList;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DiscountController() {
		try {
			discountManager = DiscountManager.getInstance();
			discountList = discountManager.getAllDiscounts();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discountPanel = new DiscountPanel(this);
		discountPanel.updateTable(TableDataUtils.getFormattedDiscountListForTable(discountList),
				TableDataUtils.getHeadersForDiscountTable());
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	public DiscountPanel getDiscountPanel() {
		return discountPanel;
	}

	@Override
	public void addDiscount() {
		// new DiscountDialog((JFrame)
		// SwingUtilities.getWindowAncestor(discountPanel), "AddDiscount",
		// new IDiscountDialogDelegate() {
		//
		// @Override
		// public void onDiscountCallBack(String code, String description,
		// String startDate, String period,
		// String percentage, String eligibilty) {
		// Discount discount = new Discount(code, description, startDate,
		// Integer.valueOf(period),
		// Float.valueOf(percentage), eligibilty);
		// // TODO dataModify
		// discountList.add(discount);
		// // UIupdate
		// discountPanel.updateTable(TableDataUtils.getFormattedDiscountListForTable(discountList),
		// TableDataUtils.getHeadersForDiscountTable());
		// }
		//
		// }).setVisible(true);
		DiscountDialog dlg = new DiscountDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel),
				"addDiscount") {

			@Override
			public boolean onDiscountCallBack(String code, String description, String startDate, String period,
					String percentage, String eligibilty) {
				Discount discount = new Discount(code, description, startDate, Integer.valueOf(period),
						Float.valueOf(percentage), eligibilty);
				// TODO dataModify
				
				try {
					discountManager.addDiscount(discount);
					discountList = discountManager.getAllDiscounts();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// UIupdate
				discountPanel.updateTable(TableDataUtils.getFormattedDiscountListForTable(discountList),
						TableDataUtils.getHeadersForDiscountTable());
				return true;
			}
		};
		dlg.setVisible(true);
	}

	// TODO keep a list of discount in the controller and modify according to
	// given row.
	@Override
	public void deleteDiscount(int row) {
		if (row < 0) {
			return;
		}
		new ConfirmationDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel), "ConfirmDialog",
				"Do u really want to delete row " + (row + 1)) {
			Discount discount = discountList.get(row);
			@Override
			protected boolean confirmClicked() {
				// TODO dataModify
				try {
					discountManager.deleteDiscount(discount.getCode());
					discountList = discountManager.getAllDiscounts();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// UIupdate
				discountPanel.updateTable(TableDataUtils.getFormattedDiscountListForTable(discountList),
						TableDataUtils.getHeadersForDiscountTable());
				return true;
			}
		}.setVisible(true);
	}

	@Override
	public void updateDiscount(int row) {
		if (row < 0) {
			return;
		}
		
		// Get the object at the index
		Discount oldDiscount = discountList.get(row);
		
		DiscountDialog updateDlg = new DiscountDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel),
				"UpdateDiscount") {

			@Override
			public boolean onDiscountCallBack(String code, String description, String startDate, String period,
					String percentage, String eligibilty) {
				Discount newDiscount = new Discount(code, description, startDate, Integer.valueOf(period),
						Float.valueOf(percentage), eligibilty);
				
				try {
					discountManager.updateDiscount(oldDiscount, newDiscount);
					discountList = discountManager.getAllDiscounts();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// UIupdate
				discountPanel.updateTable(TableDataUtils.getFormattedDiscountListForTable(discountList),
						TableDataUtils.getHeadersForDiscountTable());
				return true;
			}
		};
		updateDlg.setDiscountData(discountList.get(row));
		updateDlg.setVisible(true);
	}
}
