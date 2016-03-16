package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmDialog;
import sg.edu.nus.iss.universitystore.view.dialog.MemberDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.MemberDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.MemberPanel;

public class MemberController implements IMemberDelegate {
	private MemberPanel memberPanel;
	private ArrayList<Member> memberList;

	public MemberController() {
		memberPanel = new MemberPanel(this);
		memberList = new ArrayList<Member>();
		for (int i = 0; i < 2; i++) {
			Member e=new Member("testMember","123456",100);
			memberList.add(e);
		}
	}

	public MemberPanel getmemberPanel() {
		return memberPanel;
	}

	@Override
	public void addMember() {
		new MemberDialog((JFrame) SwingUtilities.getWindowAncestor(memberPanel), "AddMember",
				new MemberDialogDelegate() {

					@Override
					public void MemberCallBack(String memberId, String memberName, String loyaltyPoints) {
						Member member = new Member(memberId, memberName, Integer.valueOf(loyaltyPoints));
						// TODO dataModify
						// UIupdate
						memberPanel.onAddMember(member);
					}
				}).setVisible(true);
	}

	// TODO keep a list of discount in the controller and modify according to
	// given row.
	@Override
	public void deleteMember(int row) {
		if (row < 0) {
			return;
		}
		new ConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(memberPanel), "ConfirmDialog",
				"Do u really want to delete row " + (row + 1)) {

			@Override
			protected boolean confirmClicked() {
				// TODO dataModify
				// UIupdate
				memberPanel.onRemoveDiscount(row);
				return true;
			}
		}.setVisible(true);
	}

	@Override
	public void onaddMember() {
		// TODO Auto-generated method stub
		
	}
}
