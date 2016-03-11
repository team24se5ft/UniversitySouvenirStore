package sg.edu.nus.iss.universitystore.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;

public class LoginPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final static String STR_UserLabel = "userName";
	private final static String STR_PwdLabel = "password";
	private JLabel userLabel;
	private JLabel pwdLabel;
	

	private JTextField userTextField;
	private JPasswordField pwdTextField;
	private JButton btnSubmit;

	private int width = 400;
	private int height = 200;

	private ILoginDelegate loginListener;

	public LoginPanel() {
		this.setLayout(new BorderLayout());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		userLabel = new JLabel(STR_UserLabel);
		pwdLabel = new JLabel(STR_PwdLabel);
		userTextField = new JTextField("", 1);
		pwdTextField = new JPasswordField("", 1);
		btnSubmit = new JButton("submit");

		JPanel p1 = new JPanel();
		p1.setBounds((dimension.width - width) / 2, (dimension.height - height) / 2, width, height);
		p1.add(userLabel);
		p1.add(userTextField);

		p1.add(pwdLabel);
		p1.add(pwdTextField);
		p1.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginListener != null) {
					loginListener.buttonAction();
				}
			}
		});
		this.add(p1, "Center");
	}

	public void setLoginPanelListener(ILoginDelegate loginListener) {
		this.loginListener = loginListener;
	}

	public JButton getSubmitButton() {
		return btnSubmit;
	}
}
