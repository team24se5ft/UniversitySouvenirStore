package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

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
			imgbackground = javax.imageio.ImageIO.read(new File("Resources/main_background.jpg"));
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
		overlayPanel.setPreferredSize(new Dimension(330, 170));
		overlayPanel.setMaximumSize(new Dimension(330, 170));
		overlayPanel.setBackground(new java.awt.Color(0, 102, 204));
		overlayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//overlayPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		overlayPanel.setOpaque(true);
		//ImageIcon icon = new ImageIcon("Resources/add_icon.png");
		//overlayPanel.setBorder(BorderFactory.createMatteBorder(-1, -1, -1, -1, icon));
        overlayPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Vani", 3, 24), new java.awt.Color(255, 255, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
		//Add child elements to the overlay
		addElementsToOverLay();
		//Add the overlay panel to the main panel.
		add(overlayPanel);
	}

	private void addElementsToOverLay() {
		//overlayPanel.setLayout(new FlowLayout());

		//Label UserName
		lblUserName = new JLabel(STR_USER_LABEL);
		lblUserName.setFont(new java.awt.Font("Vani", 2, 18)); 
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        overlayPanel.add(lblUserName);

		//Text field UserName
		txtUserName = new JTextField();
		txtUserName.setPreferredSize(new Dimension(200, 20));
		overlayPanel.add(txtUserName);

		//Label UserName
		lblPassword = new JLabel(STR_PASSWORD_LABEL);
		lblPassword.setFont(new java.awt.Font("Vani", 2, 18));
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
		overlayPanel.add(lblPassword);

		//Text field UserName
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(200, 20));
		overlayPanel.add(txtPassword);
		
		//Submit button
		btnSubmit = new JButton(new ImageIcon("Resources/login_icon.jpg"));
		//btnSubmit.setBorderPainted(false);
		btnSubmit.setContentAreaFilled(false);
		//btnSubmit.setFocusPainted(false);
		//btnSubmit.setOpaque(false);
		
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
