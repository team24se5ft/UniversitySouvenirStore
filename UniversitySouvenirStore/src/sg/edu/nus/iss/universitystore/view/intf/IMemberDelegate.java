package sg.edu.nus.iss.universitystore.view.intf;

public interface IMemberDelegate {
	
		/**
		 * show dialog to add member
		 */
		public void addMember();
	    /**
	     * 
	     * @param row
	     */
		public void deleteMember(int row);
		public void onaddMember();

}


