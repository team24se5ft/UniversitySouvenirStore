package sg.edu.nus.iss.universitystore.model.dao.data.intf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Data Access Object Interface
 * 
 * @author Sanjay
 *
 * @param <T>
 */
public interface DataFile<T> {
	
	/**
	 * Create an entry to Data File
	 * @param t
	 * @return
	 * @throws IOException 
	 */
	void add(T t) throws IOException;
	
	/**
	 * Add all contents to Data File
	 * 
	 * @param ct
	 * @throws IOException
	 */
	public void addAll(Collection<T> ct) throws IOException;
	
	/**
	 * Delete an entry from the Data File
	 * @param id
	 * @throws IOException 
	 */
	void delete(Object id) throws IOException;
	
	/**
	 * Delete all contents of Data File
	 * 
	 * @throws FileNotFoundException
	 */
	void deleteAll() throws FileNotFoundException;
	
	/**
	 * Get all content of Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	String[] getAll() throws IOException;

}
