package sg.edu.nus.iss.universitystore.validation;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.MemberException.MemberError;

/**
 * Validation for Member
 * 
 * @author Sanjay
 *
 */
public class MemberValidation extends Validation{

	public static boolean isValidData(String memberId, String memberName, String loyaltyPoints) throws MemberException {

		if (memberId.isEmpty() || memberName.isEmpty() || loyaltyPoints.isEmpty()) {
			throw new MemberException(MemberError.EMPTY_MEMBER_FIELDS);
		}
		
		if(!loyaltyPoints.matches(Constants.Data.Member.Pattern.LOYALTY_POINTS_MATCH)) {
			throw new MemberException(MemberError.INVALID_LOYALTY_POINTS);
		}

		return isValidData(memberId, memberName);
	}
	
	public static boolean isValidData(String memberId, String memberName) throws MemberException {

		if (memberId.isEmpty() || memberName.isEmpty()) {
			throw new MemberException(MemberError.ADD_EMPTY_MEMBER_FIELDS);
		}
		
		if(!memberId.matches(Constants.Data.Member.Pattern.MEMBER_ID_MATCH)) {
			throw new MemberException(MemberError.INVALID_MEMBER_ID);
		}
		
		if(!isName(memberName)) {
			throw new MemberException(MemberError.INVALID_MEMBER_NAME);
		}

		return true;
	}
	
	public static boolean isValidLoyaltyPoint(String loyaltyPoints) throws MemberException {
		
		if(loyaltyPoints.isEmpty() || !isNumber(loyaltyPoints))
			throw new MemberException(MemberError.INVALID_LOYALTY_POINTS);
		
		return true;
	}
}
