package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import sg.edu.nus.iss.universitystore.model.Item;

public class CartManager {
	
	private static CartManager instance;
	
	private static HashMap<String,Item> Cart;
	
	private CartManager() {
		
		}
	
	
	/**
	 * Get a single instance of Cart Manager
	 * 
	 * @return CartManager
	 */
	public static CartManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new CartManager();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Delete instance of Cart Manager
	 */
	public static void deleteInstance() {
		instance = null;
		Cart.clear();
	}
	
	/*
	 * 
	 * Adding Item object to ArrayList
	 * 
	 */
	
	public void addToCart(Item item){
		Cart.put(item.getName(),item);
	}
	
	/*
	 * 
	 * Removing item from ArrayList
	 * 
	 */
	
	public void removeFromCart()
	{
		
	}
}
