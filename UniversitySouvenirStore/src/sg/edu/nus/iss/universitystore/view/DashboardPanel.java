/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.view.intf.DashBoardOptionChangeDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.DiscountPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;
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

		//Initialize the options panel
		optionPanel = new DashboardOptionsPanel();
		optionPanel.setOnOptionChangeListener(this);
		
		//The main panel which will hold all sub-panels.
		mainContentPanel = new JPanel();

		// Customize the options panel
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
		System.out.println(option);
		if (option.equals(DashboardOptionsPanel.STR_LOGOUT)) {
			UIUtils.navigateToLogin(this);
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
		mainContentPanel.add(DashboardOptionsPanel.STR_SALES, new SalesPanel());
		mainContentPanel.add(DashboardOptionsPanel.STR_INVENTORY, new InventoryPanel());
		mainContentPanel.add(DashboardOptionsPanel.STR_DISCOUNT, new DiscountPanel());
		mainContentPanel.add(DashboardOptionsPanel.STR_REPORTS, new ReportPanel());
		add(mainContentPanel, BorderLayout.CENTER);
	}
}
