package sg.edu.nus.iss.universitystore.view.intf;

public interface ISalesDelegate {
	
	/**
	 * click add product btn from the view
	 */
	public void addProduct();
	/**
	 * click check out btn form the view
	 */
	public void checkOut();
	/**
	 * click cancel btn form the view
	 * @param row
	 */
	public void cancel(int row);
	/**
	 * click member btn form the view
	 */
	public void memberIdentification();
	/**
	 * invoked when salesPanel visible
	 */
	public void onSalesPanelVisible();
	/**
	 * row not selected
	 */
	public void rowNotSelected();
	
}
