package sg.edu.nus.iss.universitystore.model;

public class Item {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private String itemID;
	private String name;
	private String description;
	private int quantity;
	private double price;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public Item(String itemID, String name, String description, int quantity, double price) {
		this.itemID = itemID;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Item(String name, String description, int quantity, double price) {
		this(null, name, description, quantity, price);
	}

	/***********************************************************/
	// Getters & Setters
	/***********************************************************/
	public String getItemID() {
		return itemID;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	@Override
	public String toString() {
		return itemID + "," + name + "," + description + "," + quantity + "," + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
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
		return true;
	}

}
