package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.view.intf.IDashBoardOptionChangeDelegate;

/**
 * @author Samrat
 *
 */
public class DashboardOptionsPanel extends JPanel {

	/***********************************************************/
	//Constructors
	/***********************************************************/
	public DashboardOptionsPanel() {
		createGUI();
	}

    /***********************************************************/
    //Instance Variables
    /***********************************************************/
    private IDashBoardOptionChangeDelegate dashBoardOptionChangeDelegate;
    
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
		add(configureOptionButton(ViewConstants.MenuOptions.STR_SALES, 25));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_INVENTORY, 25));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_MEMBER, 25));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_DISCOUNT, 25));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_REPORTS, 25));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_LOGOUT, 25));
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
		label.setFont(new Font(ViewConstants.Common.DEFAULT_FONT, 1, fontSize));
		label.setForeground(Color.WHITE);
		
		jpanel.add(label);
		jpanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				dashBoardOptionChangeDelegate.onOptionClick(label.getText());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		return jpanel;
	}

	public void setOnOptionChangeListener(IDashBoardOptionChangeDelegate dashBoardOptionChangeDelegate) {
		this.dashBoardOptionChangeDelegate = dashBoardOptionChangeDelegate;
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
