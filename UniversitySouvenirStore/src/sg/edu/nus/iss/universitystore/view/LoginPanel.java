package sg.edu.nus.iss.universitystore.view;

import java.awt.Color;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;

/**
 * @author Samrat
 *
 */
public class LoginPanel extends BasePanel{
	
	/***********************************************************/
	//Constants
	/***********************************************************/
	/**
	 * Serial Version UID.
	 */
	public static final long serialVersionUID = 1L;

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	
	/**
	 * The background image for the panel.
	 */
	private Image imgbackground;

	/**
	 * The delegate object which will enable communication to the controller.
	 */
	private ILoginDelegate delegate;

	/**
	 * The overlay panel which holds the text fields, labels & submit button.
	 */
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
			imgbackground = javax.imageio.ImageIO.read(new File(ViewConstants.PanelHeaders.BACKGRND_IMG));
		}
		catch (Exception e) { /*handled in paintComponent()*/ }
		setLayout(new GridBagLayout());
		createGUI();
	}

	/***********************************************************/
	//Getter & Setters
	/***********************************************************/
	/**
	 * Set the login delegate to enable communication from the view.
	 * @param loginListener
	 */
	public void setDelegate(ILoginDelegate delegate) {
		this.delegate = delegate;
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	/**
	 * Allows clearing the username & password textfield.
	 */
	public void clearAllFields() {
		txtUserName.setText(Constants.Common.EMPTY_STR);
		txtPassword.setText(Constants.Common.EMPTY_STR);
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

	/**
	 * The main method for creating the GUI.
	 */
	private void createGUI() {
		overlayPanel = new JPanel();
		overlayPanel.setPreferredSize(new Dimension(330, 170));
		overlayPanel.setMaximumSize(new Dimension(330, 170));
		overlayPanel.setBackground(new java.awt.Color(0, 102, 204));
		overlayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		overlayPanel.setOpaque(true);
        overlayPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, ViewConstants.Labels.STR_LOGIN, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(ViewConstants.Fonts.VANI_FONT, 3, 24), new java.awt.Color(255, 255, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(ViewConstants.Fonts.TAHOMA_FONT, 0, 16), new java.awt.Color(255, 255, 255)));
		//Add child elements to the overlay
		addElementsToOverLay();
		//Add the overlay panel to the main panel.
		add(overlayPanel);
	}

	/**
	 * Method to add the child elements to the Overlay Panel.
	 */
	private void addElementsToOverLay() {
		//Label UserName
        lblUserName = getLabel(ViewConstants.Labels.STR_USER_LABEL);
        overlayPanel.add(lblUserName);

		//Text field UserName
		txtUserName = new JTextField();
		txtUserName.setPreferredSize(new Dimension(200, 20));
		overlayPanel.add(txtUserName);

		//Label UserName
        lblPassword = getLabel(ViewConstants.Labels.STR_PASSWORD_LABEL);
		overlayPanel.add(lblPassword);

		//Text field UserName
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(200, 20));
		overlayPanel.add(txtPassword);
		
		//Submit button
		btnSubmit = new JButton(new ImageIcon(ViewConstants.PanelHeaders.LOGIN_ICON_IMG));
		btnSubmit.setBorderPainted(false);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setFocusPainted(false);
		btnSubmit.setOpaque(false);
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		
				delegate.loginButtonClicked(txtUserName.getText(), getPassword());
			}
		});
		overlayPanel.add(btnSubmit);
	}

	/**
	 * Get string from the password textfield.
	 * @return The string from the password textfield.
	 */
	private String getPassword(){
		//TODO - Find a better way of getting the password.
		return new String(txtPassword.getPassword());
	}
	
	/**
	 * Get formatted JLabel.
	 * @param labelText The text associated with the label.
	 * @return A formatted JLabel object with the associated text. 
	 */
	private JLabel getLabel(String labelText) {
		JLabel jLabel = new JLabel(labelText);
		jLabel.setFont(new java.awt.Font(ViewConstants.Fonts.VANI_FONT, 2, 18)); 
		jLabel.setForeground(new java.awt.Color(255, 255, 255));
		return jLabel;
	}
}
