package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.constants.DataConstants;
import sg.edu.nus.iss.universitystore.exception.MemberNotFound;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Member;

/**
 * Managing member data
 * 
 * @author Deepak Raaj
 *
 */

public class MemberManager {

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

	private MemberManager() throws MemberNotFound, IOException {
		initialize();
	}

	/***********************************************************/
	// Singleton
	/***********************************************************/

	/**
	 * Get a single instance of MemberManager
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static MemberManager getInstance() throws MemberNotFound, IOException, StoreException {
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
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		memberData = new DataFile<>(Constants.Data.FileName.MEMBER_DAT);
	}

	/***********************************************************/
	// Member
	/***********************************************************/

	/**
	 * (3.4.d)Adding new member
	 * 
	 * @param name
	 * @param identifier
	 * @return
	 * @throws IOException
	 */
	public boolean addNewMember(String name, String identifier) throws MemberNotFound, IOException, StoreException {
		if (isMember(identifier))
			return false;
		Member newMember = new Member(name, identifier, DataConstants.LOYALTY_NEW_MEMBER);
		memberData.add(newMember);
		return true;
	}

	/**
	 * (3.4.e.1)Finding member using id
	 * 
	 * @param identifier
	 * @return
	 * @throws IOException
	 */
	public Member getMember(String identifier) throws MemberNotFound, IOException, StoreException {
		Member memberResult = null;
		Iterator<Member> list = getMembers().iterator();
		while (list.hasNext()) {
			Member memberFound = list.next();
			if (memberFound.getIdentifier().equals(identifier)) {
				memberResult = memberFound;
			}

		}
		return memberResult;
	}

	/**
	 * (3.8.f) Displaying all member data
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Member> getMembers() throws MemberNotFound, IOException, StoreException {
		String[] membersList = memberData.getAll();
		ArrayList<Member> storedMembers = new ArrayList<>();
		for (String singleMember : membersList) {
			if (singleMember.isEmpty())
				continue;
			String[] memberSeperator = singleMember.split(DataConstants.DAT_FILE_SEPRTR);
			storedMembers.add(new Member(memberSeperator[0], memberSeperator[1], Integer.parseInt(memberSeperator[2])));

		}
		ArrayList<Member> sortedMember;
		sortedMember = sortMember(storedMembers);
		return sortedMember;

	}

	/**
	 * (3.4.c.2)checks whether member exists
	 * 
	 * @param identifier
	 * @return
	 * @throws IOException
	 */
	public boolean isMember(String identifier) throws MemberNotFound, IOException, StoreException {
		return getMember(identifier) != null;
	}

	/**
	 * Sorting member data by name
	 * 
	 * @param storedMembers
	 * @return
	 */
	public ArrayList<Member> sortMember(ArrayList<Member> storedMembers)
			throws MemberNotFound, IOException, StoreException {
		Collections.sort(storedMembers, Comparator.comparing(Member::getName).thenComparing(Member::getName));
		return storedMembers;

	}

	/**
	 * (3.4.f.1)updating name in file
	 * 
	 * @param identifier
	 * @param editedName
	 * @return
	 * @throws IOException
	 */
	public boolean updateMemberName(String identifier, String editedName)
			throws MemberNotFound, IOException, StoreException {
		boolean status = false;
		Member editMemberName;
		if (isMember(identifier)) {
			editMemberName = getMember(identifier);
			removeMember(identifier);
			editMemberName.setName(editedName);
			memberData.add(editMemberName);
			status = true;
		}
		return status;

	}

	/**
	 * ((3.4.f.2)updating loyalty points of member
	 * 
	 * @param identifier
	 * @param editedLoyalty
	 * @return
	 * @throws IOException
	 */
	public boolean updateMemberLoyalty(String identifier, String editedLoyalty)
			throws MemberNotFound, IOException, StoreException {
		boolean status = false;
		Member editMemberLoyalty;
		if (isMember(identifier)) {
			editMemberLoyalty = getMember(identifier);
			removeMember(identifier);
			editMemberLoyalty.setLoyaltyPoints(Integer.parseInt(editedLoyalty));
			memberData.add(editMemberLoyalty);
			status = true;
		}
		return status;

	}

	/**
	 * (3.4.h)Removing member object
	 * 
	 * @param identifier
	 * @return
	 * @throws IOException
	 */
	public boolean removeMember(String identifier) throws MemberNotFound, IOException, StoreException {
		boolean status = false;
		if (isMember(identifier)) {
			Member member = getMember(identifier);
			if (memberData.delete(member.toString())) {
				status = true;
			}
		}
		return status;
	}
}