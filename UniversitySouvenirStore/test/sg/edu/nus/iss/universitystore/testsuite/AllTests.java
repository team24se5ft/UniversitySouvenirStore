package sg.edu.nus.iss.universitystore.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sg.edu.nus.iss.universitystore.data.DiscountManagerTest;
import sg.edu.nus.iss.universitystore.data.InventoryManagerTest;
import sg.edu.nus.iss.universitystore.data.LoginManagerTest;
import sg.edu.nus.iss.universitystore.data.MemberManager;
import sg.edu.nus.iss.universitystore.data.MemberManagerTest;

@RunWith(Suite.class)
@SuiteClasses({DiscountManagerTest.class, InventoryManagerTest.class, LoginManagerTest.class, MemberManagerTest.class})
public class AllTests {

}
