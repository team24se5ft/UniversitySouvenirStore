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

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.Discount;

public abstract class DiscountDialog extends BaseDialog {
	private static final long serialVersionUID = 3029306694712724442L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
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

	/**
	 * Constructor to create the dialog.
	 * 
	 * @param parent
	 *            The parent frame on top of which this dialog will be created.
	 * @param title
	 *            The title of this dialog.
	 */
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

	/**
	 * The callback which will be called when the Confirm button is tapped on the dialog.
	 * @param code The codeText to recognize the discount
	 * @param description The description for the discount
	 * @param startDate The startDate of the discount
	 * @param period   The period of the discount
	 * @param percentage   The percentage of the discount
	 * @param eligibilty  for member or for all
	 * @return
	 */
	public abstract boolean onDiscountCallBack(String code, String description, String startDate, String period,
			String percentage, String eligibilty);

	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * invoke this function when you need fill data into the dialog
	 * 
	 * @param discount use discount object as parameter
	 */
	public void setDiscountData(Discount discount) {
		code.setText(discount.getCode());
		percentage.setText(discount.getPercentage() + "");
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
	// Abstract Method Implementation
	/***********************************************************/

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.universitystore.view.dialog.BaseDialog#getPanelToAddToDialog()
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

		jpanel.add(new JLabel(ViewConstants.DialogHeaders.DISC_CODE));
		jpanel.add(code);
		jpanel.add(new JLabel(ViewConstants.DialogHeaders.OFF_PERC));
		jpanel.add(percentage);
		jpanel.add(new JLabel(ViewConstants.DialogHeaders.TYPE));
		jpanel.add(initSingleButtonGroup());
		jpanel.add(new JLabel(ViewConstants.DialogHeaders.DESCRIPTION));
		jpanel.add(description);
		jpanel.add(new JLabel(ViewConstants.DialogHeaders.START_DATE));
		jpanel.add(startDate);
		jpanel.add(new JLabel(ViewConstants.DialogHeaders.PERIOD));
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
			onDiscountCallBack(code.getText(), description.getText(), startdate, period.getText(), percentage.getText(),
					eligibility);
			return true;
		}
		return false;
	}

}
