/**
 * 
 */
package sg.edu.nus.iss.universitystore.controller;

import sg.edu.nus.iss.universitystore.view.DashboardPanel;

/**
 * @author Samrat
 *
 */
public class DashboardController extends BaseController{

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private DashboardPanel dashboardPanel;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	public DashboardController() {
		dashboardPanel = new DashboardPanel();
	}
	
	/***********************************************************/
	//Setters and getters
	/***********************************************************/
	public DashboardPanel getDashboardPanel() {
		return dashboardPanel;
	}
}
