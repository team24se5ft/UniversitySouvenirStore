package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.model.Discount;

public abstract class DiscountDialog extends BaseDialog {
	private static final long serialVersionUID = 3029306694712724442L;

	// textfield define
	private TextField code;
	private TextField percentage;
	private TextField description;
	private TextField startDate;
	private TextField period;

	// M for member,A for All
	JRadioButton randioButtonM;
	JRadioButton randioButtonA;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DiscountDialog(JFrame parent) {
		super(parent, "AddDiscount");
	}

	public DiscountDialog(JFrame parent, String title) {
		super(parent, title);
		this.addWindowListener(this);
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}
	/***********************************************************/
	// Abstract Methods Definition
	/***********************************************************/
	public abstract boolean onDiscountCallBack(String code, String description, String startDate, String period, String percentage,
			String eligibilty);

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
	/**
	 * init discount Panel
	 */
	@Override
	protected JPanel getPanelToAddToDialog() {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(6, 6));

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

		jpanel.add(new JLabel("DiscountCode"));
		jpanel.add(code);
		jpanel.add(new JLabel("OffPercentage"));
		jpanel.add(percentage);
		jpanel.add(new JLabel("type"));
		jpanel.add(initSingleButtonGroup());
		jpanel.add(new JLabel("description"));
		jpanel.add(description);
		jpanel.add(new JLabel("startDate(dd/mm):"));
		jpanel.add(startDate);
		jpanel.add(new JLabel("period:"));
		jpanel.add(period);
		return jpanel;
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
			onDiscountCallBack(code.getText(), description.getText(), startdate, period.getText(),
					percentage.getText(), eligibility);
			return true;
		}
		return false;
	}

}
