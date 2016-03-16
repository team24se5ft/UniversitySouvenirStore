package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConfirmDialog extends BaseDialog implements WindowListener{

	private Label messageLabel;

	public ConfirmDialog(JFrame parent, String title, String message) {
		super(parent, title);
		messageLabel.setText(message);
		this.addWindowListener(this);
		this.setSize(300, 100);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		messageLabel = new Label();
		p.add(messageLabel);
		return p;
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

	@Override
	protected boolean performOkAction() {
		return false;
	}

}
