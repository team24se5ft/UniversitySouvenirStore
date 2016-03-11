package sg.edu.nus.iss.universitystore.model;

public class Category {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String code;
	private String name;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	public Category(String code, String name){
		this.code = code;
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
		return "Category [code=" + code + ", name=" + name + "]";
	}
}
