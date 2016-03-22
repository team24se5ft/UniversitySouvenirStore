/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * @author Samrat
 *
 */
public abstract class ProductDialog extends BaseDialog {

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
	 * 
	 * @param parent
	 *            The parent frame on top of which this dialog will be created.
	 * @param title
	 *            The title of this dialog.
	 */
	public ProductDialog(JFrame parent, String title) {
		// Call the parent
		super(parent, title);
		// Customize this dialog
		this.addWindowListener(this);
		this.setSize(400, 350);// TODO: Move to constants
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
	private JTextField thresholdQuantity;

	/**
	 * Textfield for holding the order quantity of the particular product.
	 */
	private JTextField reorderQuantity;

	/**
	 * Arraylist to hold the list of category codes
	 */
	private ArrayList<String> arrCategoryCode;
	/***********************************************************/
	// Abstract Method Definition
	/***********************************************************/

	/**
	 * Callback method for confirm clicked on the dialog.
	 * @param categoryCode The category code that selected.
	 * @param name The name of the product.
	 * @param description The description of the product.
	 * @param quantity The quantity of the product.
	 * @param price The price of the product.
	 * @param barcodeNumber The barcode number associated with the product.
	 * @param reorderThreshold The threshold quantity of the product.
	 * @param reorderQuantity The reorder quantity of the product.
	 * @return Boolean to indicate whether the dialog needs to be removed from the frame or not. 
	 * If true, then the dialog will be removed.
	 */
	public abstract boolean productCallback(String categoryCode, String name, String description, String quantity,
			String price, String barcodeNumber, String reorderThreshold, String reorderQuantity);

	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * Method to set the category code list.
	 * @param arrCategoryCode The array containing the list of category codes.
	 */
	public void setCategoryCodeList(ArrayList<String> arrCategoryCode) {
		this.arrCategoryCode = arrCategoryCode;
	}
	
	/**
	 * Set the product name
	 * @param productName The product name.
	 */
	public void setProductName(String productName) {
		this.productName.setText(productName);
	}

	/**
	 * Set the product description
	 * @param productName The product description.
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription.setText(productDescription);
	}

	/**
	 * Set the product quantity
	 * @param productName The product quantity.
	 */
	public void setProductQuantity(String quantity) {
		this.quantity.setText(quantity);
	}

	/**
	 * Set the product price
	 * @param productName The product price.
	 */
	public void setProductPrice(String price) {
		this.price.setText(price);
	}

	/**
	 * Set the product threshold quantity
	 * @param productName The product threshold quantity.
	 */
	public void setThresholdQuantity(String thresholdQuantity) {
		this.thresholdQuantity.setText(thresholdQuantity);
	}

	/**
	 * Set the product reorder quantity.
	 * @param productName The product reorder quantity..
	 */
	public void setReorderQuantity(String reorderQuantity) {
		this.reorderQuantity.setText(reorderQuantity);
	}

	/***********************************************************/
	// Abstract Method Implementation
	/***********************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#
	 * getPanelToAddToDialog()
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
		thresholdQuantity = createTextFieldOnPanel(jPanel, 5);

		createLabelOnPanel(jPanel, "Order Quantity:", 6);// Move to constants
		reorderQuantity = createTextFieldOnPanel(jPanel, 6);

		return jPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#confirmClicked()
	 */
	@Override
	protected boolean confirmClicked() {
		return productCallback(null,productName.getText(), productDescription.getText(), quantity.getText(), price.getText(),
				barcodeNumber.getText(), thresholdQuantity.getText(), reorderQuantity.getText());
	}
}
