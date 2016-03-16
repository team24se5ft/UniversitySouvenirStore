/**
 * 
 */
package sg.edu.nus.iss.universitystore.view.intf;

/**
 * @author Samrat
 *
 */
public interface IInventoryDelegate {

	/***********************************************************/
	// CRUD operations for Category
	/***********************************************************/
	
	/**
	 * Method to add a category.
	 */
	public void addCategoryClicked();
	
	/**
	 * Edit a category.
	 */
	public void editCategoryClicked();
	
	/**
	 * Delete a category.
	 */
	public void deleteCategoryClicked();
	
	/***********************************************************/
	// CRUD operations for Product
	/***********************************************************/
	
	/**
	 * Method to add a category.
	 */
	public void addProductClicked();
	
	/**
	 * Edit a category.
	 */
	public void editProductClicked();
	
	/**
	 * Delete a category.
	 */
	public void deleteProductClicked();
}
