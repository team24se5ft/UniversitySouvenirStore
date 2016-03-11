package sg.edu.nus.iss.universitystore.model;

public class Category {
	
	private final static int C_CODE = 0;
	private final static int C_NAME = 1;

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
	public Category(String[] args){
		this(args[C_CODE], args[C_NAME]);
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
		return code + ", " + name;
	}
}
