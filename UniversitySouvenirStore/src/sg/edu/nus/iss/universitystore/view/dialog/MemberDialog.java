package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import sg.edu.nus.iss.universitystore.view.dialog.intf.MemberDialogDelegate;

public class MemberDialog extends OkCancelDialog implements WindowListener {
	private static final long serialVersionUID = 3029306694712724442L;
	private MemberDialogDelegate delegate;

	private Label labelFirstName;
	private Label labelSecondName;
	private Label labelSurName;
	private TextField tfFirstName;
	private TextField tfSecondName;
	private TextField tfSurName;

	public MemberDialog(JFrame parent) {
		super(parent, "AddMember");
	}

	public MemberDialog(JFrame parent, String title,MemberDialogDelegate addMemberDialogDelegate) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(400, 150);
		this.setResizable(false);
		this.setLocation(200, 200);
	}

	@Override
	protected Panel createFormPanel() {
		Panel jp = new Panel();
		jp.setLayout(new GridLayout(3, 3));
		labelSurName = new Label("surName");
		labelFirstName = new Label("firstName");
		labelSecondName = new Label("secondName");

		tfFirstName = new TextField();
		tfFirstName.setColumns(1);
		tfSecondName = new TextField();
		tfSecondName.setColumns(1);
		tfSurName = new TextField();
		tfSurName.setColumns(1);

		jp.add(labelSurName);
		jp.add(tfSurName);

		jp.add(labelFirstName);
		jp.add(tfFirstName);

		jp.add(labelSecondName);
		jp.add(tfSecondName);
		return jp;
	}

	@Override
	protected boolean performOkAction() {
		if (tfSurName.getText().length() != 0 && tfFirstName.getText().length() != 0) {
			delegate.MemberCallBack(tfSurName.getText(), tfFirstName.getText(), tfSecondName.getText());
			return true;
		}
		return false;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
