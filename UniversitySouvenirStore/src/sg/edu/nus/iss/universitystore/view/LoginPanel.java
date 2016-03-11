package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sg.edu.nus.iss.universitystore.Constants;
import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;

/**
 * @author Samrat
 *
 */
public class LoginPanel extends JPanel{

	/***********************************************************/
	//Constants
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	private final static String STR_USER_LABEL = "Username:";
	private final static String STR_PASSWORD_LABEL = "Password:";
	private final static String STR_LOGIN = "Login";

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private Image imgbackground;

	private ILoginDelegate loginListener;

	private JPanel overlayPanel;

	//Part of the child panel
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnSubmit;
	/***********************************************************/
	//Constructors
	/***********************************************************/
	public LoginPanel()
	{
		try
		{
			imgbackground = javax.imageio.ImageIO.read(new File("Resources/Test.jpg"));
		}
		catch (Exception e) { /*handled in paintComponent()*/ }
		setLayout(new GridBagLayout());
		createGUI();
	}

	/***********************************************************/
	//Getter & Setters
	/***********************************************************/
	public void setLoginListener(ILoginDelegate loginListener) {
		this.loginListener = loginListener;
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	public void clearAllFields() {
		txtUserName.setText(Constants.STR_EMPTY);
		txtPassword.setText(Constants.STR_EMPTY);
	}
	
	/***********************************************************/
	//Private Methods
	/***********************************************************/
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		if (imgbackground != null)
			g.drawImage(imgbackground,0,0,this.getWidth(),this.getHeight(),this);
	}

	private void createGUI() {
		overlayPanel = new JPanel();
		overlayPanel.setPreferredSize(new Dimension(300, 100));
		overlayPanel.setMaximumSize(new Dimension(300, 100));
		overlayPanel.setBackground(Color.WHITE);
		overlayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		overlayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		overlayPanel.setOpaque(false);
		//Add child elements to the overlay
		addElementsToOverLay();
		//Add the overlay panel to the main panel.
		add(overlayPanel);
	}

	private void addElementsToOverLay() {
		overlayPanel.setLayout(new FlowLayout());

		//Label UserName
		lblUserName = new JLabel(STR_USER_LABEL);
		overlayPanel.add(lblUserName);

		//Text field UserName
		txtUserName = new JTextField();
		txtUserName.setPreferredSize(new Dimension(200, 20));
		overlayPanel.add(txtUserName);

		//Label UserName
		lblPassword = new JLabel(STR_PASSWORD_LABEL);
		overlayPanel.add(lblPassword);

		//Text field UserName
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(200, 20));
		overlayPanel.add(txtPassword);
		
		//Submit button
		btnSubmit = new JButton(STR_LOGIN);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginListener.loginButtonClicked(txtUserName.getText(), getPassword());
			}
		});
		overlayPanel.add(btnSubmit);
	}
	
	private String getPassword(){
		//TODO - Find a better way of getting the password.
		return new String(txtPassword.getPassword());
	}
}
