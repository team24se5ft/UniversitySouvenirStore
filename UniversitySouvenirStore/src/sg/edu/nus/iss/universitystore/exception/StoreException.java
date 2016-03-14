package sg.edu.nus.iss.universitystore.exception;

import sg.edu.nus.iss.universitystore.utility.CommonUtils.*;

public class StoreException extends Exception{
	
	private MessageTitleType title;
	private MessageType messageType;
	
	public StoreException(MessageTitleType title, String message, MessageType messageType){
		super(message);
		this.title = title;
		this.messageType = messageType;
		
	}
	
	public StoreException(String message){
		this(MessageTitleType.INFO, message, MessageType.PLAIN_MESSAGE);
	}
	
	public StoreException(){
		this("Unknown Error has occured. Please Contact your Administrator");
	}
	
	public MessageTitleType getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "[Title: " + title.getMessageTitleType() + 
				"Message: " + super.getMessage() + 
				"Message Type: " + messageType.getMessageType() + "]";
	}

	public MessageType getMessageType() {
		return messageType;
	}	

}
