
package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.controller.DiscountController;
import sg.edu.nus.iss.universitystore.controller.MemberController;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.view.intf.DashBoardOptionChangeDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.DiscountPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.MemberPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.ReportPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.SalesPanel;

/**
 * @author Samrat
 *
 */
public class DashboardPanel extends JPanel implements DashBoardOptionChangeDelegate {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	private DashboardOptionsPanel optionPanel;
	private JPanel mainContentPanel;
	
	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DashboardPanel() {
		setLayout(new BorderLayout());

		//Initialize the MenuOptions panel
		optionPanel = new DashboardOptionsPanel();
		optionPanel.setOnOptionChangeListener(this);
		
		//The main panel which will hold all sub-panels.
		mainContentPanel = new JPanel();

		// Customize the MenuOptions panel
		add(optionPanel, BorderLayout.WEST);

		// Customize the main content panel
		customizeMainContentPanel();
	}

	/***********************************************************/
	// Interface implementation
	/***********************************************************/
	@Override
	public void onOptionClick(String option) {
		changePanel(option);
	}
	
	/***********************************************************/
	// Private Methods
	/***********************************************************/
	private void changePanel(String option) {
		if (option.equals(ViewConstants.MenuOptions.STR_LOGOUT)) {
			//TODO - Do we keep it here or move to controller?
			int result = JOptionPane.showConfirmDialog(null, 
					   "Are you sure you wish to logout?",null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
						UIUtils.navigateToLogin(this);
					}
					return;
		}
		CardLayout cardLayout = (CardLayout) (mainContentPanel.getLayout());
		cardLayout.show(mainContentPanel, option);
	}

	private void customizeMainContentPanel() {
		CardLayout layout = new CardLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		mainContentPanel.setLayout(layout);
		mainContentPanel.add(ViewConstants.MenuOptions.STR_SALES, new SalesPanel());
		mainContentPanel.add(ViewConstants.MenuOptions.STR_INVENTORY, new InventoryPanel());
		mainContentPanel.add(ViewConstants.MenuOptions.STR_MEMBER, new MemberController().getmemberPanel());
		// TODO: To be resolved
		 mainContentPanel.add(ViewConstants.MenuOptions.STR_DISCOUNT, new DiscountController().getDiscountPanel());
		mainContentPanel.add(ViewConstants.MenuOptions.STR_REPORTS, new ReportPanel());
		add(mainContentPanel, BorderLayout.CENTER);
	}
}
