package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;

public abstract class BaseDialog extends JDialog {

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public BaseDialog (JFrame parent, String title) {
		super (parent, title);
		add ("Center", getPanelToAddToDialog());
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
	protected abstract JPanel getPanelToAddToDialog () ;

	/**
	 * Callback to inform the child that the "Confirm" button has been tapped in the dialog.
	 * @return Boolean to indicate whether the dialog needs to be removed from the frame or not. 
	 * If true, then the dialog will be removed.  
	 */
	protected abstract boolean confirmClicked () ;

	/***********************************************************/
	// Protected Methods
	/***********************************************************/

	/**
	 * Method to create a label on a panel that is having Grid Bag Layout.
	 * @param jPanel The panel on which the label will be added.
	 * @param labelText The text of the label.
	 * @param yPosition The position of the label in terms of the grid bag constraints. 
	 * @return Return a reference to the label that was added to the panel.
	 */
	protected JLabel createLabelOnPanel(JPanel jPanel, String labelText, int yPosition) {
		JLabel jLabel = new JLabel(labelText);
		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 0;
		gridBagConstraintForLabel.gridy = yPosition;
		jPanel.add(jLabel, gridBagConstraintForLabel);
		return jLabel;
	}

	/**
	 * Method to create a textfield on a provided panel based on Grid Bag Layout
	 * @param yPosition The position of the textfield on the panel.
	 * @return Return a reference to the textfield that was added to the panel.
	 */
	protected JTextField createTextFieldOnPanel(JPanel jPanel, int yPosition) {
		JTextField jTextField = new JTextField(10);
		GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
		gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
		gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
		gridBagConstraintForTextField.gridx = 1;
		gridBagConstraintForTextField.gridy = yPosition;
		jPanel.add(jTextField, gridBagConstraintForTextField);
		return jTextField;
	}
	
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
				boolean success = confirmClicked ();
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