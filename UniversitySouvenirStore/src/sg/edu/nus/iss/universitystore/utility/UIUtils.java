/**
 * 
 */
package sg.edu.nus.iss.universitystore.utility;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.MainFrame;
import sg.edu.nus.iss.universitystore.controller.DashboardController;
import sg.edu.nus.iss.universitystore.controller.LoginController;

/**
 * @author Samrat
 *
 */

public class UIUtils {
	
	/***********************************************************/
	// Static variables
	/***********************************************************/
	private static DashboardController dashboardController;
	
	/***********************************************************/
	//Enums
	/***********************************************************/
	public enum DialogType{
		ERROR_MESSAGE,/** Used for error messages. */
		INFORMATION_MESSAGE,/** Used for information messages. */
		WARNING_MESSAGE,/** Used for warning messages. */
		QUESTION_MESSAGE,/** Used for questions. */
		PLAIN_MESSAGE;/** No icon is used. */
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	
	/**
	 * Method will display JOptionPane on the frame on which the panel is added to.
	 * @param panel The panel whose parent frame will be used to display the Option Pane.
	 * @param title The title of the dialog
	 * @param message The message of the dialog
	 * @param dialogType The dialog type.
	 */
	public static void showMessageDialog(JPanel panel,String title, String message ,DialogType dialogType) {
		int messageType;
		switch (dialogType) {
		case ERROR_MESSAGE:
			messageType = JOptionPane.ERROR_MESSAGE;
			break;
		case INFORMATION_MESSAGE:
			messageType = JOptionPane.INFORMATION_MESSAGE;
			break;
		case WARNING_MESSAGE:
			messageType = JOptionPane.WARNING_MESSAGE;
			break;
		case QUESTION_MESSAGE:
			messageType = JOptionPane.QUESTION_MESSAGE;
			break;
		default://Handles PLAIN_MESSAGE
			messageType = JOptionPane.PLAIN_MESSAGE;
			break;
		}
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
		JOptionPane.showMessageDialog(topFrame, message,title,
				messageType);
	}
	
	
	public static void navigateToDashboard(JPanel panel){
		MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(panel);
		JPanel mainPanel = mainFrame.getMainPanel();
		mainPanel.removeAll();
		dashboardController = new DashboardController();
		mainPanel.add(dashboardController.getDashboardPanel());
		mainFrame.getContentPane().invalidate();
		mainFrame.getContentPane().validate();
	}
	
	public static DashboardController getDashBoardController() {
		return dashboardController;
	}
	
	public  static void navigateToLogin(JPanel panel) {
		MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(panel);
		JPanel mainPanel = mainFrame.getMainPanel();
		mainPanel.removeAll();
		LoginController loginController = new LoginController();
		mainPanel.add(loginController.getLoginPanel());
		mainFrame.getContentPane().invalidate();
		mainFrame.getContentPane().validate();
	}
}
