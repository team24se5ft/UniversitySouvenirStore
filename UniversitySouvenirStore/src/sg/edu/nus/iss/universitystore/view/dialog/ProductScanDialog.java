package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ProductScanDialog extends BaseDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = -6976311071183139160L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	// textfield define
	private TextField ProductCode;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public ProductScanDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(400, 80);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}
	
	/***********************************************************/
	// Abstract Methods definition
	/***********************************************************/
	/**
	 * add product scan call back function
	 * @param productCode gain the productCode to add product
	 * @return
	 */
	public abstract boolean onProductScanResult(String productCode);

	/***********************************************************/
	// Abstract Methods Implementation
	/***********************************************************/
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1, 2));

		ProductCode = new TextField();
		ProductCode.setColumns(1);

		jp.add(new JLabel("productCode:"));
		jp.add(ProductCode);
		return jp;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		if (ProductCode.getText().length() != 0) {
			onProductScanResult(ProductCode.getText());
			return false;
		}
		return false;
	}

}
