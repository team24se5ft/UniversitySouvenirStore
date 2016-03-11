package sg.edu.nus.iss.universitystore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import sg.edu.nus.iss.universitystore.view.MainPanel;

public class MainFrame extends JFrame{
	
	/***********************************************************/
	//Constants
	/***********************************************************/
	public static final String STR_FILE = "File";
	public static final String STR_EDIT = "Edit";
	public static final String STR_SOURCE = "Source";
	public static final String STR_EXIT = "Exit";
	public static final String STR_TRUE = "true";
	
	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private MainPanel mainPanel;
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	//Private Methods
	/***********************************************************/
	private void addMenuToFrame() {
		JMenuBar menuBar = new JMenuBar();
		//Add menus
		JMenu fileMenu = new JMenu(STR_FILE);
		menuBar.add(fileMenu);

		//Add menus
		JMenu editMenu = new JMenu(STR_EDIT);
		menuBar.add(editMenu);

		//Add menus
		JMenu soureMenu = new JMenu(STR_SOURCE);
		menuBar.add(soureMenu);

		JMenuItem item = new JMenuItem(STR_EXIT);
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
		this.setSize (Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
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
		// take the menu bar off the frame
		System.setProperty(Constants.APPLE_MENUBAR, STR_TRUE);

		// set the look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}


		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.createAndShowGUI();
			}
		});
	}

}
