package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.Discount;

public abstract class DiscountDialog extends BaseDialog {
	private static final long serialVersionUID = 1L;

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	// textfield define
	private JTextField code;
	private JTextField percentage;
	private JTextField description;
	private JTextField startDate;
	private JTextField period;

	// Member or Public
	JRadioButton radioBtnMember;
	JRadioButton radioBtnPublic;

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
	 * The callback which will be called when the Confirm button is tapped on
	 * the dialog.
	 * 
	 * @param code
	 *            The codeText to recognize the discount
	 * @param description
	 *            The description for the discount
	 * @param startDate
	 *            The startDate of the discount
	 * @param period
	 *            The period of the discount
	 * @param percentage
	 *            The percentage of the discount
	 * @param eligibilty
	 *            for member or for all
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
	 * @param discount
	 *            use discount object as parameter
	 */
	public void setDiscountData(Discount discount) {
		code.setText(discount.getCode());
		percentage.setText(discount.getPercentage() + "");
		description.setText(discount.getDescription());
		startDate.setText(discount.getStartDate());
		period.setText(discount.getPeriod() + "");
		// From the backend we will get the value 'M' or 'A'.
		if (discount.getEligibilty().equals(Constants.Data.Discount.Eligibility.MEMBER)) {
			radioBtnMember.setSelected(true);
			radioBtnPublic.setSelected(false);
		} else {
			radioBtnMember.setSelected(false);
			radioBtnPublic.setSelected(true);
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
		radioBtnMember = new JRadioButton(ViewConstants.Labels.STR_MEMBER, true);
		radioBtnPublic = new JRadioButton(ViewConstants.Labels.STR_PUBLIC);
		contentPane.add(radioBtnMember);
		contentPane.add(radioBtnPublic);
		ButtonGroup group = new ButtonGroup();
		group.add(radioBtnMember);
		group.add(radioBtnPublic);
		return contentPane;
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

		addElements(jPanel);
		return jPanel;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		// check discount eligibility
		String eligibility;
		if (radioBtnMember.isSelected()) {
			// Backend takes in values of only 'M' or 'A'.
			eligibility = Constants.Data.Discount.Eligibility.MEMBER;
		} else {
			eligibility = Constants.Data.Discount.Eligibility.ALL;
		}
		// check date format
		String startdate = startDate.getText();
		return onDiscountCallBack(code.getText(), description.getText(), startdate, period.getText(),
				percentage.getText(), eligibility);
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/**
	 * Method to create the various textfields and their labels.
	 * 
	 * @param panel
	 *            The panel on which the textfields & labels need to be placed.
	 */
	private void addElements(JPanel jPanel) {
		int index = 0;

		// Discount Code
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.DISC_CODE, index);
		code = createTextFieldOnPanel(jPanel, index++);

		// Discount Percentage
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.OFF_PERC, index);
		percentage = createTextFieldOnPanel(jPanel, index++);

		// Type
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.TYPE, index);
		jPanel.add(initSingleButtonGroup(), getConstraintsForSecondColumn(index++));

		// Description
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.DESCRIPTION, index);
		description = createTextFieldOnPanel(jPanel, index++);

		// Start Date
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.START_DATE, index);
		startDate = createTextFieldOnPanel(jPanel, index++);

		// Period
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.PERIOD, index);
		period = createTextFieldOnPanel(jPanel, index++);
	}
}
