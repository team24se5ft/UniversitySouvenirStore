/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.dialog.intf;

/**
 * @author Samrat
 *
 */
public interface IProductDialogDelegate {

	// TODO : Documentation
	public void confirmClicked(String productName, String productDescription, String quantity, String price, String barcodeNumber, String reorderQuantity, String orderQuantity);
}
