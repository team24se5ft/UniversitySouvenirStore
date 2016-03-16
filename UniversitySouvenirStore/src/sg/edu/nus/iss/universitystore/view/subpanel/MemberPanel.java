package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.model.*;
import sg.edu.nus.iss.universitystore.controller.MemberController;

public class MemberPanel extends JPanel{
	private JTable MemberTable;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	private ImageIcon addIcon;
	private ImageIcon editIcon;
	private ImageIcon deleteIcon;

	private JPanel buttonPanel;

	private MemberController delegate;

	public MemberPanel(MemberController memberController) {
		setBackground(Color.WHITE);
		this.delegate = memberController;
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initMemberTable();
		initButtonPanel();
	}

	private void initMemberTable() {
		String[] headers = {ViewConstants.TableHeaders.MEMBER_ID, ViewConstants.TableHeaders.MEMBER_NAME, ViewConstants.TableHeaders.MEMBER_POINTS};
		String[] content = {"Sample","A123","20"};
		String data[][] = { content };
		DefaultTableModel model = new DefaultTableModel(data, headers) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		MemberTable = new JTable(model);
		MemberTable.setRowHeight(40);
		MemberTable.setBorder(BorderFactory.createEtchedBorder());
		MemberTable.setGridColor(Color.BLACK);
		MemberTable.setIntercellSpacing(new Dimension(1, 1));
		JScrollPane JSP = new JScrollPane(MemberTable);
		add(JSP, "Center");
	}

	private void initButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		// ImageIcons with Label
		btnAdd = initImageButton("Resources/add_icon.png", ViewConstants.Labels.STR_ADD_MEMBER);
		btnEdit = initImageButton("Resources/edit_icon.png", ViewConstants.Labels.STR_EDIT_MEMBER);
		btnDelete = initImageButton("Resources/delete_icon.png", ViewConstants.Labels.STR_DELETE_MEMBER);

		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		add(buttonPanel, "South");
		initButtonEvent();
	}

	/**
	 * imageButton init here
	 * 
	 * @param imageUrl
	 * @param btnText
	 * @return
	 */
	private JButton initImageButton(String imageUrl, String btnText) {
		ImageIcon icon = new ImageIcon(imageUrl);
		JButton btn = new JButton(btnText);
		btn.setIcon(icon);
		btn.setHorizontalTextPosition(JLabel.CENTER);
		btn.setVerticalTextPosition(JLabel.BOTTOM);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
		return btn;
	}

	public void onRemoveDiscount(int row) {
		DefaultTableModel model = (DefaultTableModel) MemberTable.getModel();
		if (MemberTable.getSelectedRow() != -1) {
			model.removeRow(row);
		}
	}

	public void onAddMember(sg.edu.nus.iss.universitystore.model.Member member) {
		DefaultTableModel model = (DefaultTableModel) MemberTable.getModel();
		model.addRow(new Object[] {member.getIdentifier(),member.getName(),member.getLoyaltyPoints()});
	}

	private void initButtonEvent() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.deleteMember(MemberTable.getSelectedRow());
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addMember();
			}
		});

	}

}
