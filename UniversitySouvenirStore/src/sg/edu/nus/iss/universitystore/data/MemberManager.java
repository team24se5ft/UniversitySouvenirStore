package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.MemberException.MemberError;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.validation.MemberValidation;

/**
 * Manages Member Data File
 * 
 * @author Deepak Raaj
 *
 */
public class MemberManager {

	/**
	 * Member Arguments
	 */
	public enum MemberArg {
		NAME(0), MEMBER_ID(1), LOYALTY_POINTS(2);

		private int position;

		private MemberArg(int position) {
			this.position = position;
		}
	}

	/***********************************************************/
	// Instance Variables
	/***********************************************************/

	/**
	 * Member instance
	 */
	private static MemberManager instance;
	/**
	 * Member Data
	 */
	private DataFile<Member> memberData;

	/***********************************************************/
	// Private Method for Constructors
	/***********************************************************/

	/**
	 * Member Constructor
	 * 
	 * @throws MemberException
	 */
	private MemberManager() throws MemberException {
		try {
			initialize();
		} catch (IOException e) {
			throw new MemberException(MemberError.UNKNOWN_ERROR);
		}
	}

	/***********************************************************/
	// Singleton
	/***********************************************************/

	/**
	 * Get a single instance of MemberManager
	 * 
	 * @return The instance of MemberManager
	 * @throws MemberException
	 */
	public static MemberManager getInstance() throws MemberException {
		if (instance == null) {
			synchronized (MemberManager.class) {
				if (instance == null) {
					instance = new MemberManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Delete instance of Data File Manager
	 */
	public static void deleteInstance() {
		instance = null;
	}

	/***********************************************************/
	// Private Methods for Constructors
	/***********************************************************/

	/**
	 * Initialize Member Data File
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		memberData = new DataFile<>(Constants.Data.FileName.MEMBER_DAT);
	}

	/***********************************************************/
	// Validation for Discount
	/***********************************************************/

	/**
	 * Validates Member Data
	 * 
	 * @param dataStrList
	 * @return Boolean
	 * @throws MemberException
	 */
	private boolean isValidMemberData(String[] dataStrList) {
		try {
			return MemberValidation.isValidData(dataStrList[MemberArg.MEMBER_ID.ordinal()],
					dataStrList[MemberArg.NAME.ordinal()], dataStrList[MemberArg.LOYALTY_POINTS.ordinal()]);
		} catch (MemberException memberExp) {
			return false;
		}
	}

	/***********************************************************/
	// Public Methods for Member
	/***********************************************************/

	/**
	 * Adding new member
	 * 
	 * @param name
	 * @param identifier
	 * @return Boolean
	 * @throws IOException
	 */
	public boolean addNewMember(String identifier, String name) throws MemberException {
		// Check if the member already exists
		if (isMember(identifier)) {
			throw new MemberException(MemberError.MEMBER_IDENTIFIER_ALREADY_PRESENT);
		} else {
			Member newMember = new Member(identifier, name, Constants.Data.Member.LOYALTY_NEW_MEMBER);
			try {
				memberData.add(newMember);
			} catch (IOException e) {
				throw new MemberException(MemberError.UNKNOWN_ERROR);
			}
			return true;
		}
	}

	/**
	 * Finding member using id
	 * 
	 * @param identifier
	 * @return Member, if member not found return null
	 * @throws MemberException
	 */
	public Member getMember(String identifier) throws MemberException {
		// First check the list
		Member memberResult = null;
		Iterator<Member> list = getAllMembers().iterator();
		while (list.hasNext()) {
			Member memberFound = list.next();
			if (memberFound.getIdentifier().equals(identifier)) {
				memberResult = memberFound;
				break;
			}
		}

		// If the member was not found, we throw an exception
		if (memberResult == null) {
			throw new MemberException(MemberError.MEMBER_NOT_PRESENT_IN_FILE);
		}

		return memberResult;
	}

	/**
	 * Displaying all member data
	 * 
	 * @return List of Members
	 * @throws IOException
	 */
	public ArrayList<Member> getAllMembers() throws MemberException {
		String[] membersList;
		// Read all the data from file
		try {
			membersList = memberData.getAll();
		} catch (IOException e) {
			throw new MemberException(MemberError.UNKNOWN_ERROR);
		}
		// Store all the values in an array list.
		ArrayList<Member> storedMembers = new ArrayList<Member>();
		for (String singleMember : membersList) {

			String[] memberStrSpltLst = singleMember.split(Constants.Data.FILE_SEPTR);

			if (memberStrSpltLst.length != 3 || !isValidMemberData(memberStrSpltLst))
				continue;

			storedMembers.add(new Member(memberStrSpltLst[MemberArg.MEMBER_ID.ordinal()],
					memberStrSpltLst[MemberArg.NAME.ordinal()], memberStrSpltLst[MemberArg.LOYALTY_POINTS.ordinal()]));
		}
		return storedMembers;
	}

	/**
	 * (3.4.c.2)checks whether member exists If the
	 * 
	 * @param identifier
	 * @return
	 * @throws MemberException
	 */
	public boolean isMember(String identifier) {
		try {
			// The below line will throw an exception if the member is not
			// present.
			getMember(identifier);
			return true;
		} catch (MemberException e) {
			return false;
		}
	}

	/**
	 * Method to update an existing member. The update will only take place if
	 * the member is already present.
	 * 
	 * @param oldMember
	 *            The old member object that needs to be updated.
	 * @param updatedMember
	 *            The new member object.
	 * @return true if successful, else false
	 */
	public boolean updateMember(Member oldMember, Member updatedMember) throws MemberException {
		// The below line will throw an exception if the member is not present.
		if (isMember(oldMember.getIdentifier())) {
			removeMember(oldMember.getIdentifier());
			try {
				memberData.add(updatedMember);
				return true;
			} catch (IOException e) {
				throw new MemberException(MemberError.UNKNOWN_ERROR);
			}
		} else {
			throw new MemberException(MemberError.MEMBER_NOT_PRESENT_IN_FILE);
		}
	}

	/**
	 * Removing member object
	 * 
	 * @param identifier
	 * @return Boolean
	 * @throws IOException
	 */
	public boolean removeMember(String identifier) throws MemberException {
		// Check if the member exists.
		if (isMember(identifier)) {
			Member member = getMember(identifier);
			try {
				memberData.delete(member.toString());
				return true;
			} catch (Exception e) {
				throw new MemberException(MemberError.UNKNOWN_ERROR);
			}
		} else {
			throw new MemberException(MemberError.MEMBER_NOT_PRESENT_IN_FILE);
		}
	}
}