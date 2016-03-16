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

import sg.edu.nus.iss.universitystore.view.dialog.intf.AddDiscountDialogDelegate;

public class AddDiscountDialog extends OkCancelDialog implements WindowListener {
	private static final long serialVersionUID = 3029306694712724442L;

	//textfield define
	private TextField code;  
	private TextField percentage; 
	private TextField description;
	private TextField startDate;
	private TextField period;

	//M for member,A for All
	JRadioButton randioButtonM;
	JRadioButton randioButtonA;

	private AddDiscountDialogDelegate delegate;

	/***********************************************************/
	//Constructors
	/***********************************************************/
	public AddDiscountDialog(JFrame parent) {
		super(parent, "AddDiscount");
	}

	public AddDiscountDialog(JFrame parent, String title, AddDiscountDialogDelegate delegate) {
		super(parent, title);
		this.delegate = delegate;
		this.addWindowListener(this);
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}
	
	
	/***********************************************************/
	//Private Methods
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
	//override method
	/***********************************************************/
	@Override
	protected Panel createFormPanel() {
		Panel jp = new Panel();
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
	protected boolean performOkAction() {
		// check discount eligibility
		String eligibility;
		if (randioButtonM.isSelected()) {
			eligibility = "M";
		} else {
			eligibility = "A";
		}
		// check date format
		String startdate = startDate.getText();
//		if (!DateUtils.checkDate()) {
//			return false;
//		}
		if (code.getText().length() != 0 && percentage.getText().length() != 0) {
			delegate.onAddDiscount(code.getText(), description.getText(), startdate, period.getText(), percentage.getText(),
					eligibility);
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
