package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmationDialog;
import sg.edu.nus.iss.universitystore.view.dialog.MemberDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.IMemberDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.MemberPanel;

public class MemberController implements IMemberDelegate {
	private MemberPanel memberPanel;
	private ArrayList<Member> memberList;

	public MemberController() {
		memberPanel = new MemberPanel(this);
		memberList = new ArrayList<Member>();
		for (int i = 0; i < 2; i++) {
			Member e=new Member("A12345", "Sample", -1);
			memberList.add(e);
		}
		memberPanel.setMemberTableData(memberList);
	}

	public MemberPanel getMemberPanel() {
		return memberPanel;
	}
    /**
     * Implementing addMember from IMemberDelegate
     */
	@Override
	public void addMember() {
		new MemberDialog((JFrame) SwingUtilities.getWindowAncestor(memberPanel), "AddMember",
				new IMemberDialogDelegate() {
					@Override
					public void MemberCallBack(String memberId, String memberName, String loyaltyPoints) {
						// TODO Auto-generated method stub
						Member member = new Member(memberId,memberName,Integer.valueOf(loyaltyPoints));
						// TODO dataModify
						memberList.add(member);
						// UIupdate
						memberPanel.onAddMember(member);
					}

				}, MemberDialog.ADD_TYPE).setVisible(true);
	}

    /*
     * Implementing deleteMember from IMemberDelegate
     * @see sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate#deleteMember(int)
     */
	@Override
	public void deleteMember(int row) {
		if (row < 0) {
			return;
		}
		new ConfirmationDialog((JFrame) SwingUtilities.getWindowAncestor(memberPanel), "ConfirmDialog",
				"Do u really want to delete row " + (row + 1)) {
					private static final long serialVersionUID = 1L;

			@Override
			protected boolean confirmClicked() {
				// TODO dataModify
				memberList.remove(row);
				// UIupdate
				memberPanel.onRemoveMember(row);
				return true;
			}
		}.setVisible(true);
	}

	/**
	 * Implementing updateMember from IMemberDelegate
	 */
	@Override
	public void updateMember(int row) {
		// TODO Auto-generated method stub
		if (row < 0) {
			return;
		}
		MemberDialog updateDlg=new MemberDialog((JFrame) SwingUtilities.getWindowAncestor(memberPanel), "UpdateMember",
				new IMemberDialogDelegate() {

					@Override
					public void MemberCallBack(String memberId, String memberName, String loyaltyPoints) {
						// TODO Auto-generated method stub
						Member member = new Member(memberId, memberName, Integer.valueOf(loyaltyPoints));
						// TODO dataModify
						memberList.remove(row);
						memberList.add(row,member);
						// UIupdate
						memberPanel.onUpdateMember(member, row);
						
					}

				}, MemberDialog.UPDATE_TYPE);
		updateDlg.setMemberData(memberList.get(row));
		updateDlg.setVisible(true);
		
	}
}
