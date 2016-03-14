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
	
}
