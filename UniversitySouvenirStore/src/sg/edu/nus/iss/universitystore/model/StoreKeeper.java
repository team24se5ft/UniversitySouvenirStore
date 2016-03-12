package sg.edu.nus.iss.universitystore.model;

public class StoreKeeper {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String userName;
	private String password;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	public StoreKeeper(String userName,String password) {
		this.userName = userName;
		this.password = password;
	}
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	
	/**
	 * @return the name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Storekeeper [name=" + userName + ", password=" + password + "]";
	}
}
