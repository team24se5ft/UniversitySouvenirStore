package sg.edu.nus.iss.universitystore.utility;

import javax.swing.JOptionPane;

public class CommonUtils {
	
	public enum MessageType{
		ERROR_MESSAGE(JOptionPane.ERROR_MESSAGE),/** Used for error messages. */
		INFORMATION_MESSAGE(JOptionPane.INFORMATION_MESSAGE),/** Used for information messages. */
		WARNING_MESSAGE(JOptionPane.WARNING_MESSAGE),/** Used for warning messages. */
		QUESTION_MESSAGE(JOptionPane.QUESTION_MESSAGE),/** Used for questions. */
		PLAIN_MESSAGE(JOptionPane.PLAIN_MESSAGE);/** No icon is used. */
		
		private int messageType;
		
		private MessageType(int messageType){
			this.messageType = messageType;
		}
		
		public int getMessageType(){
			return messageType;
		}
	}
	
	public enum MessageTitleType{
		INFO("Information"),
		WARNING("Warning"),
		ERROR("Error");
		
		private String msgTitleType;
		
		private MessageTitleType(String msgTitleType){
			this.msgTitleType = msgTitleType;
		}
		
		public String getMessageTitleType(){
			return msgTitleType;
		}
	}

}
