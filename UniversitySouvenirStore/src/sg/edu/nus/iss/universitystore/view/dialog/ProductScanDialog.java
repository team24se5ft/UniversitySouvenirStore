package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.universitystore.view.dialog.intf.ProductScanDialogDelegate;

public class ProductScanDialog extends BaseDialog {
	private static final long serialVersionUID = -6976311071183139160L;
	// textfield define
	private TextField ProductCode;

	private ProductScanDialogDelegate delegate;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public ProductScanDialog(JFrame parent) {
		super(parent, "ScanProdcutCode");
	}

	public ProductScanDialog(JFrame parent, String title, ProductScanDialogDelegate delegate) {
		super(parent, title);
		this.delegate = delegate;
		this.addWindowListener(this);
		this.setSize(400, 80);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

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
			delegate.onProductScanResult(ProductCode.getText());
			return false;
		}
		return false;
	}

}
