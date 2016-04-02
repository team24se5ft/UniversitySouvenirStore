package sg.edu.nus.iss.universitystore.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.test.InitializeTest;

/**
 * Data Access Object Implementation
 * 
 * @author Sanjay
 *
 * @param <T>
 */
class DataFile<T> {

	private String file;

	DataFile(String fileName) throws FileNotFoundException, IOException {
		String filePath = InitializeTest.isJUnit() ? Constants.Data.TEST_FILE_PATH : Constants.Data.FILE_PATH;
		this.file = filePath + fileName + Constants.Data.FILE_EXT;
		initialize();

	}

	/**
	 * If File does not exist an empty file is created
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		if (!exists(this.file)) {
			writeStringToFile(Constants.Common.EMPTY_STR, file);
		}
	}

	/**
	 * Create an entry to Data File
	 * 
	 * @param t
	 * @return Boolean
	 * @throws IOException
	 */
	public boolean add(T t) throws IOException {
		String content = getStringFromFile(file);
		// Add new content to file
		return writeStringToFile(content + t.toString(), file);
	}

	/**
	 * Delete an entry from the Data File
	 * 
	 * @param id
	 * @throws IOException
	 */
	public boolean delete(String id) throws IOException {
		String[] contents = getAll();
		StringBuffer newContent = new StringBuffer();
		for (String line : contents) {
			if (id.equals(line))
				continue;
			newContent.append(line);
			newContent.append(Constants.Common.NEW_LINE);
		}

		return writeStringToFile(newContent.toString(), file);

	}

	/**
	 * Add all contents to Data File
	 * 
	 * @param ct
	 * @throws IOException
	 */
	public boolean addAll(Collection<T> ct) throws IOException {
		Iterator<T> iterator = ct.iterator();
		StringBuffer content = new StringBuffer();

		while (iterator.hasNext()) {
			content.append(iterator.next());
			content.append(Constants.Common.NEW_LINE);
		}

		return writeStringToFile(content.toString(), file);
	}

	/**
	 * Delete all contents of Data File
	 * 
	 * @throws FileNotFoundException
	 */
	public boolean deleteAll() throws FileNotFoundException {
		return writeStringToFile(Constants.Common.EMPTY_STR, file);
	}

	/**
	 * Get all content of Data File
	 * 
	 * @return
	 * @throws IOException
	 */
	public String[] getAll() throws IOException {
		return getStringFromFile(file).split(Constants.Common.NEW_LINE);
	}

	/**
	 * Delete File
	 * 
	 * @return
	 */
	public boolean delete() {
		return (new File(file)).delete();
	}

	/**
	 * Check if file exists in directory
	 * 
	 * @param file
	 *            File Path and Name
	 * @return Boolean
	 * @throws IOException
	 */
	private boolean exists(String file) throws IOException {

		FileReader fr = null;
		try {
			fr = new FileReader(file);
			fr.close();
		} catch (FileNotFoundException e) {
			return false;
		}

		return true;
	}

	/**
	 * Write String Content to File
	 * 
	 * @param stringToWrite
	 * @param file
	 * @throws FileNotFoundException
	 */
	private boolean writeStringToFile(String stringToWrite, String file) throws FileNotFoundException {
		boolean status = false;
		PrintWriter writeToFile = null;
		try {
			writeToFile = new PrintWriter(file);
			writeToFile.print(stringToWrite);
			status = true;
		} finally {
			writeToFile.close();
		}
		return status;
	}

	/**
	 * Read Content from file into a string
	 * 
	 * @param file
	 * @return String Content
	 * @throws IOException
	 */
	private String getStringFromFile(String file) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = null;

		try {
			br = new BufferedReader(new FileReader(file));
			sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(Constants.Common.NEW_LINE);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

}
