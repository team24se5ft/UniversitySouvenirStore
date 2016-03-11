package sg.edu.nus.iss.universitystore.model;

public class Member {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String identifier;
	private String name;
	private int loyaltyPoints;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the loyaltyPoints
	 */
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Member [identifier=" + identifier + ", name=" + name + ", loyaltyPoints=" + loyaltyPoints + "]";
	}
}
