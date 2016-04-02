package sg.edu.nus.iss.universitystore.model;

/**
 * Product Domain Object
 * 
 * @author Sanjay
 *
 */
public class Product {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String identifier;
	private String name;
	private String description;
	private int quantity;
	private String barCode;
	private double price;
	private int reorderThreshold;
	private int reorderQuantity;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	
	/**
	 * Product Constructor
	 * 
	 * @param identifier
	 * @param name
	 * @param description
	 * @param quantity
	 * @param price
	 * @param barCode
	 * @param reorderThreshold
	 * @param reorderQuantity
	 */
	public Product(String identifier, String name, String description, String quantity, String price, String barCode, 
			String reorderThreshold, String reorderQuantity) {
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.quantity = Integer.parseInt(quantity);
		this.barCode = barCode;
		this.price = Double.parseDouble(price);
		this.reorderThreshold = Integer.parseInt(reorderThreshold);
		this.reorderQuantity = Integer.parseInt(reorderQuantity);
	}

	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the BarCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the reorderThreshold
	 */
	public int getReorderThreshold() {
		return reorderThreshold;
	}

	/**
	 * @return the reorderQuantity
	 */
	public int getReorderQuantity() {
		return reorderQuantity;
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
		return identifier + "," + name + ",\"" + description + "\"," + quantity + "," + price + "," + barCode + ","
				+ reorderThreshold + "," + reorderQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + reorderQuantity;
		result = prime * result + reorderThreshold;
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
		Product other = (Product) obj;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (reorderQuantity != other.reorderQuantity)
			return false;
		if (reorderThreshold != other.reorderThreshold)
			return false;
		return true;
	}

}
