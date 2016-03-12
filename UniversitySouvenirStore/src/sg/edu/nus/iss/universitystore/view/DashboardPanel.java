/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.view.intf.DashBoardOptionChangeDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.DiscountPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.InventoryPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.ReportPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.SalesPanel;

/**
 * @author Samrat
 *
 */
public class DashboardPanel extends JPanel {

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DashboardPanel() {

		setBackground(new Color(25, 99, 221));

		setLayout(new BorderLayout());

		optionPanel = new DashboardOptionsPanel();
		optionPanel.setOnOptionChangeListener(new DashBoardOptionChangeDelegate() {

			@Override
			public void onOptionClick(String option) {
				changePanel(option);
			}
		});
		mainContentPanel = new JPanel();
		notificationPanel = new JPanel();

		// Customize the options panel
		add(optionPanel, BorderLayout.WEST);

		// Customize the main content panel
		mainContentPanel.setBackground(new Color(90, 90, 90));
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

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	private DashboardOptionsPanel optionPanel;
	private JPanel mainContentPanel;
	private JPanel notificationPanel;

	public void changePanel(String option) {
		System.out.println(option);
		if (option == DashboardOptionsPanel.STR_LOGOUT) {
			// dispose mainFrame and show Login page
			return;
		}
		CardLayout cardLayout = (CardLayout) (mainContentPanel.getLayout());
		cardLayout.show(mainContentPanel, option);
	}

}
