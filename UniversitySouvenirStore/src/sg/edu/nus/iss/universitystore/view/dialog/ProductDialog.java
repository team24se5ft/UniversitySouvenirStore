/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.view.dialog.intf.IProductDialogDelegate;

/**
 * @author Samrat
 *
 */
public class ProductDialog extends BaseDialog implements WindowListener {

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
	public ProductDialog(JFrame parent, String title, IProductDialogDelegate delegate) {
		// Call the parent
		super(parent, title);
		// Set the delegate
		this.delegate = delegate;
		// Customize this dialog
		this.addWindowListener(this);
		this.setSize(400, 300);//TODO: Move to constants
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
	private IProductDialogDelegate delegate;

	/**
	 * Text field for holding the product name entered by the user.
	 */
	private JTextField productName;

	/**
	 * Textfield for holding the production description as entered by the user.
	 */
	private JTextField productDescription;

	/**
	 * Textfield for holding the quantity available for the particular product.
	 */
	private JTextField quantity;

	/**
	 * Textfield for holding the price for the particular product.
	 */
	private JTextField price;
	
	/**
	 * Textfield for holding the bar code number for the particular product.
	 */
	private JTextField barcodeNumber;

	/**
	 * Textfield for holding the reorder quantity of the particular product.
	 */
	private JTextField reorderQuantity;

	/**
	 * Textfield for holding the order quantity of the particular product.
	 */
	private JTextField orderQuantity;

	/***********************************************************/
	// Abstract Method Implementation
	/***********************************************************/
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
		createLabelOnPanel(jPanel, "Product Name:", 0);// Move to constants
		productName = createTextFieldOnPanel(jPanel, 0);

		createLabelOnPanel(jPanel, "Product Description:", 1);// Move to constants
		productDescription = createTextFieldOnPanel(jPanel, 1);
		
		createLabelOnPanel(jPanel, "Product Quantity:", 2);// Move to constants
		quantity = createTextFieldOnPanel(jPanel, 2);
		
		createLabelOnPanel(jPanel, "Product Price:", 3);// Move to constants
		price = createTextFieldOnPanel(jPanel, 3);
		
		createLabelOnPanel(jPanel, "Bar Code Number:", 4);// Move to constants
		barcodeNumber = createTextFieldOnPanel(jPanel, 4);
		
		createLabelOnPanel(jPanel, "Reorder Quantity (Threshold):", 5);// Move to constants
		reorderQuantity = createTextFieldOnPanel(jPanel, 5);
		
		createLabelOnPanel(jPanel, "Order Quantity:", 6);// Move to constants
		orderQuantity = createTextFieldOnPanel(jPanel, 6);
		
		return jPanel;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#confirmClicked()
	 */
	@Override
	protected boolean confirmClicked() {
		delegate.confirmClicked(productName.getText(), productDescription.getText(), quantity.getText(), price.getText(), barcodeNumber.getText(), reorderQuantity.getText(), orderQuantity.getText());
		// TODO Auto-generated method stub
		return false;
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
