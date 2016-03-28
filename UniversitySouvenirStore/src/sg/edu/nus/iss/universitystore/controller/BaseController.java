/**
 * 
 */
package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.view.BasePanel;

/**
 * @author Samrat
 *
 */
public class BaseController {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	protected BasePanel basePanel;
	/**
	 * Method to navigate to the dashboard screen.
	 */
	
	/***********************************************************/
	// Protected Methods
	/***********************************************************/
	
	/**
	 * Navigate from any class to the the dashboard.
	 * @param panel The panel that is currently being displayed.
	 */
	protected void navigateToDashboard(JPanel panel) {
		UIUtils.navigateToDashboard(panel);
	}
}
