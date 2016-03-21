package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class ConfirmationDialog extends BaseDialog{

	private static final long serialVersionUID = 1L;
	
	private Label messageLabel;

	public ConfirmationDialog(JFrame parent, String title, String message) {
		super(parent, title);
		messageLabel.setText(message);
		this.addWindowListener(this);
		this.setSize(300, 100);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	protected JPanel getPanelToAddToDialog() {
		JPanel jPanel = new JPanel();
		messageLabel = new Label();
		jPanel.add(messageLabel);
		return jPanel;
	}

	@Override
	protected abstract boolean confirmClicked();
}
