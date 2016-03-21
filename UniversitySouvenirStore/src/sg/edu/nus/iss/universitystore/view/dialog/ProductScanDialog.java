package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ProductScanDialog extends BaseDialog {
	private static final long serialVersionUID = -6976311071183139160L;
	// textfield define
	private TextField ProductCode;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public ProductScanDialog(JFrame parent) {
		super(parent, "ScanProdcutCode");
	}

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
	 * add member dialog call back function
	 */
	public abstract boolean onProductScanResult(String productCode);

	/***********************************************************/
	// override method
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
