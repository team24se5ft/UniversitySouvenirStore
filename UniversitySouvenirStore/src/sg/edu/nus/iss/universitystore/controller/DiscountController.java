package sg.edu.nus.iss.universitystore.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.DiscountManager;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
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
			e.printStackTrace();
			UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
					DialogType.WARNING_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
					DialogType.WARNING_MESSAGE);
		} catch (StoreException e) {
			UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
					DialogType.WARNING_MESSAGE);
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
		DiscountDialog dlg = new DiscountDialog((JFrame) SwingUtilities.getWindowAncestor(discountPanel),
				"addDiscount") {

			@Override
			public boolean onDiscountCallBack(String code, String description, String startDate, String period,
					String percentage, String eligibilty) {
				boolean flag = false;
				Discount discount = new Discount(code, description, startDate, Integer.valueOf(period),
						Float.valueOf(percentage), eligibilty);
				if (code.length() == 0 && description.length() == 0 && percentage.length() == 0
						&& startDate.length() == 0) {
					UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING,
							"please fullfill all the required information.", DialogType.WARNING_MESSAGE);
					return false;
				}
				try {
					flag = discountManager.addDiscount(discount);
					discountList = discountManager.getAllDiscounts();
				} catch (NumberFormatException e) {
					e.printStackTrace();
					UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
					return false;
				} catch (IOException e) {
					UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
					e.printStackTrace();
					return false;
				}
				return isUpdateUI(flag);
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
				boolean flag = false;
				// TODO dataModify
				try {
					flag = discountManager.deleteDiscount(discount.getCode());
					discountList = discountManager.getAllDiscounts();
				} catch (NumberFormatException e) {
					UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
					e.printStackTrace();
				}
				return isUpdateUI(flag);
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
				boolean flag = false;
				Discount newDiscount = new Discount(code, description, startDate, Integer.valueOf(period),
						Float.valueOf(percentage), eligibilty);
				try {
					flag = discountManager.updateDiscount(oldDiscount, newDiscount);
					discountList = discountManager.getAllDiscounts();
				} catch (IOException e) {
					UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING, e.getMessage(),
							DialogType.WARNING_MESSAGE);
					e.printStackTrace();
				}
				return isUpdateUI(flag);
			}
		};
		updateDlg.setDiscountData(discountList.get(row));
		updateDlg.setVisible(true);
	}
	/**
	 * judge whether updateUI according to flag
	 * @param flag
	 * @return
	 */
	private boolean isUpdateUI(boolean flag) {
		if (flag) {
			discountPanel.updateTable(TableDataUtils.getFormattedDiscountListForTable(discountList),
					TableDataUtils.getHeadersForDiscountTable());
			return true;
		} else {
			UIUtils.showMessageDialog(discountPanel, ViewConstants.ErrorMessages.STR_WARNING,
					ViewConstants.ValidationMessage.validatedFailed, DialogType.WARNING_MESSAGE);
			return false;
		}
	}
}
