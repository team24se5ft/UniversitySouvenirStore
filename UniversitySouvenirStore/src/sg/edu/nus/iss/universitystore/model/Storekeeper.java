package sg.edu.nus.iss.universitystore.model;

public class Storekeeper {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String name;
	private String password;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
		return "Storekeeper [name=" + name + ", password=" + password + "]";
	}
}
