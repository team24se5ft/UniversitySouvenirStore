package sg.edu.nus.iss.universitystore.model;

/**
 * Vendor Domain ObjectW
 * 
 * @author Sanjay
 *
 */
public class Vendor {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String name;
	private String description;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Vendor Constructor
	 * 
	 * @param name
	 * @param description
	 */
	public Vendor(String name, String description) {
		this.name = name;
		this.description = description;
	}
	/***********************************************************/
	// Getters & Setters
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
	// Public Methods
	/***********************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "," + description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Vendor other = (Vendor) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
