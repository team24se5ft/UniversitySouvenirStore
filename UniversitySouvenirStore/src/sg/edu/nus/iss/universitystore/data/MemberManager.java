/**
 * 
 */
package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import sg.edu.nus.iss.universitystore.data.DataFile;

import sg.edu.nus.iss.universitystore.constants.Constants;

import sg.edu.nus.iss.universitystore.constants.DataConstants;
import sg.edu.nus.iss.universitystore.model.Member;

/**
 * @author Deepak Raaj
 *
 */

public class MemberManager {
	private static MemberManager instance;
	private DataFile<Member> memberData;

	private MemberManager() throws FileNotFoundException, IOException {
		initialize();
	}

	public static MemberManager getInstance() throws FileNotFoundException, IOException {
		if (instance == null) {
			synchronized (MemberManager.class) {
				if (instance == null) {
					instance = new MemberManager();
				}
			}
		}
		return instance;
	}

	private void initialize() throws FileNotFoundException, IOException {
		memberData = new DataFile<>(Constants.Data.FileName.MEMBER_DAT);
	}

	/**
	 * Get All members in Data File
	 * 
	 * @return List of all Categories
	 * @throws IOException
	 */
	public ArrayList<Member> getAllMembers() throws IOException {
		String[] memberList = memberData.getAll();
		ArrayList<Member> members = new ArrayList<>();

		for (String memberLine : memberList) {

			if (memberLine.isEmpty())
				continue;
			String[] membersList = memberLine.split(DataConstants.DAT_FILE_SEPRTR);

			members.add(new Member(membersList[0], membersList[1], Integer.parseInt(membersList[2])));
		}

		return members;
	}

	/**
	 * Search member using identifier
	 * 
	 * @return member name and loyalty points
	 * @throws IOException
	 */
	public String memberSearch(String identifier) throws IOException {
		ArrayList<Member> member = getAllMembers();
		for (Member memberLine : member) {
			if (memberLine.getIdentifier().equals(identifier)) {
				return memberLine.getName() + DataConstants.DAT_FILE_SEPRTR + memberLine.getLoyaltyPoints();
			}

		}

		return DataConstants.MEMBER_NOT_FOUND;
	}

	/**
	 * Add member by providing name Randomly generate member id
	 * 
	 * @throws IOException
	 */

	public void addNewMember(String name) throws IOException {
		String identifier = UUID.randomUUID().toString().substring(0, 6);
		memberData.add(new Member(name, identifier, DataConstants.LOYALTY_NEW_MEMBER));
	}

	/**
	 * Delete member object by providing member id
	 * 
	 * @throws IOException
	 */

	public void deleteMember(String identifier) throws IOException {

		ArrayList<Member> member = getAllMembers();
		for (Member memberLine : member) {
			if (memberLine.getIdentifier().equals(identifier)) {
				memberData.delete(memberLine);
			} else {
				System.out.println(DataConstants.MEMBER_NOT_FOUND);
			}

		}

	}

}
