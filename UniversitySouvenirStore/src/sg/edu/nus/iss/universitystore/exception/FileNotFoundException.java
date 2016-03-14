package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.utility.CommonUtils.MessageTitleType;
import sg.edu.nus.iss.universitystore.utility.CommonUtils.MessageType;

public class FileNotFoundException extends StoreException {

	public FileNotFoundException(String message){
		super(MessageTitleType.ERROR, message, MessageType.ERROR_MESSAGE);
	}
}
