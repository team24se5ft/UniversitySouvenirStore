package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.view.dialog.intf.IMemberDialogDelegate;

public class MemberDialog extends BaseDialog implements WindowListener {
	private static final long serialVersionUID = 3029306694712724442L;
	private int type;
	private IMemberDialogDelegate delegate;

	/***********************************************************/
	// Constants
	/***********************************************************/
	public final static int ADD_TYPE = 0;
	public final static int UPDATE_TYPE = 1;

	private Label labelMemberName;
	private Label labelLoyaltyPoints;
	private Label labelMemberId;
	private TextField tfMemberName;
	private TextField tfLoyaltyPoints;
	private TextField tfMemberId;

	public MemberDialog(JFrame parent) {
		super(parent, "AddMember");
	}

	public MemberDialog(JFrame parent, String title,IMemberDialogDelegate delegate, int type) {
		super(parent, title);
		this.type = type;
		this.delegate = delegate;
		this.addWindowListener(this);
		this.setSize(250, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}
	
	/**
	 * invoke this function when you need fill data into the dialog
	 * @param member
	 */
	public void setMemberData(Member member) {
		tfMemberId.setText(member.getIdentifier());
		tfMemberName.setText(member.getName());
		tfLoyaltyPoints.setText(member.getLoyaltyPoints()+ "");
		
		
	}
	

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
     * adding row to table onClick
     */
	@Override
	protected boolean confirmClicked() {
		if (tfMemberId.getText().length() != 0 && tfMemberName.getText().length() != 0) {
			delegate.MemberCallBack(tfMemberId.getText(), tfMemberName.getText(), tfLoyaltyPoints.getText());
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
