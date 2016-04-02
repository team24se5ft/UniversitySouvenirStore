package sg.edu.nus.iss.universitystore.data;



import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.LoginException;
import sg.edu.nus.iss.universitystore.exception.LoginException.LoginError;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.utility.JUnitUtility;

public class LoginManagerTest {

	StoreKeeper storeKeeper1, storeKeeper2;
	LoginManager loginManager;
	
	@Before
	public void setUp() throws Exception {
		storeKeeper1=new StoreKeeper("1","1");
		storeKeeper2=new StoreKeeper("2","2");
	}

	@After
	public void tearDown() throws Exception {
		loginManager.deleteInstance();
		storeKeeper1=null;
		storeKeeper2=null;
	}

	@Test
	public void test() {
		try{
			// Copy Test File Category.dat
			JUnitUtility.copyFile(Constants.Data.FileName.STORE_KEEPER_DAT,
								(JUnitConstants.Data.FILE_FOLDER.STOREKEEPER.toString().toLowerCase()+ Constants.Data.FILE_PATH_SEPTR));

			// Instantiate Inventory Manager
			loginManager = LoginManager.getInstance();
			
			Assert.assertTrue(loginManager.isValidCredentials(storeKeeper1));
			
			Assert.assertFalse(loginManager.isValidCredentials(storeKeeper2));
		}
		catch (LoginException e) {
			Assert.assertEquals(LoginError.INVALID_CREDENTIALS.toString(),e.getMessage());
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
		
	}

}
