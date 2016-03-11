package sg.edu.nus.iss.universitystore.model;

public class Product {

	/***********************************************************/
	//Instance Variables
	/***********************************************************/
	private String identifier;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private int reorderThreshold;
	private int reorderQuantity;
	
	/***********************************************************/
	//Constructors
	/***********************************************************/
	
	
	/***********************************************************/
	//Getters & Setters
	/***********************************************************/
	
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
	//Public Methods
	/***********************************************************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [identifier=" + identifier + ", name=" + name + ", description=" + description + ", quantity="
				+ quantity + ", price=" + price + ", reorderThreshold=" + reorderThreshold + ", reorderQuantity="
				+ reorderQuantity + "]";
	}
}
