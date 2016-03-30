package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;

public abstract class MemberScanDialog extends BaseDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = -6976311071183139160L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	// textfield define
	private TextField MemberCode;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	/**
	 * Constructor to create the dialog.
	 * 
	 * @param parent
	 *            The parent frame on top of which this dialog will be created.
	 * @param title
	 *            The title of this dialog.
	 */
	public MemberScanDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(400, 80);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}
	
	
	/**
	 * check the memberCode and return result
	 * @param MemberCode 
	 */
	public abstract boolean onMemberIdentification(String MemberCode);

	/***********************************************************/
	// Abstract Method Implementation
	/***********************************************************/
	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#getPanelToAddToDialog()
	 */
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1, 2));

		MemberCode = new TextField();
		MemberCode.setColumns(1);

		jp.add(new JLabel(ViewConstants.DialogHeaders.MEMBER_CODE));
		jp.add(MemberCode);
		return jp;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		if (MemberCode.getText().length() != 0) {
			return onMemberIdentification(MemberCode.getText());
		}
		return false;
	}

}
