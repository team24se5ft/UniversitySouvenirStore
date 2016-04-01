package sg.edu.nus.iss.universitystore.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.MemberManager;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.validation.MemberValidation;
import sg.edu.nus.iss.universitystore.view.dialog.ConfirmationDialog;
import sg.edu.nus.iss.universitystore.view.dialog.MemberDialog;
import sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.MemberPanel;

public class MemberController implements IMemberDelegate {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * Instance of the Member Manager for retrieving the data from the dB.
	 */
	private MemberManager memberManager;

	/**
	 * Holds the reference to the view associated to show the inventory.
	 */
	private MemberPanel memberPanel;

	/**
	 * The list of all member.
	 */
	private ArrayList<Member> arrMember;

	/**
	 * The reference to the main frame on which the current panel is added.
	 */
	private JFrame topFrame;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Inventory Controller Constructor
	 */
	public MemberController() {
		try {
			// Initialize the instance variables.
			memberManager = MemberManager.getInstance();
			arrMember = memberManager.getAllMembers();
		} catch (MemberException memberExp) {
			UIUtils.showMessageDialog(memberPanel, ViewConstants.StatusMessage.ERROR,
					memberExp.getMessage(), DialogType.ERROR_MESSAGE);
		}

		// Initialize the panel associated with this controller
		memberPanel = new MemberPanel(this);

		// Update Member Panel with retrieved data
		memberPanel.updateTable(TableDataUtils.getFormattedMemberListForTable(arrMember),
				TableDataUtils.getHeadersForMemberTable());

		// Get main frame reference
		topFrame = (JFrame) SwingUtilities.getWindowAncestor(memberPanel);
	}

	/***********************************************************/
	// Getters & setters
	/***********************************************************/

	public MemberPanel getMemberPanel() {
		return memberPanel;
	}

	/***********************************************************/
	// IMemberDelegate Implementation
	/***********************************************************/

	@Override
	public void addMemberClicked() {
		MemberDialog memberDialog = new MemberDialog(topFrame, "Add Member") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean memberCallBack(String memberId, String memberName, String loyaltyPoints) {
				try {
					if(MemberValidation.isValidData(memberId, memberName)) {
						memberManager.addNewMember(memberId, memberName);
						// Show the success dialog
						UIUtils.showMessageDialog(memberPanel, ViewConstants.StatusMessage.SUCCESS,
								"Successfully added.", DialogType.INFORMATION_MESSAGE);
						// Update Table
						arrMember = memberManager.getAllMembers();
						memberPanel.updateTable(TableDataUtils.getFormattedMemberListForTable(arrMember),
								TableDataUtils.getHeadersForMemberTable());
						return true;
					}
				} catch (MemberException memberExp) {
					UIUtils.showMessageDialog(memberPanel, ViewConstants.StatusMessage.ERROR,
							memberExp.getMessage(), DialogType.ERROR_MESSAGE);
				}
				return false;
			}
		};
		// Make the category dialog visible.
		memberDialog.setVisible(true);
	}

	/*
	 * Implementing deleteMember from IMemberDelegate
	 * 
	 * @see
	 * sg.edu.nus.iss.universitystore.view.intf.IMemberDelegate#deleteMember(
	 * int)
	 */
	@Override
	public void deleteMemberClicked(int index) {
		ConfirmationDialog confirmationDialog = new ConfirmationDialog(topFrame, "Delete Member",
				"Do u really want to delete the member?") {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean confirmClicked() {
				Member member = arrMember.get(index);
				try {
					// Update the backend
					memberManager.removeMember(member.getIdentifier());
					// Update Table
					arrMember = memberManager.getAllMembers();
					memberPanel.updateTable(TableDataUtils.getFormattedMemberListForTable(arrMember),
							TableDataUtils.getHeadersForMemberTable());
				} catch (MemberException memberExp) {
					UIUtils.showMessageDialog(memberPanel, ViewConstants.StatusMessage.ERROR,
							memberExp.getMessage(), DialogType.ERROR_MESSAGE);
				}
				// Remove the dialog
				return true;
			}
		};
		confirmationDialog.setVisible(true);
	}

	@Override
	public void editMemberClicked(int index) {
		// Get the object at the index
				Member member = arrMember.get(index);
				// Implement an instance of the member dialog
				MemberDialog memberDialog = new MemberDialog(topFrame, "Edit Member") {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean memberCallBack(String memberId, String memberName, String loyaltyPoints) {
						try {
							if(MemberValidation.isValidData(memberId, memberName, loyaltyPoints) && MemberValidation.isValidLoyaltyPoint(loyaltyPoints)) {
								// If the value is valid Update the value in the dB
								Member updatedMember = new Member(memberId, memberName, loyaltyPoints);
								memberManager.updateMember(member, updatedMember);
								// Update the local copy
								arrMember = memberManager.getAllMembers();
								// Update table
								memberPanel.updateTable(TableDataUtils.getFormattedMemberListForTable(arrMember),
										TableDataUtils.getHeadersForMemberTable());
								// Dismiss the dialog OR show a success dialog
								return true;
							}
						} catch (MemberException memberExp) {
							UIUtils.showMessageDialog(memberPanel, ViewConstants.StatusMessage.ERROR,
									memberExp.getMessage(), DialogType.ERROR_MESSAGE);
						}
						return false;
					}
				};

				// Set the text of the dialog as per the object
				memberDialog.setMemberId(member.getIdentifier());
				memberDialog.setMemberName(member.getName());
				memberDialog.setLoyaltyPoints(String.valueOf(member.getLoyaltyPoints()));
				// Make the dialog visible.
				memberDialog.setDisplayLoyaltyPoints(true);
				memberDialog.setVisible(true);
	}
	
	@Override
	public void rowNotSelected() {
		// Display message for error.
				UIUtils.showMessageDialog(memberPanel, ViewConstants.StatusMessage.ERROR, ViewConstants.Controller.PLEASE_SELECT_ROW,
						DialogType.WARNING_MESSAGE);
	}
	
	@Override
	public void onMemberPanelVisible() {
		try {
			arrMember = memberManager.getAllMembers();
			memberPanel.updateTable(TableDataUtils.getFormattedMemberListForTable(arrMember),
					TableDataUtils.getHeadersForMemberTable());
		} catch (MemberException e) {
			e.printStackTrace();
		}
	}
}
