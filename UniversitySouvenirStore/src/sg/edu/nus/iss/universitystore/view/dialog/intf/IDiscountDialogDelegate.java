package sg.edu.nus.iss.universitystore.view.dialog.intf;

public interface IDiscountDialogDelegate {
	/**
	 * add and update discount dialog call back function
	 */
	public void onDiscountCallBack(String code, String description, String startDate, String period, String percentage,
			String eligibilty);
}
