package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.Member;

public abstract class MemberDialog extends BaseDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * The panel associated with this dialog.
	 */
	private JPanel jPanel;


	/**
	 * Text field for holding the member name entered by the user.
	 */
	private JTextField memberName;

	/**
	 * Textfield for holding the member identifier entered by the user.
	 */
	private JTextField memberId;
	
	/**
	 * Textfield for holding the loyalty points that was entered by the user.
	 */
	private JTextField loyaltyPoints;
	
	/***********************************************************/
	// Constructors
	/***********************************************************/
	/**
	 * Constructor to create the dialog.
	 * @param parent The parent frame on top of which this dialog will be created.
	 * @param title The title of this dialog.
	 */
	public MemberDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(250, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	/***********************************************************/
	// Abstract Method Definition
	/***********************************************************/
	/**
	 * The callback which will be called when the Confirm button is tapped on the dialog.
	 * @param memberId The member identifier.
	 * @param memberName The member name that was added/edited.
	 * @param loyaltyPoints The loyalty points of the member that was added/edited.
	 * @return Boolean to indicate whether the dialog needs to be removed from the frame or not. 
	 * If true, then the dialog will be removed.
	 */
	public abstract boolean memberCallBack(String memberId, String memberName, String loyaltyPoints);

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	
	/**
	 * Method to set the member name of the dialog
	 * @param memberName The string which needs to be set as the text for the member name textfield.
	 */
	public void setMemberName(String memberName) {
		this.memberName.setText(memberName);
	}
	
	/**
	 * Method to set the member id of the dialog
	 * @param memberId The string which needs to be set as the text for the member id textfield.
	 */
	public void setMemberId(String memberId) {
		this.memberId.setText(memberId);
	}
	
	/**
	 * Method to set the loyalty points of the dialog
	 * @param loyaltyPoints The string which needs to be set as the text for the loyalty points textfield.
	 */
	public void setLoyaltyPoints(String loyaltyPoints) {
		this.loyaltyPoints.setText(loyaltyPoints);
	}

	/***********************************************************/
	// Abstract Method Implementation
	/***********************************************************/
	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#getPanelToAddToDialog()
	 */
	@Override
	protected boolean confirmClicked() {
		return memberCallBack(memberId.getText(), memberName.getText(), loyaltyPoints.getText());
	}
	
	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#getPanelToAddToDialog()
	 */
	@Override
	protected JPanel getPanelToAddToDialog() {
		// Initialize the panel
		jPanel = new JPanel();

		// Add border for creating a space from the margins.
		Border border = jPanel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		jPanel.setBorder(new CompoundBorder(border, margin));

		// Add the gridbag layout
		GridBagLayout panelGridBagLayout = new GridBagLayout();
		panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
		panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
		panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jPanel.setLayout(panelGridBagLayout);

		// Keep track of the index.
		int index = 0;
		
		// Finally add the elements
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.MEMBER_ID, index);// Move to constants
		memberId = createTextFieldOnPanel(jPanel, index++);
		
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.MEMBER_NAME, index);// Move to constants
		memberName = createTextFieldOnPanel(jPanel, index++);
		
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.LOYALTY_POINTS, index);// Move to constants
		loyaltyPoints = createTextFieldOnPanel(jPanel, index++);
		
		return jPanel;
	}
}
