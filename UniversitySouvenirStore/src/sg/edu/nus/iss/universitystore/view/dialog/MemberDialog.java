package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.model.Member;

public abstract class MemberDialog extends BaseDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = 3029306694712724442L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private Label labelMemberName;
	private Label labelLoyaltyPoints;
	private Label labelMemberId;
	private TextField tfMemberName;
	private TextField tfLoyaltyPoints;
	private TextField tfMemberId;

	/**
	 * Constructor to create the dialog.
	 * 
	 * @param parent
	 *            The parent frame on top of which this dialog will be created.
	 * @param title
	 *            The title of this dialog.
	 */
	public MemberDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(250, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	/**
	 * The callback which will be called when the Confirm button is tapped on
	 * the dialog.
	 * 
	 * @param memberId
	 *            memberId to recognize the member
	 * @param memberName
	 * @param loyaltyPoints
	 * @return
	 */
	public abstract boolean MemberCallBack(String memberId, String memberName, String loyaltyPoints);

	/**
	 * invoke this function when you need fill data into the dialog
	 * 
	 * @param member
	 */
	public void setMemberData(Member member) {
		tfMemberId.setText(member.getIdentifier());
		tfMemberName.setText(member.getName());
		tfLoyaltyPoints.setText(member.getLoyaltyPoints() + "");

	}

	/***********************************************************/
	// Abstract Method Implementation
	/***********************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#
	 * getPanelToAddToDialog()
	 */
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(3, 3));
		labelMemberId = new Label("Member ID");
		labelMemberName = new Label("Member Name");
		labelLoyaltyPoints = new Label("Loyalty Points");

		tfMemberName = new TextField();
		tfMemberName.setColumns(1);
		tfMemberId = new TextField();
		tfMemberId.setColumns(1);
		tfLoyaltyPoints = new TextField();
		tfLoyaltyPoints.setColumns(1);

		jp.add(labelMemberId);
		jp.add(tfMemberId);

		jp.add(labelMemberName);
		jp.add(tfMemberName);

		jp.add(labelLoyaltyPoints);
		jp.add(tfLoyaltyPoints);
		return jp;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		if (tfMemberId.getText().length() != 0 && tfMemberName.getText().length() != 0) {
			MemberCallBack(tfMemberId.getText(), tfMemberName.getText(), tfLoyaltyPoints.getText());
			return true;
		}
		return false;
	}

}
