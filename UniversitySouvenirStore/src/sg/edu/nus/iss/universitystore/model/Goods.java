package sg.edu.nus.iss.universitystore.model;

public class Goods {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	private Category category;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private int reorderThreshold;
	private int reorderQuantity;
	
	/***********************************************************/
	// Constructors
	/***********************************************************/
	public Goods(Category category, String name, String description, int quantity, double price, int reorderThreshold, int reorderQuantity){
		this.category = category;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.reorderThreshold = reorderThreshold;
		this.reorderQuantity = reorderQuantity;
	}

	/***********************************************************/
	// Getters & Setters
	/***********************************************************/
	public Category getCategory() {
		return category;
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

	public int getReorderThreshold() {
		return reorderThreshold;
	}

	public int getReorderQuantity() {
		return reorderQuantity;
	}

	/***********************************************************/
	// Public Methods
	/***********************************************************/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Goods other = (Goods) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
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
