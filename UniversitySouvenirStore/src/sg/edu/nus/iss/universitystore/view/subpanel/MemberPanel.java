package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate;

public class MemberPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JTable memberTable;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	private JPanel buttonPanel;

	private IMemberDelegate delegate;// to communicate with member
										// controller

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public MemberPanel(IMemberDelegate delegate) {
		setBackground(Color.WHITE);
		this.delegate = delegate;
		BorderLayout borderLayout = new BorderLayout();
		// set height and width gap
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initmemberTable();
		initButtonPanel();
		// add(new JLabel("member"));
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/
	private void initmemberTable() {
		String[] headers = { "Member ID", "Name", "LoyaltyPoints" };
		String data[][] = {  };
		// init table model
		DefaultTableModel model = new DefaultTableModel(data, headers) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		memberTable = new JTable(model);
		memberTable.setRowHeight(40);
		memberTable.setBorder(BorderFactory.createEtchedBorder());
		memberTable.setGridColor(Color.BLACK);
		memberTable.setIntercellSpacing(new Dimension(1, 1));
		JScrollPane JSP = new JScrollPane(memberTable);
		add(JSP, "Center");
	}

	/**
	 * init ButtonPanle which include add,update and delete
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		btnAdd = new JButton(new ImageIcon("Resources/add_icon.png"));
		btnEdit = new JButton(new ImageIcon("Resources/edit_icon.png"));
		btnDelete = new JButton(new ImageIcon("Resources/delete_icon.png"));

		// setting border to empty
		btnAdd.setBorder(BorderFactory.createEmptyBorder());
		btnEdit.setBorder(BorderFactory.createEmptyBorder());
		btnDelete.setBorder(BorderFactory.createEmptyBorder());

		btnAdd.setPreferredSize(new Dimension(70, 70));
		btnEdit.setPreferredSize(new Dimension(70, 70));
		btnDelete.setPreferredSize(new Dimension(70, 70));

		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		add(buttonPanel, "South");
		initButtonEvent();
	}


	/**
	 * all the memberPanel button`s event init here
	 */
	private void initButtonEvent() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.deleteMember(memberTable.getSelectedRow());
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addMember();
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.updateMember(memberTable.getSelectedRow());
			}
		});

	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	
	/**
	 * entrance of member tableData,should be invoke by controller
	 * @param list
	 */
	public void setMemberTableData(ArrayList<Member> list){
		for(Member entity:list){
			onAddMember(entity);
		}
	}

	/**
	 * after confirm in dialog to remove item
	 * 
	 * @param row
	 *            which row should be deleted
	 */
	public void onRemoveMember(int row) {
		DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
		if (memberTable.getSelectedRow() != -1) {
			model.removeRow(row);
		}
	}

	/**
	 * after click ok in AddmemberDialog to addMember
	 * 
	 * @param member
	 */
	public void onAddMember(Member member) {
		DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
		model.addRow(new Object[] { member.getIdentifier(), member.getName(), member.getLoyaltyPoints() });
	}

	/**
	 * after clicking ok in ModifyMemberDialog to updateMember
	 * 
	 * @param member
	 */
	public void onUpdateMember(Member member, int row) {
		DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
		model.removeRow(row);
		model.insertRow(row,
				new Object[] {member.getIdentifier(), member.getName(), member.getLoyaltyPoints()});
	}
	
	public void updateUI(String[] header,String[][] content){
		DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
		model.setDataVector(content, header);
	}
}
