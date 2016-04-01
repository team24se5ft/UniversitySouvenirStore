package sg.edu.nus.iss.universitystore.data;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.Constants.Data.Discount.Eligibility;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.DiscountException.DiscountError;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.intf.UniversityStoreJUnit;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.utility.JUnitUtility;

/**
 * JUnit for Discount Manager
 * 
 * @author Sanjay
 *
 */
public class DiscountManagerTest extends UniversityStoreJUnit {

	DiscountManager discountManager;
	MemberManager memberManager;

	String discountCode1, discountCode2, invldDiscountCode;
	String discountPercentage1, discountPercentage2;
	String discountStartDate1, discountStartDate2;
	String discountDesc1, discountDesc2;
	String discountPeriod1, discountPeriod2;

	String memberID;
	String memberName;

	Discount discount1, discount2, invldDiscount, discountDefault;

	Member member;

	@Before
	public void setUp() throws Exception {
		super.setUp();

		// Member
		memberID = "ABZW123KL";
		memberName = "Abzsde Klaoel";
		member = new Member(memberID, memberName);

		// Discount 1
		discountCode1 = "GME_WEEK";
		discountDesc1 = "Games Week";
		discountStartDate1 = "2016-03-31";
		discountPercentage1 = "10";
		discountPeriod1 = "7";
		discount1 = new Discount(discountCode1, discountDesc1, discountStartDate1, discountPeriod1, discountPercentage1,
				Eligibility.MEMBER);

		// Discount 2
		discountCode2 = "APP_WEEK";
		discountDesc2 = "Applicances Week";
		discountStartDate2 = "2016-03-31";
		discountPercentage2 = "20";
		discountPeriod2 = "7";
		discount2 = new Discount(discountCode2, discountDesc2, discountStartDate2, discountPeriod2, discountPercentage2,
				Eligibility.ALL);

		// Invalid Code
		invldDiscountCode = "INVALID_DISCOUNT";

		discountDefault = new Discount(Constants.Data.Discount.Member.Public.CODE,
				Constants.Data.Discount.Member.Public.DESCRIPTION,
				Constants.DateTime.CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Constants.Data.Discount.Member.Public.PERIOD, Constants.Data.Discount.Member.Public.DEFAULT_DISCOUNT,
				Constants.Data.Discount.Eligibility.ALL);

	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();

		discountCode1 = discountCode2 = invldDiscountCode = null;
		discountPercentage1 = discountPercentage2 = null;
		discountStartDate1 = discountStartDate2 = null;
		discountDesc1 = discountDesc2 = null;
		discountPeriod1 = discountPeriod2 = null;

		discount1 = discount2 = invldDiscount = discountDefault = null;

		memberID = null;
		memberName = null;

		DiscountManager.deleteInstance();
		MemberManager.deleteInstance();
	}

	/**
	 * Initialize Discount Test
	 */
	@Test
	public void testInitializeDiscount() {
		try {
			// Instantiate Discount Manager
			discountManager = DiscountManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.DISCOUNT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 0);
		} catch (DiscountException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Testing following Scenarios which throws no Exceptions: 
	 * 1. Add Discount
	 * 2. Update Discount 
	 * 3. Delete Discount 
	 * 4. Delete Same Discount
	 */
	@Test
	public void testDiscountScenario() {
		try {
			// Copy Test File Discount.dat
			JUnitUtility.copyFile(Constants.Data.FileName.DISCOUNT_DAT,
					(JUnitConstants.Data.FILE_FOLDER.DISCOUNT.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR));
			// Get Instant of Discount Manager
			discountManager = DiscountManager.getInstance();
			// Get Instant of Member Manager
			memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.DISCOUNT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);

			// Add Member
			Assert.assertTrue(memberManager.addNewMember(memberID, memberName));

			// Add new discount
			Assert.assertTrue(discountManager.addDiscount(discount1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 4);

			// Get Discount for Public Members
			Assert.assertEquals(discountDefault,
					discountManager.getDiscount(Constants.Data.Discount.Member.Public.CODE));
			// Get Discount for Member
			Assert.assertTrue(discountManager.getDiscount(memberID).getPercentage() == 20);

			// Update new discount
			Assert.assertTrue(discountManager.updateDiscount(discount1, discount2));
			Assert.assertTrue(discountManager.hasDiscount(discount2.getCode()));

			// Get Discount for Public Members
			Assert.assertTrue(
					discountManager.getDiscount(Constants.Data.Discount.Member.Public.CODE).getPercentage() == 20);

			// Delete new discount
			Assert.assertTrue(discountManager.deleteDiscount(discount2.getCode(), false));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);

			// Delete Invalid discount
			Assert.assertFalse(discountManager.deleteDiscount(discount1.getCode(), false));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException | DiscountException exp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Testing Exception Discount already added
	 */
	@Test
	public void testAddIdenticalDiscount() {
		try {
			// Copy Test File Discount.dat
			JUnitUtility.copyFile(Constants.Data.FileName.DISCOUNT_DAT,
					(JUnitConstants.Data.FILE_FOLDER.DISCOUNT.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR));
			// Get Instant of Discount Manager
			discountManager = DiscountManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.DISCOUNT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);

			// Add New Discount
			Assert.assertTrue(discountManager.addDiscount(discount1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 4);

			// Add Same Discount
			Assert.assertTrue(discountManager.addDiscount(discount1));
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (DiscountException discExp) {
			Assert.assertEquals(DiscountError.DISCOUNT_ALREADY_PRESENT.toString(), discExp.getMessage());
		}
	}

	/**
	 * Testing Exception Default Discount cannot be deleted
	 */
	@Test
	public void testDeleteDefaultDiscount() {
		try {
			// Copy Test File Discount.dat
			JUnitUtility.copyFile(Constants.Data.FileName.DISCOUNT_DAT,
					(JUnitConstants.Data.FILE_FOLDER.DISCOUNT.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR));
			// Get Instant of Discount Manager
			discountManager = DiscountManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.DISCOUNT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);

			// Delete Invalid discount
			Assert.assertFalse(discountManager.deleteDiscount(invldDiscountCode, false));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (DiscountException discExp) {
			Assert.assertEquals(DiscountError.DEFAULT_DISCOUNT_NOT_DELETABLE.toString(), discExp.getMessage());
		}
	}

	@Test
	public void testUpdateDefaultDiscount() {
		try {
			// Copy Test File Discount.dat
			JUnitUtility.copyFile(Constants.Data.FileName.DISCOUNT_DAT,
					(JUnitConstants.Data.FILE_FOLDER.DISCOUNT.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR));
			// Get Instant of Discount Manager
			discountManager = DiscountManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.DISCOUNT_DAT, 1));
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(discountManager.getAllDiscounts().size() == 3);

			Discount defaultDiscount = discountManager.findDiscount(Constants.Data.Discount.Member.Existing.CODE);

			Discount defaultDiscountUpdated = new Discount(Constants.Data.Discount.Member.Existing.CODE, discountDesc2,
					discountStartDate2, discountPeriod2, discountPercentage2, Eligibility.MEMBER);

			// Update new discount
			Assert.assertTrue(discountManager.updateDiscount(defaultDiscount, defaultDiscountUpdated));
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (DiscountException discExp) {
			Assert.assertEquals(DiscountError.EXTNGMEMBER_PERCENTAGE_ONLY.toString(), discExp.getMessage());
		}
	}

}
