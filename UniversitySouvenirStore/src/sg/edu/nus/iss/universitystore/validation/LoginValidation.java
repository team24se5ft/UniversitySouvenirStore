package sg.edu.nus.iss.universitystore.validation;

import sg.edu.nus.iss.universitystore.exception.LoginException;
import sg.edu.nus.iss.universitystore.exception.LoginException.LoginError;

public class LoginValidation extends Validation{

	public static boolean isValidData(String username, String password) throws LoginException {
		
		if(username.isEmpty() || password.isEmpty()) {
			throw new LoginException(LoginError.ENTER_USERNAME_PASSWORD);
		}
		
		return true;
	}
	
}
