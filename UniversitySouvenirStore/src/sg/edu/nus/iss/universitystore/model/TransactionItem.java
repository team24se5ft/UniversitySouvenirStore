/**
 * 
 */
package sg.edu.nus.iss.universitystore.model;

/**
 * @author Samrat
 *
 */
public class TransactionItem {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * The product related to this item.
	 */
	private Product product;

	/**
	 * The quantity of the current product
	 */
	private int quantity;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Constructor for creating a new transaction item.
	 * 
	 * @param product
	 *            The product for this Transaction Item.
	 * @param quantity
	 *            The quantity of the products.
	 */
	public TransactionItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * Constructor for creating a new transaction item. The quantity will be
	 * hardcoded as 1.
	 * 
	 * @param product
	 *            The product for this Transaction Item.
	 */
	public TransactionItem(Product product) {
		this(product, 1);
	}
	/***********************************************************/
	// Getters & Setters
	/***********************************************************/

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/

	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * Method to get the total of this item.
	 * 
	 * @return The total of this transaction item.
	 */
	public float getTotal() {
		return ((float) product.getPrice() * quantity);
	}
}
