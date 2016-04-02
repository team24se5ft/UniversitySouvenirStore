package sg.edu.nus.iss.universitystore.view.dialog;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;

public abstract class MemberScanDialog extends BaseDialog {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = -6976311071183139160L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	// textfield define
	private JTextField memberCode;

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
	public MemberScanDialog(JFrame parent, String title) {
		// Call the parent
		super(parent, title);
		// Customize this dialog
		this.addWindowListener(this);
		this.setSize(400, 200);// TODO: Move to constants
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
	}

	/**
	 * check the memberCode and return result
	 * 
	 * @param MemberCode
	 */
	public abstract boolean onMemberIdentification(String MemberCode);

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

		// Keep track of the index.
		int index = 0;

		// Finally add the elements
		createLabelOnPanel(jPanel, ViewConstants.DialogHeaders.MEMBER_CODE, index);
		memberCode = createTextFieldOnPanel(jPanel, index++);
		
		return jPanel;
	}

	/**
	 * when Ok button be clicked
	 */
	@Override
	protected boolean confirmClicked() {
		return onMemberIdentification(memberCode.getText());
	}

}
