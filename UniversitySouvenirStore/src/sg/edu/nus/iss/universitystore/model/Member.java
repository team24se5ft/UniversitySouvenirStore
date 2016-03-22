package sg.edu.nus.iss.universitystore.model;

public class Member {

	/***********************************************************/
	//Instance Variables
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
	//Constructors
	/***********************************************************/
	/**
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
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param loyaltyPoints the loyaltyPoints to set
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
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name+","+identifier+","+loyaltyPoints;
	}
}
