/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.view.dialog.intf.ICategoryDialogDelegate;

/**
 * @author Samrat
 *
 */
public class CategoryDialog extends BaseDialog implements WindowListener {

	/***********************************************************/
	// Constants
	/***********************************************************/

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	/**
	 * Constructor to create the dialog.
	 * @param parent The parent frame on top of which this dialog will be created.
	 * @param title The title of this dialog.
	 */
	public CategoryDialog(JFrame parent, String title, ICategoryDialogDelegate delegate) {
		// Call the parent
		super(parent, title);
		// Set the delegate
		this.delegate = delegate;
		// Customize this dialog
		this.addWindowListener(this);
		this.setSize(400, 200);//TODO: Move to constants
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * The panel associated with this dialog.
	 */
	private JPanel jPanel;

	/**
	 * Delegate for communicating with the controller.
	 */
	private ICategoryDialogDelegate delegate;

	/**
	 * Text field for holding the category name entered by the user.
	 */
	private JTextField categoryName;

	/**
	 * Textfield for holding the category code entered by the user.
	 */
	private JTextField categoryCode;
	/***********************************************************/
	// Abstract Method Implementation
	/***********************************************************/

	@Override
	protected boolean confirmClicked() {
		// TODO - Add validation
		delegate.confirmClicked(categoryCode.getText(), categoryName.getText());
		return false;
	}

	@Override
	protected JPanel getPanelToAddToDialog() {
		// Initialize the panel
		jPanel = new JPanel();

		// Add border for creating a space from the margins.
		Border border = jPanel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		jPanel.setBorder(new CompoundBorder(border, margin));

		// Add the gridbag layout
		GridBagLayout panelGridBagLayout = new GridBagLayout();
		panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
		panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
		panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jPanel.setLayout(panelGridBagLayout);

		// Finally add the elements
		createLabelOnPanel(jPanel, "Category Code:", 0);// Move to constants
		categoryCode = createTextField(jPanel, 0);
		
		createLabelOnPanel(jPanel, "Category Name:", 1);// Move to constants
		categoryName = createTextField(jPanel, 1);
		return jPanel;
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/**
	 * Method to create a label on a panel that is having Grid Bag Layout.
	 * @param jPanel The panel on which the label will be added.
	 * @param labelText The text of the label.
	 * @param yPosition The position of the label in terms of the grid bag constraints. 
	 * @return Return a reference to the label that was added to the panel.
	 */
	private JLabel createLabelOnPanel(JPanel jPanel, String labelText, int yPosition) {
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
	private JTextField createTextField(JPanel jPanel, int yPosition) {
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
