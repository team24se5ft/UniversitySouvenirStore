package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.view.dialog.intf.IDiscountDialogDelegate;

public class DiscountDialog extends BaseDialog implements WindowListener {
	private static final long serialVersionUID = 3029306694712724442L;
	private int type;// 0=add,1=update

	// textfield define
	private TextField code;
	private TextField percentage;
	private TextField description;
	private TextField startDate;
	private TextField period;

	// M for member,A for All
	JRadioButton randioButtonM;
	JRadioButton randioButtonA;

	private IDiscountDialogDelegate delegate;

	/***********************************************************/
	// Constants
	/***********************************************************/
	public final static int ADD_TYPE = 0;
	public final static int UPDATE_TYPE = 1;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DiscountDialog(JFrame parent) {
		super(parent, "AddDiscount");
	}

	public DiscountDialog(JFrame parent, String title, IDiscountDialogDelegate delegate, int type) {
		super(parent, title);
		this.type = type;
		this.delegate = delegate;
		this.addWindowListener(this);
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	/**
	 * invoke this function when you need fill data into the dialog
	 * @param discount
	 */
	public void setDiscountData(Discount discount) {
		code.setText(discount.getCode());
		percentage.setText(discount.getPercentage()+"");
		description.setText(discount.getDescription());
		startDate.setText(discount.getStartDate());
		period.setText(discount.getPeriod() + "");
		if (discount.getEligibilty().equals("M")) {
			randioButtonM.setSelected(true);
			randioButtonA.setSelected(false);
		} else {
			randioButtonM.setSelected(false);
			randioButtonA.setSelected(true);
		}
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/**
	 * single button initliazaiton
	 */
	private JPanel initSingleButtonGroup() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		randioButtonM = new JRadioButton("M", true);
		randioButtonA = new JRadioButton("A");
		contentPane.add(randioButtonM);
		contentPane.add(randioButtonA);
		ButtonGroup group = new ButtonGroup();
		group.add(randioButtonM);
		group.add(randioButtonA);
		return contentPane;
	}

	/***********************************************************/
	// override method
	/***********************************************************/
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(6, 6));

		code = new TextField();
		code.setColumns(1);
		percentage = new TextField();
		percentage.setColumns(1);
		description = new TextField();
		description.setColumns(1);
		startDate = new TextField();
		startDate.setColumns(1);
		period = new TextField();
		period.setColumns(1);

		jp.add(new JLabel("DiscountCode"));
		jp.add(code);
		jp.add(new JLabel("OffPercentage"));
		jp.add(percentage);
		jp.add(new JLabel("type"));
		jp.add(initSingleButtonGroup());
		jp.add(new JLabel("description"));
		jp.add(description);
		jp.add(new JLabel("startDate(dd/mm):"));
		jp.add(startDate);
		jp.add(new JLabel("period:"));
		jp.add(period);
		return jp;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		// check discount eligibility
		String eligibility;
		if (randioButtonM.isSelected()) {
			eligibility = "M";
		} else {
			eligibility = "A";
		}
		// check date format
		String startdate = startDate.getText();
		// if (!DateUtils.checkDate()) {
		// return false;
		// }
		if (code.getText().length() != 0 && percentage.getText().length() != 0) {
			delegate.onDiscountCallBack(code.getText(), description.getText(), startdate, period.getText(),
					percentage.getText(), eligibility);
			return true;
		}
		return false;
	}

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
