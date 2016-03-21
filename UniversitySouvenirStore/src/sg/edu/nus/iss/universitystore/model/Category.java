package sg.edu.nus.iss.universitystore.model;

import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.messages.Messages;
import sg.edu.nus.iss.universitystore.utility.CommonUtils.MessageTitleType;
import sg.edu.nus.iss.universitystore.utility.CommonUtils.MessageType;

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
	/**
	 * @throws StoreException *********************************************************/
	public Category(String code, String name) throws StoreException{
		this.code = code.toUpperCase();
		validate();
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
	/***********************************************************/
	//Validate Methods
	/***********************************************************/
	/**
	 * Validate Category creation
	 * @throws StoreException 
	 */
	public void validate() throws StoreException{
		if(code.length() != 3)
			throw new StoreException(MessageTitleType.ERROR, Messages.Error.Category.INVALID_CODE_LENGTH, MessageType.ERROR_MESSAGE);
		else if(!code.matches("^[a-zA-Z]{3}$"))
			throw new StoreException(MessageTitleType.ERROR, Messages.Error.Category.INVALID_CHARACTERS, MessageType.ERROR_MESSAGE);
	}
}
