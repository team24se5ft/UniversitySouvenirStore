/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.intf;

/**
 * @author Samrat
 *
 */
public interface IReportDelegate {

	/**
	 * Method will be called when the report panel is visible.
	 */
	public void reportPanelVisible();
	/**
	 * Method will be called when the transaction query is need
	 */
	public void onTransactionQuery(String startDate,String endDate);
}
