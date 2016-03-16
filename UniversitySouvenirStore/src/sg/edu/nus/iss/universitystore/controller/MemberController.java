package sg.edu.nus.iss.universitystore.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.view.dialog.AddDiscountDialog;
import sg.edu.nus.iss.universitystore.view.dialog.AddMemberDialog;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmDialog;
import sg.edu.nus.iss.universitystore.view.dialog.intf.AddDiscountDialogDelegate;
import sg.edu.nus.iss.universitystore.view.dialog.intf.AddMemberDialogDelegate;
import sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.MemberPanel;

public class MemberController implements IMemberDelegate {
	private MemberPanel memberPanel;

	public MemberController() {
		memberPanel = new MemberPanel(this);
	}

	public MemberPanel getmemberPanel() {
		return memberPanel;
	}

	@Override
	public void addMember() {
		new AddMemberDialog((JFrame) SwingUtilities.getWindowAncestor(memberPanel), "AddMember",
				new AddMemberDialogDelegate() {

					@Override
					public void onAddMember(String memberId, String memberName, String loyaltyPoints) {
						Member member = new Member(memberId, memberName, Integer.valueOf(loyaltyPoints));
						// TODO dataModify
						// UIupdate
						memberPanel.onAddMember(member);
					}
				}).setVisible(true);
		;
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
			protected boolean performOkAction() {
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
