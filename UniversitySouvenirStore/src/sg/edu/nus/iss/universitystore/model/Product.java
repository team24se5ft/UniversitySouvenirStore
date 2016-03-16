package sg.edu.nus.iss.universitystore.model;

public class Product {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String identifier;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private int reorderThreshold;
	private int reorderQuantity;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public Product(String identifier, String name, String description, String quantity, String price,
			String reorderThreshold, String reorderQuantity) {
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.quantity = Integer.parseInt(quantity);
		this.price = Double.parseDouble(price);
		this.reorderThreshold = Integer.parseInt(reorderThreshold);
		this.reorderQuantity = Integer.parseInt(reorderQuantity);
	}

	public Product(String identifier, Goods goods) {
		this.identifier = identifier;
		this.name = goods.getName();
		this.description = goods.getDescription();
		this.quantity = goods.getQuantity();
		this.price = goods.getPrice();
		this.reorderThreshold = goods.getReorderThreshold();
		this.reorderQuantity = goods.getReorderQuantity();
	}

	/*
	 * public Product(Object[] args){ this((String)args[0], (String)args[0],
	 * (String)args[0], (Integer)args[0], (Double)args[0], (Integer)args[0],
	 * (Integer)args[0]); }
	 */

	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
		return identifier + "," + name + "," + description + "," + quantity + "," + price + "," + reorderThreshold + ","
				+ reorderQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
