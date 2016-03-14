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
	
	public Product(String identified, Goods goods){
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
		return "Product [identifier=" + identifier + ", name=" + name + ", description=" + description + ", quantity="
				+ quantity + ", price=" + price + ", reorderThreshold=" + reorderThreshold + ", reorderQuantity="
				+ reorderQuantity + "]";
	}
}
