package sg.edu.nus.iss.universitystore.view.intf;

public interface IDiscountDelegate {
	/**
	 * show dialog to add a discount
	 */
	public void addDiscount();

	/**
	 * 
	 * @param row
	 *            convert row to make sure delete selected row
	 */
	public void deleteDiscount(int row);
}
