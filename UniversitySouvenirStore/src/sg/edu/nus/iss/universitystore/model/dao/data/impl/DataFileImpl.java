package sg.edu.nus.iss.universitystore.model.dao.data.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import sg.edu.nus.iss.universitystore.model.dao.constants.DataConstants;
import sg.edu.nus.iss.universitystore.model.dao.data.intf.DataFile;

/**
 * Data Access Object Implementation
 * 
 * @author Sanjay
 *
 * @param <T>
 */
public class DataFileImpl<T> implements DataFile<T> {
	
	private String fileName;
	
	public DataFileImpl(String fileName) throws FileNotFoundException, IOException{
		this.fileName = DataConstants.DAT_FILE_PATH +  
				fileName + DataConstants.EXTENSION;
		initialize();
		
	}
	
	private void initialize() throws FileNotFoundException, IOException{
		if(!exists(this.fileName)){
			writeStringToFile("", fileName);
		}
	}

	@Override
	public void add(T t) throws IOException {
		String content = getStringFromFile(fileName);
		// Add new content to file
		writeStringToFile(content+t.toString(), fileName);
	}

	@Override
	public void delete(Object id) throws IOException {
		// TODO Auto-generated method stub
		String[] contents = getAll();
		StringBuffer newContent = new StringBuffer();
		for(String line : contents){
			if(id.toString().equals(line))
				continue;
			newContent.append(line);
			newContent.append(DataConstants.NEW_LINE);
		}
		// Do some manipulation
		writeStringToFile(newContent.toString(), fileName);
		
	}
	
	@Override
	public void addAll(Collection<T> ct) throws IOException{
		Iterator<T> iterator = ct.iterator();
		StringBuffer content = new StringBuffer();
		
		while(iterator.hasNext()){
			content.append(iterator.next());
			content.append(DataConstants.NEW_LINE);
		}
		
		writeStringToFile(content.toString(), fileName);
	}
	
	@Override
	public void deleteAll() throws FileNotFoundException{
		writeStringToFile("", fileName);
	}
	
	@Override
	public String[] getAll() throws IOException {
		return getStringFromFile(fileName).split(DataConstants.NEW_LINE);
	}
	
	private boolean exists(String fileName) throws IOException{
		
		FileReader fr = null;		
		try {
			fr = new FileReader(fileName);
			fr.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		
		return true;
	}
	
	private void writeStringToFile(String stringToWrite, String fileName) throws FileNotFoundException{
		PrintWriter writeToFile = null;
		try {
			writeToFile = new PrintWriter(fileName);
			writeToFile.print(stringToWrite);
		} finally {
			writeToFile.close();
		}
	}
	
	private String getStringFromFile(String fileName) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = null;
		
	    try {
	    	br = new BufferedReader(new FileReader(fileName));
	        sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        } 
	    } finally {
	        br.close();
	    }
	    return sb.toString();
	}
	
	/*public ArrayList<Model> getAllModels() throws IOException{
		String[] modelStrList = getAll();
		ArrayList<Model> model = new ArrayList<>();
		
		for(String modelStr : modelStrList){
			String[] strSplit = modelStr.split(",");
			model.add(new Model(strSplit));
		}
		
		return model;
	}*/

}
