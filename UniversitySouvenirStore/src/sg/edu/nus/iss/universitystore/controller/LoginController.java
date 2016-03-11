package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.view.LoginPanel;
import sg.edu.nus.iss.universitystore.view.intf.ILoginDelegate;

public class LoginController implements ILoginDelegate {
	private LoginPanel logPanel;

	public LoginController() {
		logPanel = new LoginPanel();
		logPanel.setLoginPanelListener(this);
	}
	
	public JPanel getLoginPanel(){
		return logPanel;
	}

	@Override
	public void buttonAction() {
		System.out.println(123);
	}
}
