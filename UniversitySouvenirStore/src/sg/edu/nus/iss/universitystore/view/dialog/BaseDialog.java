package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;

public abstract class BaseDialog extends JDialog {

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public BaseDialog (JFrame parent, String title) {
		super (parent, title);
		add ("Center", createFormPanel());
		add ("South",  createButtonPanel());
	}

	public BaseDialog (JFrame parent) {
		this (parent, "");
	}

	/***********************************************************/
	// Abstract Methods
	/***********************************************************/
	/**
	 * Method to implement the actual JPanel that will consist of all the labels & textfields.
	 * @return The JPanel that will be displayed in the dialog.
	 */
	protected abstract JPanel createFormPanel () ;

	/**
	 * Callback to inform the child that the "Confirm" button has been tapped in the dialog.
	 * @return Boolean to indicate whether the dialog needs to be removed from the frame or not. 
	 * If true, then the dialog will be removed.  
	 */
	protected abstract boolean performOkAction () ;

	/***********************************************************/
	// Private Methods
	/***********************************************************/
	/**
	 * Creates the button panel consisting of "Confirm" & "Cancel" options.
	 * @return The JPanel which will have the two buttons("Confirm" & "Cancel").
	 */
	private JPanel createButtonPanel () {
		JPanel jPanel = new JPanel();
		// Confirm button
		Button btnConfirm = new Button(ViewConstants.Labels.STR_CONFIRM);
		btnConfirm.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				boolean success = performOkAction ();
				if (success) {
					destroyDialog ();
				}
			}
		});

		// Cancel button
		Button btnCancel = new Button(ViewConstants.Labels.STR_CANCEL);
		btnConfirm.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				destroyDialog ();
			}
		});

		// Add the button to the panel
		jPanel.add(btnConfirm);
		jPanel.add(btnCancel);

		return jPanel;
	}

	/**
	 * Destroys the dialog.
	 */
	private void destroyDialog () {
		setVisible (false);
		dispose();
	}
}