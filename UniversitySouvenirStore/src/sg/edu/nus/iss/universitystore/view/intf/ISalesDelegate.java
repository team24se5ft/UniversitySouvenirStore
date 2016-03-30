package sg.edu.nus.iss.universitystore.view.intf;

import javax.jws.Oneway;

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
	 */
	public void cancel();
	/**
	 * click member btn form the view
	 */
	public void memberIdentification();
	
	/**
	 * Called when the sales panel is visible. 
	 */
	public void onSalesPanelVisible();
}
