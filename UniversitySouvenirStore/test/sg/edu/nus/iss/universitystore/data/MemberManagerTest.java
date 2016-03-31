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
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.intf.UniversityStoreJUnit;
import sg.edu.nus.iss.universitystore.messages.JUnitMessages;

/**
 * @author Deepak
 *
 */
public class MemberManagerTest extends UniversityStoreJUnit {

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
		memberLoyaltyPoints1 = 25;
		member1 = new Member(memberName1, memberId1, memberLoyaltyPoints1);

		// Invalid MemberId
		memberName2 = "Deepak";
		memberId2 = "1ghd77hh";
		memberLoyaltyPoints2 = 10;
		member2 = new Member(memberName2, memberId2, memberLoyaltyPoints2);
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
		memberLoyaltyPoints1 = memberLoyaltyPoints2 = invalidMemberLoyaltyPoints3 = invalidMemberLoyaltyPoints4 = 0;

	}

	/**
	 * Test method for
	 * {@link sg.edu.nus.iss.universitystore.data.MemberManager#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link sg.edu.nus.iss.universitystore.data.MemberManager#addNewMember(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testAddNewMember() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);

			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 1);

			// Add new member
			Assert.assertTrue(memberManager.addNewMember(memberName1, memberId1));
			// Find member
			Member member = memberManager.getMember(memberId1);

			// Check Post Conditions
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 3));
			Assert.assertEquals(member, member1);
		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}
	}

	/**
	 * Test the following functionalities: 1. Addition of One New Member Twice
	 * 2. Check for invalid Member 4. Delete a member 5. Delete an invalid
	 * member
	 */

	@Test
	public void testCategoryFunctionalities() {
		try {
			// Copy Test File Member.dat
			JUnitUtility.copyFile(Constants.Data.FileName.MEMBER_DAT,
					JUnitConstants.Data.FILE_FOLDER.INVENTORY.toString().toLowerCase()
							+ Constants.Data.FILE_PATH_SEPTR);
			// Instantiate Inventory Manager
			MemberManager memberManager = MemberManager.getInstance();

			// Check Initial Conditions
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 1));
			Assert.assertTrue(memberManager.getAllMembers().size() == 1);

			// Add new member
			Assert.assertTrue(memberManager.addNewMember(memberName1, memberId1));
			// Add the same member
			Assert.assertFalse(memberManager.addNewMember(memberName1, memberId1));
			// Add Second member
			Assert.assertTrue(memberManager.addNewMember(memberName2, memberId2));
			// Find Member
			Member memberOne = memberManager.getMember(memberId1);
			Member memberTwo = memberManager.getMember(memberId2);
			Assert.assertEquals(memberManager.getMember(invalidMemberId3), null);

			// Check First Level Post Conditions
			Assert.assertTrue(memberManager.getAllMembers().size() == 3);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 3));
			Assert.assertEquals(memberOne, member1);
			Assert.assertEquals(memberTwo, member2);

			// Delete valid member
			Assert.assertTrue(memberManager.removeMember(memberId2));

			// Check First Level Post Conditions
			Assert.assertTrue(memberManager.getAllMembers().size() == 2);
			Assert.assertTrue(JUnitUtility.checkFileCount(Constants.Data.FileName.MEMBER_DAT, 2));

			// Delete invalid member
			Assert.assertFalse(memberManager.removeMember(invalidMemberId4));

		} catch (IOException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		} catch (MemberException e) {
			fail(JUnitMessages.Error.JUNIT_FAIL);
		}

	}
}
