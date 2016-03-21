/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagLayout;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
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
public class CategoryDialog extends BaseDialog {

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
	 * @param delegate The delegate(controller reference) which enables communication to the controller.
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
	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#getPanelToAddToDialog()
	 */
	@Override
	protected boolean confirmClicked() {
		// TODO - Add validation
		delegate.confirmClicked(categoryCode.getText(), categoryName.getText());
		return false;
	}
	
	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#getPanelToAddToDialog()
	 */
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
		categoryCode = createTextFieldOnPanel(jPanel, 0);
		
		createLabelOnPanel(jPanel, "Category Name:", 1);// Move to constants
		categoryName = createTextFieldOnPanel(jPanel, 1);
		return jPanel;
	}

}
