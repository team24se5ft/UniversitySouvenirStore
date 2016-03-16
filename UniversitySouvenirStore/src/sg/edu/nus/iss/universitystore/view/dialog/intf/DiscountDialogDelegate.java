package sg.edu.nus.iss.universitystore.view.dialog.intf;

public interface DiscountDialogDelegate {
	/**
	 * add discount dialog call back function
	 */
	public void onDiscountCallBack(String code, String description, String startDate, String period, String percentage,
			String eligibilty);
}
