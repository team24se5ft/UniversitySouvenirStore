package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class MemberScanDialog extends BaseDialog {
	private static final long serialVersionUID = -6976311071183139160L;
	// textfield define
	private TextField MemberCode;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public MemberScanDialog(JFrame parent) {
		super(parent, "ScanMemberCode");
	}

	public MemberScanDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(400, 80);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}
	
	
	/**
	 * check the membercode and return result
	 * @param MemberCode
	 */
	public abstract boolean onMemberLogin(String MemberCode);

	/***********************************************************/
	// override method
	/***********************************************************/
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1, 2));

		MemberCode = new TextField();
		MemberCode.setColumns(1);

		jp.add(new JLabel("MemberCode:"));
		jp.add(MemberCode);
		return jp;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		if (MemberCode.getText().length() != 0) {
			onMemberLogin(MemberCode.getText());
			return false;
		}
		return false;
	}

}
