package sg.edu.nus.iss.universitystore.view.intf;

public interface IMemberDelegate {

	/***********************************************************/
	// Common
	/***********************************************************/
	/**
	 * Method will be called if the edit/delete are tapped without selecting a
	 * row of the table.
	 */
	public void rowNotSelected();

	/**
	 * Method to add a member.
	 */
	public void addMemberClicked();

	/**
	 * Edit a member.
	 * @param index The selected index of the member table.
	 */
	public void editMemberClicked(int index);
	
	/**
	 * Delete a member.
	 * @param index The selected index of the member table.
	 */
	public void deleteMemberClicked(int index);
}
