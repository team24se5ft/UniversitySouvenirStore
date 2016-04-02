package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.DiscountException.DiscountError;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.utility.DateUtils;
import sg.edu.nus.iss.universitystore.validation.DiscountValidation;

/**
 * Class Manages Discount Data File
 * 
 * @author Sanjay
 *
 */
public class DiscountManager {
	
	/***********************************************************/
	// Enums
	/***********************************************************/

	/**
	 * Discount Arguments
	 */
	public enum DiscountArg {
		CODE(0), START_DATE(1), PERIOD(2), PERCENTAGE(3), ELIGIBILITY(4), DESCRIPTION(5);

		private int position;

		private DiscountArg(int position) {
			this.position = position;
		}
	}

	/***********************************************************/
	// Instance Variables
	/***********************************************************/

	/**
	 * Instance of Discount Manager
	 */
	private static DiscountManager instance;

	/**
	 * Discount Data
	 */
	private DataFile<Discount> discountData;

	/**
	 * Member Data File Manager
	 */
	private MemberManager memberManager;

	/***********************************************************/
	// Singleton
	/***********************************************************/

	/**
	 * Get a single instance of Data File Manager
	 * 
	 * @return DataFileManager
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws StoreException
	 */
	public static DiscountManager getInstance() throws DiscountException {
		if (instance == null) {
			synchronized (DiscountManager.class) {
				if (instance == null) {
					instance = new DiscountManager();
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
	// Constructors
	/***********************************************************/

	/**
	 * Discount Manager Constructor
	 * 
	 * @throws DiscountException
	 */
	private DiscountManager() throws DiscountException {
		try {
			initialize();
			memberManager = MemberManager.getInstance();
		} catch (MemberException | IOException memberExp) {
			throw new DiscountException(DiscountError.UNKNOWN_ERROR);
		}
	}

	/***********************************************************/
	// Private Methods for Constructors
	/***********************************************************/

	/**
	 * Initialize Data for Discount
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void initialize() throws FileNotFoundException, IOException {
		discountData = new DataFile<>(Constants.Data.FileName.DISCOUNT_DAT);
	}

	/***********************************************************/
	// Validation for Discount
	/***********************************************************/

	/**
	 * Validates the format of Data in a row of Data File and if the period is
	 * >=0 and if percentage is < 100
	 * 
	 * @param dataLine
	 * @return Boolean
	 */
	private boolean isValidDiscount(String[] discountList) {
		boolean status = false;

		try {
			if (discountList.length == 6) {
				status = DiscountValidation.isValidData(discountList[DiscountArg.CODE.ordinal()],
						discountList[DiscountArg.DESCRIPTION.ordinal()], discountList[DiscountArg.START_DATE.ordinal()],
						discountList[DiscountArg.PERIOD.ordinal()], discountList[DiscountArg.PERCENTAGE.ordinal()],
						discountList[DiscountArg.ELIGIBILITY.ordinal()]);
			}
		} catch (DiscountException exception) {
			status = false;
		}

		return status;
	}

	/**
	 * Validates if the Discount is for New Member or Existing Member
	 * 
	 * @param discount
	 * @return Boolean
	 */
	private boolean isDefaultDiscount(Discount discount) {
		return discount.getCode().equals(Constants.Data.Discount.Member.Existing.CODE)
				|| discount.getCode().equals(Constants.Data.Discount.Member.New.CODE);
	}

	/**
	 * For Update Discount, Validates if Default Discounts is for New Member or
	 * Existing Member only percentage is editable
	 * 
	 * @param oldDiscount
	 * @param newDiscount
	 * @return Boolean
	 * @throws DiscountException
	 */
	private boolean updateDefaultDiscount(Discount oldDiscount, Discount newDiscount) throws DiscountException {

		if (oldDiscount.getCode().equals(Constants.Data.Discount.Member.Existing.CODE)) {
			if (!oldDiscount.equalsIgnoresPercentage(newDiscount))
				throw new DiscountException(DiscountError.EXTNGMEMBER_PERCENTAGE_ONLY);

			return true;
		} else if (oldDiscount.getCode().equals(Constants.Data.Discount.Member.New.CODE)) {
			if (!oldDiscount.equalsIgnoresPercentage(newDiscount))
				throw new DiscountException(DiscountError.NEWMEMBER_PERCENTAGE_ONLY);

			return true;
		}

		return false;
	}

	/***********************************************************/
	// Private Methods for Discount
	/***********************************************************/

	/**
	 * Get all Valid Discounts applicable for current system date
	 * 
	 * @return List of Discounts
	 * @throws IOException
	 */
	private ArrayList<Discount> getAllMemberDiscounts() throws DiscountException {
		ArrayList<Discount> discountList = getAllDiscounts();
		ArrayList<Discount> memberDiscountList = new ArrayList<>();

		for (Discount discount : discountList) {

			if (discount.getCode().equals(Constants.Data.Discount.Member.Existing.CODE)
					|| discount.getCode().equals(Constants.Data.Discount.Member.New.CODE))
				continue;

			// Checks if discount is applicable for current date
			if (!isApplicableDate(discount.getStartDate(), discount.getPeriod() - 1))
				continue;

			// Add Discount
			memberDiscountList.add(discount);
		}

		return memberDiscountList;
	}

	/**
	 * Return all discount applicable to non-members
	 * 
	 * @return List of Discounts applicable to the Public
	 * @throws IOException
	 */
	private ArrayList<Discount> getAllPublicDiscounts() throws DiscountException {
		ArrayList<Discount> discountList = getAllMemberDiscounts();
		ArrayList<Discount> publicDiscountList = new ArrayList<>();

		for (Discount discount : discountList) {
			if (discount.getEligibilty().equalsIgnoreCase(Constants.Data.Discount.Eligibility.ALL))
				publicDiscountList.add(discount);
		}

		return publicDiscountList;
	}

	/**
	 * Checks if Current Date is within the Discount Start Date and Period or if
	 * it is set as 'ALWAYS'
	 * 
	 * @param startDate
	 * @param period
	 * @return
	 */
	private boolean isApplicableDate(String startDate, int period) {
		// Checks if start date is set as 'ALWAYS'
		if (startDate.equalsIgnoreCase(Constants.Data.Discount.ALWAYS))
			return true;

		LocalDate discountStrtDate = LocalDate.parse(startDate);

		// Is applicable if Current Date is equal to or after discount date
		// And if Current Date is equal to or before the end of the period of
		// discount
		return ((discountStrtDate.isBefore(Constants.DateTime.CURRENT_DATE)
				&& discountStrtDate.plusDays(period).isAfter(Constants.DateTime.CURRENT_DATE))
				|| (discountStrtDate.isEqual(Constants.DateTime.CURRENT_DATE)
						|| discountStrtDate.plusDays(period).isEqual(Constants.DateTime.CURRENT_DATE)));

	}

	/**
	 * Splits Row of Data File into a list of Strings
	 * 
	 * @param line
	 * @return Boolean
	 */
	private String[] splitDiscountData(String discountRow) {

		return DateUtils.extractContent(discountRow, Constants.Data.Discount.Pattern.LINE_MATCH,
				Constants.Data.Discount.Pattern.DESCRIPTION_REPLACE,
				Constants.Data.Discount.Pattern.OTHER_CNTNT_REPLACE);
	}

	/***********************************************************/
	// Public Methods for Discount for Transaction Page
	/***********************************************************/

	/**
	 * Gets all discounts from Data File
	 * 
	 * @return Discount List
	 * @throws IOException
	 */
	public ArrayList<Discount> getAllDiscounts() throws DiscountException {
		ArrayList<Discount> discountList = new ArrayList<>();
		String[] discountStrLst = null;
		try {
			discountStrLst = discountData.getAll();
		} catch (IOException ioExp) {
			throw new DiscountException(DiscountError.UNKNOWN_ERROR);
		}

		for (String discountStr : discountStrLst) {

			String[] discountStrSpltLst = splitDiscountData(discountStr);

			// Checks if line in Data file is of valid
			if (!isValidDiscount(discountStrSpltLst))
				continue;

			// Add Discount
			discountList.add(new Discount(discountStrSpltLst[DiscountArg.CODE.ordinal()],
					discountStrSpltLst[DiscountArg.DESCRIPTION.ordinal()],
					discountStrSpltLst[DiscountArg.START_DATE.ordinal()],
					discountStrSpltLst[DiscountArg.PERIOD.ordinal()],
					discountStrSpltLst[DiscountArg.PERCENTAGE.ordinal()],
					discountStrSpltLst[DiscountArg.ELIGIBILITY.ordinal()]));
		}

		return discountList;
	}

	/**
	 * Get Discount Percentage for a Customer
	 * 
	 * @param member
	 * @return
	 * @throws StoreException
	 * @throws IOException
	 * @throws MemberNotFound
	 */
	public Discount getCustomerDiscount(String memberID) throws DiscountException {
		// Customer who is not a Member will not get a discount
		Discount discount = new Discount(Constants.Data.Discount.Member.Public.CODE,
				Constants.Data.Discount.Member.Public.DESCRIPTION,
				Constants.DateTime.CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Constants.Data.Discount.Member.Public.PERIOD, Constants.Data.Discount.Member.Public.DEFAULT_DISCOUNT,
				Constants.Data.Discount.Eligibility.ALL);

		// Check if member is valid
		if (memberManager.isMember(memberID)) {
			Member member;
			try {
				member = memberManager.getMember(memberID);
			} catch (MemberException exp) {
				throw new DiscountException(DiscountError.MEMBER_NOT_PRESENT_IN_FILE);
			}
			// Get Discount for New or Existing Member
			discount = member.getLoyaltyPoints() == Constants.Data.Member.LOYALTY_NEW_MEMBER
					? findDiscount(Constants.Data.Discount.Member.New.CODE)
					: findDiscount(Constants.Data.Discount.Member.Existing.CODE);
		}

		return discount;
	}

	/**
	 * Get Discount for Customer
	 * 
	 * @param memberID
	 * @return Max Discount
	 * @throws StoreException
	 * @throws IOException
	 * @throws MemberNotFound
	 */
	public Discount getDiscount(String memberID) throws DiscountException {
		Discount maxDiscount = getCustomerDiscount(memberID);
		ArrayList<Discount> discountList = memberManager.isMember(memberID) ? getAllMemberDiscounts()
				: getAllPublicDiscounts();

		for (Discount discount : discountList) {
			if (discount.getPercentage() > maxDiscount.getPercentage())
				maxDiscount = discount;
		}

		return maxDiscount;
	}

	/***********************************************************/
	// Public Methods for Discount for Discount Page
	/***********************************************************/

	/**
	 * Find Discount details
	 * 
	 * @param code
	 * @return Discount
	 * @throws DiscountException
	 */
	public Discount findDiscount(String code) throws DiscountException {
		ArrayList<Discount> discountList = getAllDiscounts();
		Discount discountFound = null;

		for (Discount discount : discountList) {
			if (discount.getCode().equals(code.toUpperCase())) {
				discountFound = discount;
				break;
			}
		}
		// If the discount is not found, then throw an exception
		if (discountFound == null) {
			throw new DiscountException(DiscountError.DISCOUNT_NOT_PRESENT_IN_FILE);
		}

		return discountFound;
	}

	/**
	 * Check if Discount code exists
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public boolean hasDiscount(String code) {
		try {
			findDiscount(code);
			return true;
		} catch (DiscountException discountExp) {
			return false;
		}
	}

	/**
	 * Add Discount
	 * 
	 * @param code
	 * @param description
	 * @param startDate
	 * @param period
	 * @param percentage
	 * @param eligibilty
	 * @return Status
	 * @throws IOException
	 */
	public boolean addDiscount(Discount discount) throws DiscountException {

		if (hasDiscount(discount.getCode())) {
			throw new DiscountException(DiscountError.DISCOUNT_ALREADY_PRESENT);
		} else {
			try {
				return discountData
						.add(new Discount(discount.getCode(), discount.getDescription(), discount.getStartDate(),
								discount.getPeriod(), discount.getPercentage(), discount.getEligibilty()));
			} catch (IOException ioExp) {
				throw new DiscountException(DiscountError.UNKNOWN_ERROR);
			}
		}
	}

	/**
	 * Delete Discount
	 * 
	 * @param code
	 * @return Status
	 * @throws IOException
	 */
	public boolean deleteDiscount(String code, boolean fromUpdate) throws DiscountException {

		if (hasDiscount(code)) {
			Discount discount = findDiscount(code);

			if (!fromUpdate && isDefaultDiscount(discount))
				throw new DiscountException(DiscountError.DEFAULT_DISCOUNT_NOT_DELETABLE);

			try {
				return discountData.delete(discount.toString());
			} catch (IOException ioExp) {
				throw new DiscountException(DiscountError.UNKNOWN_ERROR);
			}
		}
		return false;
	}

	/**
	 * Update Discount
	 * 
	 * @param oldDiscount
	 * @param newDiscount
	 * @return Boolean
	 * @throws DiscountException
	 */
	public boolean updateDiscount(Discount oldDiscount, Discount newDiscount) throws DiscountException {

		if (updateDefaultDiscount(oldDiscount, newDiscount) | deleteDiscount(oldDiscount.getCode(), true)) {
			return addDiscount(newDiscount);
		}

		return false;
	}

}
