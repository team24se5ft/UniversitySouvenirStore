package sg.edu.nus.iss.universitystore.data;

import static org.junit.Assert.*;

import java.io.IOException;

import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.utility.JUnitUtility;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.MemberException.MemberError;
import sg.edu.nus.iss.universitystore.intf.UniversityStoreJUnit;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;

/**
 * @author Deepak
 *
 */
public class MemberManagerTest extends UniversityStoreJUnit {
	
	MemberManager memberManager;

	/**
	 * Members
	 */
	private Member member1, member2;

	/**
	 * Member name
	 */
	private String memberName1, memberName2, invalidMemberName3, invalidMemberName4;
	/**
	 * Member id
	 */
	private String memberId1, memberId2, invalidMemberId3, invalidMemberId4;

	/**
	 * Member loyalty points
	 */
	private Integer memberLoyaltyPoints1, memberLoyaltyPoints2, invalidMemberLoyaltyPoints3,invalidMemberLoyaltyPoints4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();

		// First Member
		memberName1 = "Yuvraj";
		memberId1 = "Ax00uujq";
		memberLoyaltyPoints1 = -1;
		member1 = new Member(memberId1, memberName1, memberLoyaltyPoints1);

		// Invalid MemberId
		memberName2 = "Deepak";
		memberId2 = "1ghd77hh";
		memberLoyaltyPoints2 = 10;
		member2 = new Member(memberId2, memberName2, memberLoyaltyPoints2);
		// Invalid MemberName
		invalidMemberName3 = "";
		invalidMemberId3 = "bgjj88jj";
		invalidMemberLoyaltyPoints3 = 0;

		// Invalid MemberName and MemberId
		invalidMemberName4 = "Deeeepak";
		invalidMemberId4 = "";
		invalidMemberLoyaltyPoints4 = 0;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		MemberManager.deleteInstance();
		// Destroy all category content;
		member1 = member2 = null;
		memberId1 = memberId2 = invalidMemberId3 = invalidMemberId4 = null;
		memberName1 = memberName2 = invalidMemberName3 = invalidMemberName4 = null;
		memberLoyaltyPoints1 = memberLoyaltyPoints2 = invalidMemberLoyaltyPoints3 = invalidMemberLoyaltyPoints4 = null;

	}
	
	/**
	 * Initialize Discount Test
	 */
	@Test
	public void testInitializeDiscount() {
		try {
			// Instantiate Discount Manager
			memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 0);
		} catch (MemberException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}
	
	/**
	 * Test Basic addition of Member
	 */
	@Test
	public final void testAddNewMember() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.MEMBER.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);

			// Add new member
			Assert.assertTrue(memberManager.addNewMember(memberId1, memberName1));
			
			// Check Post Conditions
			Assert.assertTrue(memberManager.isMember(memberId1));
			// Find member
			Member member = memberManager.getMember(memberId1);
			Assert.assertTrue(memberManager.getAllMembers().size() == 4);
			Assert.assertEquals(member, member1);
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException memberExp) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Test the following functionalities: 
	 * 1. Addition of Two New Member
	 * 2. Update Member Two 
	 * 3. Delete a Member Two
	 * member
	 */

	@Test
	public void testMemberFunctionalities() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.MEMBER.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);
			
			// Add new member
			Assert.assertTrue(memberManager.addNewMember(memberId1, memberName1));
			// Add Second member
			Assert.assertTrue(memberManager.addNewMember(memberId2, memberName2));
			// Find Member
			Member memberOne = memberManager.getMember(memberId1);
			Member memberTwo = memberManager.getMember(memberId2);
			
			// Update Member Two
			Member updatedMemberTwo = new Member(memberTwo.getIdentifier(), memberTwo.getName(), memberLoyaltyPoints2);			
			Assert.assertTrue(memberManager.updateMember(memberTwo, updatedMemberTwo));
			memberTwo = memberManager.getMember(memberId2);
			
			// Check First Level Post Conditions
			Assert.assertTrue(memberManager.getAllMembers().size() == 5);
			Assert.assertEquals(memberOne, member1);
			Assert.assertEquals(memberTwo, member2);

			// Delete valid member
			Assert.assertTrue(memberManager.removeMember(memberId2));

			// Check First Level Post Conditions
			Assert.assertTrue(memberManager.getAllMembers().size() == 4);
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException memberExp) {
			memberExp.printStackTrace();
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}

	}
	
	/**
	 * Test Exception Addition of Duplicate Member
	 */
	@Test
	public final void testAddDuplicateMember() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.MEMBER.toString().toLowerCase() + Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);

			// Add new member
			Assert.assertTrue(memberManager.addNewMember(memberId1, memberName1));

			// Check Post Conditions
			Assert.assertTrue(memberManager.isMember(memberId1));
			// Find member
			Member member = memberManager.getMember(memberId1);
			Assert.assertTrue(memberManager.getAllMembers().size() == 4);
			Assert.assertEquals(member, member1);

			// Add same member
			Assert.assertFalse(memberManager.addNewMember(memberId1, memberName1));
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException memberExp) {
			Assert.assertEquals(MemberError.MEMBER_IDENTIFIER_ALREADY_PRESENT.toString(), memberExp.getMessage());
		}
	}
	
	/**
	 * Test Exception for Invalid Member
	 */
	@Test
	public final void testInvalidMember() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.MEMBER.toString().toLowerCase() + Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);

			// Check Post Conditions
			Assert.assertFalse(memberManager.isMember(memberId1));
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException memberExp) {
			Assert.assertEquals(MemberError.MEMBER_NOT_PRESENT_IN_FILE.toString(), memberExp.getMessage());
		}
	}
	
	/**
	 * Test Exception Invalid Delete
	 */
	@Test
	public final void testInvalidDeleteMember() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.MEMBER.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);

			// Add new member
			Assert.assertTrue(memberManager.addNewMember(memberId1, memberName1));
			
			// Check Post Conditions
			Assert.assertTrue(memberManager.isMember(memberId1));
			// Find member
			Member member = memberManager.getMember(memberId1);
			Assert.assertTrue(memberManager.getAllMembers().size() == 4);
			Assert.assertEquals(member, member1);
			
			Assert.assertTrue(memberManager.removeMember(memberId1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);
			
			Assert.assertFalse(memberManager.removeMember(memberId1));
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException memberExp) {
			Assert.assertEquals(MemberError.MEMBER_NOT_PRESENT_IN_FILE.toString(), memberExp.getMessage());
		}
	}
	
	/**
	 * Test Update Invalid Member
	 */
	@Test
	public final void testUpdateInvalidMember() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.MEMBER.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);

			// Add new member
			Assert.assertFalse(memberManager.updateMember(member1, member2));
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException memberExp) {
			Assert.assertEquals(MemberError.MEMBER_NOT_PRESENT_IN_FILE.toString(), memberExp.getMessage());
		}
	}
}
