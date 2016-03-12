package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sg.edu.nus.iss.universitystore.Constants;

/**
 * @author Samrat
 *
 */
public class DashboardOptionsPanel extends JPanel {

	/***********************************************************/
	//Constants
	/***********************************************************/
	public static final String STR_SALES = "Sales";
	public static final String STR_INVENTORY = "Inventory";
	public static final String STR_REPORTS = "Reports";
	public static final String STR_LOGOUT = "Logout";
	public static final String STR_DISCOUNT = "Sales";
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	public DashboardOptionsPanel() {
		createGUI();
	}

	/***********************************************************/
	//Private Methods
	/***********************************************************/
	private void createGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(200, 300));
		setMaximumSize(new Dimension(200, 300));
		setBackground(new Color(100, 200, 150));
		
		//Add the selection buttons
		addButtons();
		//Add the announcement pane
		addAnnouncementPane();
	}

	private void addButtons() {
		add(configureOptionButton(STR_SALES, 25));
		add(configureOptionButton(STR_INVENTORY, 25));
		add(configureOptionButton(STR_DISCOUNT, 25));
		add(configureOptionButton(STR_REPORTS, 25));
		add(configureOptionButton(STR_LOGOUT, 25));
	}

	//option Button configure here
	private JPanel configureOptionButton(String labelText, int fontSize) {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridBagLayout());
		jpanel.setPreferredSize(new Dimension(200, 50));
		jpanel.setMaximumSize(new Dimension(200, 50));
		jpanel.setBackground(Color.DARK_GRAY);
		jpanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		JLabel label = new JLabel(labelText);
		label.setFont(new Font(Constants.DEFAULT_FONT, 1, fontSize));
		label.setForeground(Color.WHITE);
		jpanel.add(label);
		return jpanel;
	}

	private void addAnnouncementPane() {
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		//TODO - This input needs to be received. 
		textArea.setText("03/03: Linc Pen stock low.\n\n27/02: Member A001482497 successfully created."); 
		textArea.setEditable(false);
		textArea.setHighlighter(null);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);
	}
}
