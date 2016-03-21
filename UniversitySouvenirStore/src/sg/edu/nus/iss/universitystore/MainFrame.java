package sg.edu.nus.iss.universitystore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.controller.DashboardController;
import sg.edu.nus.iss.universitystore.controller.LoginController;
import sg.edu.nus.iss.universitystore.view.MainPanel;

public class MainFrame extends JFrame{
	
	/***********************************************************/
	//Constants
	/***********************************************************/
	
	
	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private MainPanel mainPanel;
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	
	/***********************************************************/
	//Private Methods
	/***********************************************************/
	private void addMenuToFrame() {
		JMenuBar menuBar = new JMenuBar();
		//Add menus
		JMenu fileMenu = new JMenu(ViewConstants.MainFrame.MenuBar.FILE);
		menuBar.add(fileMenu);

		//Add menus
		JMenu editMenu = new JMenu(ViewConstants.MainFrame.MenuBar.EDIT);
		menuBar.add(editMenu);

		//Add menus
		JMenu soureMenu = new JMenu(ViewConstants.MainFrame.MenuBar.SOURCE);
		menuBar.add(soureMenu);

		JMenuItem item = new JMenuItem(ViewConstants.MainFrame.MenuBar.EXIT);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(item);

		this.setJMenuBar(menuBar);
	}

	/***********************************************************/
	//Public Methods
	/***********************************************************/
	public void createAndShowGUI() {
		//Initialize the content pane
		this.mainPanel = new MainPanel();
		//Setup the Frame
		this.setSize (ViewConstants.MainFrame.WINDOW_WIDTH, ViewConstants.MainFrame.WINDOW_HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Add components and stuff
		this.setContentPane(mainPanel);
		//Add the menu bar
		addMenuToFrame();
		//Optional: center on screen
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/***********************************************************/
	//Main
	/***********************************************************/
	public static void main(String[] args) {
		//Take the menu bar off the frame
		System.setProperty(ViewConstants.MainFrame.APPLE_MENUBAR, Boolean.TRUE.toString());

		//Set the look and feel
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.createAndShowGUI();
				//Add the login screen on launch
				LoginController loginController = new LoginController();
				mainFrame.getMainPanel().add(loginController.getLoginPanel());
			}
		});
	}

}
