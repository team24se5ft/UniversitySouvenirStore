package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;

public abstract class BaseDialog extends JDialog implements WindowListener{

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public BaseDialog (JFrame parent, String title) {
		super (parent, title);
		this.setModal(true);
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
	 * @param jPanel The panel on which the textfield will be added.
	 * @param yPosition The position of the textfield on the panel.
	 * @return Return a reference to the textfield that was added to the panel.
	 */
	protected JTextField createTextFieldOnPanel(JPanel jPanel, int yPosition) {
		JTextField jTextField = new JTextField(10);
		jPanel.add(jTextField, getConstraintsForSecondColumn(yPosition));
		return jTextField;
	}
	
	/**
	 * Method to create a combo box on a provided panel based on Grid Bag Layout
	 * @param list The list that will be passed to the combo box.
	 * @param jPanel The panel on which the combo box will be added.
	 * @param yPosition The position of the combo box on the panel.
	 * @return Return a reference to the combo box that was added to the panel.
	 */
	protected JComboBox<String> createComboBoxOnPanel(String[]list, JPanel panel, int yPosition) {
		JComboBox<String> comboBox = new JComboBox<String>(list);
		panel.add(comboBox, getConstraintsForSecondColumn(yPosition));
		return comboBox;
	}

	/**
	 * Method to get the constraints for any item that is placed on the second column 
	 * @param yPosition The position of the item on the panel.
	 * @return The updated constraints.
	 */
	protected GridBagConstraints getConstraintsForSecondColumn(int yPosition) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = yPosition;
		return gridBagConstraints;
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
		btnCancel.addActionListener(new ActionListener () {
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
	
	/***********************************************************/
	// Window Listener Methods
	/***********************************************************/
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