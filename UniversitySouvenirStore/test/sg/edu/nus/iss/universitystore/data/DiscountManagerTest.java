package sg.edu.nus.iss.universitystore.data;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.utility.JUnitUtility;

public class DiscountManagerTest {
	
	DiscountManager discountManager;
	Discount discount;
	
	@Before
	public void setUp() throws Exception {
		discount=new Discount("DIS","DAT","2016-03-31",5,10,"A");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDiscount() {
		try{
			// Copy Test File Discount.dat
			JUnitUtility.copyFile(Constants.Data.FileName.DISCOUNT_DAT,
				(JUnitConstants.Data.FILE_FOLDER.DISCOUNT.toString().toLowerCase()+ Constants.Data.FILE_PATH_SEPTR));
			// Instantiate Discount Manager
			discountManager = DiscountManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.DISCOUNT_DAT, 1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 0);
			
			Assert.assertTrue(discountManager.addDiscount(discount));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 1);
			
			Assert.assertEquals(discountManager.findDiscount("DIS"), discount);
			
			Assert.assertTrue(discountManager.hasDiscount("DIS"));
			Assert.assertFalse(discountManager.hasDiscount("DAT"));
			
			Assert.assertTrue(discountManager.findDiscount(code));
			
		}catch(IOException e){
			
		} catch (DiscountException e) {
			
			e.printStackTrace();
		}
	}

}
