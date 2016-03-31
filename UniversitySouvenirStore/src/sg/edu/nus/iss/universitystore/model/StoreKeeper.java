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
		return userName + "," + password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreKeeper other = (StoreKeeper) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
}
