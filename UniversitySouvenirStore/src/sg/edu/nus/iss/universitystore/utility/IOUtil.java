/**
 * 
 */
package sg.edu.nus.iss.universitystore.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Samrat
 *
 */
public class IOUtil {	
	
	public void writeStringToFile(String stringToWrite, String fileName){
		String fileNameWithExtension = fileName + ".dat";
		PrintWriter writeToFile = null;
		try {
			writeToFile = new PrintWriter(fileNameWithExtension);
			writeToFile.print(stringToWrite);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			writeToFile.close();
		}
	}
	
	public String getStringFromFile(String fileName) throws IOException {
		String fileNameWithExtension = fileName + ".dat";
		BufferedReader br = null;
		StringBuilder sb = null;
	    try {
	    	br = new BufferedReader(new FileReader(fileNameWithExtension));
	        sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        } 
	    }catch (Exception e) {
				System.out.print(e.getMessage());
			}
	     finally {
	        br.close();
	    }
	    return sb.toString();
	}
}
