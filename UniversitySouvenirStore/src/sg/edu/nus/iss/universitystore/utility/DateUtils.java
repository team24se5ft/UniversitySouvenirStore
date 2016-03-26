package sg.edu.nus.iss.universitystore.utility;

import java.util.ArrayList;
import java.util.Arrays;

import sg.edu.nus.iss.universitystore.constants.Constants;

public class DateUtils {
	
	public static String[] extractContent(String line, String matchPattern, String despPattern, String othrCntPattern){
				
		String description = line.replaceAll(matchPattern, despPattern);
		String otherContent = line.replaceAll(matchPattern, othrCntPattern);
		
		ArrayList<String> strSpltArray = new ArrayList<>(Arrays
				.asList(otherContent.split(Constants.Data.FILE_SEPTR)));

		strSpltArray.add(description);		

		return strSpltArray.toArray(new String[strSpltArray.size()]);
	}

}
