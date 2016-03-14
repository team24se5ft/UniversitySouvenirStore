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
			// Integer.ParseInt used since loyalty points parameter is in int
			// type
			// type

			members.add(new Member(membersList[0], membersList[1], Integer.parseInt(membersList[2])));
		}
		// System.out.println(members.toString());

		return members;
	}

	/**
	 * Search member using identifier
	 * 
	 * @return member name and loyalty points
	 * @throws IOException
	 */
	public String memberSearch(String identifier) throws IOException {
		// String value="";
		ArrayList<Member> member = getAllMembers();
		for (Member memberLine : member) {
			/*
			 * System.out.println("our identifier "+identifier+" line1::
			 * memberLine.getIdentifier()); System.out.println("boolean ::
			 * memberLine.getIdentifier().equals(identifier));
			 */
			if (memberLine.getIdentifier().equals(identifier)) {
				String value = memberLine.getName() + "," + memberLine.getLoyaltyPoints();
				return value;
			}

		}
		// System.out.println(value);

		return "member not found";
	}

	/**
	 * Add member by providing name Randomly generate member id
	 * 
	 * @throws IOException
	 */

	public void addNewMember(String name) throws IOException {
		int loyaltyPoints = -1;
		String identifier = UUID.randomUUID().toString().substring(0, 6);
		memberData.add(new Member(name, identifier, loyaltyPoints));
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
				System.out.println("member not found");
			}

		}
		// System.out.println(value);

	}

	/**
	 * Checked values using main method for this class alone
	 * 
	 * @return member name and loyalty points if id given
	 * @throws FileNotFoundException,IOException
	 */
	/*
	 * public static void main(String[] args) throws FileNotFoundException,
	 * IOException { MemberManager m=new MemberManager(); m.addNewMember("dee");
	 * m.getAllMembers(); String
	 * value=m.memberSearch("9896c2a4-64f1-4a28-a0fa-312537492775");
	 * System.out.println(value); m.deleteMember("cf617");
	 * 
	 * }
	 */

}
