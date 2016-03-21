package sg.edu.nus.iss.universitystore.controller.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JPanel;
import javax.xml.crypto.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.data.LoginManager;
import sg.edu.nus.iss.universitystore.model.StoreKeeper;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.view.LoginPanel;

public class LoginControllerTest {
	LoginManager loginManager1, loginManager2;
	String username,password;
	LoginPanel loginPanel;
	JPanel  jPanel;
	StoreKeeper storeKeeper;
	
	@Before
	public void setUp() throws Exception {
		loginManager1 = LoginManager.getInstance();
		loginManager2 = LoginManager.getInstance();
		storeKeeper = new StoreKeeper("LaBlah", "LaBlah1234");
		username= storeKeeper.getUserName();
		password = storeKeeper.getPassword();
	}

	@After
	public void tearDown() throws Exception {
		loginManager1 = null;
		loginManager2 = null;
		username = null;
		password = null;
	}

	@Test
	public void testLoginController(){
		assertNotNull(LoginManager.getInstance());
		assertEquals(LoginManager.getInstance(), loginManager1);
		assertEquals(loginManager2, loginManager1);
	}

	@Test
	public void testgetLoginPanel(){
		assertEquals(jPanel,loginPanel);
		//assertTrue(loginPanel.isShowing());
	}
	
	@Test
	public void testloginButtonClicked() throws IOException{
		assertEquals(storeKeeper.toString(), (username+","+password));
		assertNotNull(loginManager1.isValidCredentials(storeKeeper));
	}


}
