package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.view.dialog.intf.MemberScanDialogDelegate;

public class MemberScanDialog extends BaseDialog {
	private static final long serialVersionUID = -6976311071183139160L;
	// textfield define
	private TextField MemberCode;

	private MemberScanDialogDelegate delegate;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public MemberScanDialog(JFrame parent) {
		super(parent, "ScanMemberCode");
	}

	public MemberScanDialog(JFrame parent, String title, MemberScanDialogDelegate delegate) {
		super(parent, title);
		this.delegate = delegate;
		this.addWindowListener(this);
		this.setSize(400, 80);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

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
			delegate.onMemberLogin(MemberCode.getText());
			return false;
		}
		return false;
	}

}
