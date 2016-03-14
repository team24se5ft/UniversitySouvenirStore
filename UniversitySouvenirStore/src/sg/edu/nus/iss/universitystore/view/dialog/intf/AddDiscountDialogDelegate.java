package sg.edu.nus.iss.universitystore.view.dialog.intf;

import java.time.LocalDate;

public interface AddDiscountDialogDelegate {
	/**
	 * add discount dialog call back function
	 */
	public void onAddDiscount(String code, String description, String startDate, String period, String percentage,
			String eligibilty);
}