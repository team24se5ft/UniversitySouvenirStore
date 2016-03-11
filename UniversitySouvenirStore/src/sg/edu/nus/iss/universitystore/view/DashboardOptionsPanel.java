/**
 * 
 */
package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Samrat
 *
 */
public class DashboardOptionsPanel extends JPanel {

	public DashboardOptionsPanel() {
		createGUI();
	}
	
	private void createGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(200, 300));
		setMaximumSize(new Dimension(200, 300));
		setBackground(new Color(100, 200, 150));
		
		addButtons();
		addAnnouncementPane();
	}
	
	private void addButtons() {
		JPanel salesPanel = new JPanel();
		salesPanel.setLayout(new GridBagLayout());
		salesPanel.setPreferredSize(new Dimension(200, 50));
		salesPanel.setMaximumSize(new Dimension(200, 50));
		salesPanel.setBackground(new Color(10, 50, 150));
		JLabel label =  new JLabel("Sales");
		label.setFont(new Font("Verdana",1,30));
		label.setForeground(Color.WHITE);
		salesPanel.add(label);
		
		add(salesPanel);
	}
	
	private void addAnnouncementPane() {
		
	}
}
