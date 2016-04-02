package sg.edu.nus.iss.universitystore.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sg.edu.nus.iss.universitystore.data.DiscountManagerTest;
import sg.edu.nus.iss.universitystore.data.InventoryManagerTest;
import sg.edu.nus.iss.universitystore.data.LoginManagerTest;
import sg.edu.nus.iss.universitystore.data.MemberManagerTest;
import sg.edu.nus.iss.universitystore.data.TransactionManagerTest;

/**
 * Added All JUnits
 * 
 * @author Sanjay
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ DiscountManagerTest.class, InventoryManagerTest.class, LoginManagerTest.class,
		MemberManagerTest.class, TransactionManagerTest.class })
public class AllTests {

}
