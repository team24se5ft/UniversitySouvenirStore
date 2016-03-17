package sg.edu.nus.iss.universitystore.model;

public class Member {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String identifier;
	private String name;
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

	private int loyaltyPoints;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	/**
	 * @param identifier
	 * @param name
	 * @param loyaltyPoints
	 */
	public Member(String name, String identifier, int loyaltyPoints) {
		this.identifier = identifier;
		this.name = name;
		this.loyaltyPoints = loyaltyPoints;
		
	}
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	

	/*public Member(String[] split) {
		// TODO Auto-generated constructor stub
	}*/



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

	@Override
	public String toString() {
		return name+","+identifier+","+loyaltyPoints;
	}
	
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
}
