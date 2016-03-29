package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;

public abstract class ProductScanDialog extends BaseDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = -6976311071183139160L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	// textfield define
	private JTextField ProductCode;
	private JTextField Quantity;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public ProductScanDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(400, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	/***********************************************************/
	// Abstract Methods definition
	/***********************************************************/

	/**
	 * add product scan call back function
	 * 
	 * @param productCode
	 *            gain the productCode to add product
	 * @param quantity
	 *            key in quantity
	 * @return
	 */
	public abstract boolean onProductScanResult(String productCode, int quantity);

	/***********************************************************/
	// Abstract Methods Implementation
	/***********************************************************/
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jPanel = new JPanel();
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

		// ProductCode = new JTextField();
		// ProductCode.setColumns(1);
		// quantity = new JTextField();
		// quantity.setColumns(1);

		// Finally add the elements
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.PROD_CODE, 0);
		ProductCode = createTextFieldOnPanel(jPanel, 0);
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.PROD_QUANTITY, 1);
		Quantity = createTextFieldOnPanel(jPanel, 1);
		// jp.add(new JLabel(ViewConstants.DialogHeaders.PROD_CODE));
		// jp.add(ProductCode);
		// jp.add(new JLabel(ViewConstants.DialogHeaders.PROD_QUANTITY));
		// jp.add(quantity);
		return jPanel;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		// FIXME procode and quantity validaton
		onProductScanResult(ProductCode.getText(), Integer.valueOf(Quantity.getText()));
		return false;
	}

}
