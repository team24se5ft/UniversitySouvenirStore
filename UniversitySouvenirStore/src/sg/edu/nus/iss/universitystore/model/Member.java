package sg.edu.nus.iss.universitystore.model;

/**
 * Member Domain Object
 * 
 * @author Deepak
 *
 */
public class Member {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * The identifier of the member.
	 */
	private String identifier;

	/**
	 * Name of the member.
	 */
	private String name;

	/**
	 * Loyalty points accumulated for the member.
	 */
	private int loyaltyPoints;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Member Constructor
	 * 
	 * @param identifier
	 * @param name
	 * @param loyaltyPoints
	 */
	public Member(String identifier, String name, int loyaltyPoints) {
		this.identifier = identifier;
		this.name = name;
		this.loyaltyPoints = loyaltyPoints;

	}

	public Member(String identifier, String name, String loyaltyPoints) {
		this.identifier = identifier;
		this.name = name;
		this.loyaltyPoints = Integer.parseInt(loyaltyPoints);

	}

	/**
	 * Constructor for creating a new member instance.
	 * This will be used when creating a new member.
	 * @param identifier The identifier of the member
	 * @param name The name of the member
	 */
	public Member(String identifier, String name) {
		this(identifier, name, 0);
	}
	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param loyaltyPoints
	 *            the loyaltyPoints to set
	 */
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

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
	// Public Methods
	/***********************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "," + identifier + "," + loyaltyPoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + loyaltyPoints;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Member other = (Member) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (loyaltyPoints != other.loyaltyPoints)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
