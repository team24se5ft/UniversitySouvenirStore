package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		add(configureOptionButton(ViewConstants.MenuOptions.STR_SALES, 20,ViewConstants.PanelHeaders.SALE_ICON_IMG));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_INVENTORY, 20,ViewConstants.PanelHeaders.INVENTORY_ICON_IMG));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_MEMBER, 20,ViewConstants.PanelHeaders.MEMBER_ICON_IMG));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_DISCOUNT, 20,ViewConstants.PanelHeaders.DISCOUNT_ICON_IMG));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_REPORTS, 20,ViewConstants.PanelHeaders.REPORT_ICON_IMG));
		add(configureOptionButton(ViewConstants.MenuOptions.STR_LOGOUT, 20,ViewConstants.PanelHeaders.LOGOUT_ICON_IMG));
	}

	//option Button configure here
	private JPanel configureOptionButton(String labelText, int fontSize,String iconUrl) {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpanel.setPreferredSize(new Dimension(200, 50));
		jpanel.setMaximumSize(new Dimension(200, 50));
		jpanel.setBackground(Color.DARK_GRAY);
		jpanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		Image image=new ImageIcon(iconUrl).getImage();
		JLabel iconLabel=new JLabel(new ImageIcon(image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		JLabel label = new JLabel(labelText);
		label.setFont(new Font(ViewConstants.Common.DEFAULT_FONT, 1, fontSize));
		label.setForeground(Color.WHITE);
		jpanel.add(iconLabel);
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
		textArea.setText(ViewConstants.PanelHeaders.ANNOUNCEMENT_DEMO); 
		textArea.setEditable(false);
		textArea.setHighlighter(null);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);
	}
}
