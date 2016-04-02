package sg.edu.nus.iss.universitystore.model;

/**
 * Category Domain Object
 * 
 * @author Sanjay
 *
 */
public class Category {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String code;
	private String name;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	/**
	 * Category Constructor
	 * @param code
	 * @param name
	 */
	public Category(String code, String name) {
		this.code = code.toUpperCase();
		this.name = name;
	}
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/***********************************************************/
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return code + "," + name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Category other = (Category) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
