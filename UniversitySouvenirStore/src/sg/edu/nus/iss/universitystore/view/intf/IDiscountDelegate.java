package sg.edu.nus.iss.universitystore.view.intf;

public interface IDiscountDelegate {
	
	/***********************************************************/
	// CRUD operations for Discount
	/***********************************************************/
	/**
	 * Method will be called if the edit/delete are tapped without selecting a row of the table.
	 */
	public void rowNotSelected();
	
	/**
	 * Method to add a discount.
	 */
	public void addDiscount();

	/**
	 * Edit a discount.
	 * @param index The selected index of the discount table.
	 */
	public void deleteDiscount(int row);
	
	/**
	 * Delete a discount.
	 * @param index The selected index of the discount table.
	 */
	public void updateDiscount(int row);
}
