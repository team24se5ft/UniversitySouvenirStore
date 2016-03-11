/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * @author Samrat
 *
 */
public class DashboardPanel extends JPanel{

	/***********************************************************/
	//Constructors
	/***********************************************************/
	public DashboardPanel(){
		
		setBackground(new Color(25, 99, 221));
		
		setLayout(new BorderLayout());
		
		optionPanel = new JPanel();
		mainContentPanel = new JPanel();
		notificationPanel = new JPanel();
		
		//Customize the options panel
		optionPanel.setPreferredSize(new Dimension(200, 300));
		optionPanel.setMaximumSize(new Dimension(200, 300));
		optionPanel.setBackground(new Color(100, 200, 150));
		add(optionPanel, BorderLayout.WEST);
		
		//Customize the main content panel
		mainContentPanel.setBackground(new Color(90, 90, 90));
		add(mainContentPanel, BorderLayout.CENTER);
	}
	
	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	private JPanel optionPanel;
	private JPanel mainContentPanel;
	private JPanel notificationPanel;
	
}
