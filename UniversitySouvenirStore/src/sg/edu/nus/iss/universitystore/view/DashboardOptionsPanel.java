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
		add(configOptionButton("Sales", 25));
		add(configOptionButton("Inventory", 25));
		add(configOptionButton("Discount", 25));
		add(configOptionButton("Report", 25));
		add(configOptionButton("Logout", 25));
	}

	//option Button config here
	private JPanel configOptionButton(String labelText, int FontSize) {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridBagLayout());
		jpanel.setPreferredSize(new Dimension(200, 50));
		jpanel.setMaximumSize(new Dimension(200, 50));
		jpanel.setBackground(new Color(10, 50, 150));
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Verdana", 1, FontSize));
		label.setForeground(Color.WHITE);
		jpanel.add(label);
		return jpanel;
	}

	private void addAnnouncementPane() {

	}
}
