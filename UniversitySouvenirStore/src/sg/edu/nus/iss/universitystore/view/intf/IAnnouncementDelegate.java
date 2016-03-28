/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.intf;

import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.model.Product;

/**
 * @author Samrat
 *
 */
public interface IAnnouncementDelegate {

	/**
	 * After sale, if the threshold of a product is reached then this method will be called.
	 * @param: The list of products that have gone below threshold level.
	 */
	public void thresholdReached(String[] arrMessages); 
}
