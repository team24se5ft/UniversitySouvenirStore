package sg.edu.nus.iss.universitystore.model;

public class Vendor {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String name;
	private String description;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	public Vendor(String name, String description){
		this.name = name;
		this.description = description;
	}
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vendor [name=" + name + ", description=" + description + "]";
	}
}
