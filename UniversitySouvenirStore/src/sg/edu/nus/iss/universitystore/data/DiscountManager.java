package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.data.DiscountManager.DiscountArg;
import sg.edu.nus.iss.universitystore.exception.DiscountException;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.DiscountException.DiscountError;
import sg.edu.nus.iss.universitystore.exception.MemberException.MemberError;
import sg.edu.nus.iss.universitystore.exception.StoreException;
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

	/**
	 * New Member Discount
	 */
	private float newMemberDiscount;

	/**
	 * Member Discount
	 */
	private float memberDiscount;

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
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws StoreException
	 * @throws MemberNotFound
	 */
	private DiscountManager() throws DiscountException {
		try {
			initialize();
			memberManager = MemberManager.getInstance();
		} catch (MemberException | IOException memberExp) {
			throw new DiscountException(DiscountError.UNKNOWN_ERROR);
		}

		this.newMemberDiscount = Constants.Data.Discount.Member.New.DEFAULT_DISCOUNT;
		this.memberDiscount = Constants.Data.Discount.Member.Existing.DEFAULT_DISCOUNT;
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
	// Getters & Setters
	/***********************************************************/

	/**
	 * (3.2.a.1) Set New Member Discount
	 * 
	 * @param discountPercentage
	 */
	public void setNewMemberDiscount(int discountPercentage) {
		this.newMemberDiscount = discountPercentage;
	}

	/**
	 * (3.2.b.1) Set Member Discount
	 * 
	 * @param discountPercentage
	 */
	public void setMemberDiscount(int discountPercentage) {
		this.memberDiscount = discountPercentage;
	}

	/**
	 * (3.2.a) Get New Member Discount
	 * 
	 * @return Discount
	 */
	public Discount getNewMemberDiscount() {
		return new Discount(Constants.Data.Discount.Member.New.CODE, Constants.Data.Discount.Member.New.DESCRIPTION,
				Constants.DateTime.CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Constants.Data.Discount.Member.New.PERIOD, this.newMemberDiscount,
				Constants.Data.Discount.Eligibility.MEMBER);
	}

	/**
	 * (3.2.b) Get Member Discount
	 * 
	 * @return Discount
	 */
	public Discount getMemberDiscount() {
		return new Discount(Constants.Data.Discount.Member.Existing.CODE,
				Constants.Data.Discount.Member.Existing.DESCRIPTION,
				Constants.DateTime.CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Constants.Data.Discount.Member.Existing.PERIOD, this.memberDiscount,
				Constants.Data.Discount.Eligibility.MEMBER);
	}

	/***********************************************************/
	// Validation for Discount
	/***********************************************************/

	/**
	 * Validates the format of Data in a row of Data File and
	 * if the period is <=365 and if percentage is < 100
	 * 
	 * @param dataLine
	 * @return Boolean
	 */
	private boolean isValidDiscount(String[] discountList) {
		boolean status = false;

		try {
			if (discountList.length == 6) {
				status = DiscountValidation.isValidData(discountList[DiscountArg.CODE.ordinal()],
						discountList[DiscountArg.DESCRIPTION.ordinal()],
						discountList[DiscountArg.START_DATE.ordinal()], discountList[DiscountArg.PERIOD.ordinal()],
						discountList[DiscountArg.PERCENTAGE.ordinal()],
						discountList[DiscountArg.ELIGIBILITY.ordinal()]);
			}
		} catch (DiscountException exception) {
			status = false;
		}

		return status;
	}

	/***********************************************************/
	// Private Methods for Discount
	/***********************************************************/

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
	 * (3.2.c) Get all Valid Discounts applicable for current system date
	 * 
	 * @return List of Discounts
	 * @throws IOException
	 */
	public ArrayList<Discount> getAllMemberDiscounts() throws DiscountException {
		ArrayList<Discount> discountList = getAllDiscounts();
		ArrayList<Discount> memberDiscountList = new ArrayList<>();

		for (Discount discount : discountList) {

			// Checks if discount is applicable for current date
			if (!isApplicableDate(discount.getStartDate(), discount.getPeriod()))
				continue;

			// Add Discount
			memberDiscountList.add(discount);
		}

		return memberDiscountList;
	}

	/**
	 * Checks if Current Date is within the Discount Start Date and Period
	 * 
	 * @param startDate
	 * @param period
	 * @return
	 */
	private boolean isApplicableDate(String startDate, int period) {
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
	 * (3.2.c) Return all discount applicable to non-members
	 * 
	 * @return List of Discounts applicable to the Public
	 * @throws IOException
	 */
	public ArrayList<Discount> getAllPublicDiscounts() throws DiscountException {
		ArrayList<Discount> discountList = getAllMemberDiscounts();
		ArrayList<Discount> publicDiscountList = new ArrayList<>();

		for (Discount discount : discountList) {
			if (discount.getEligibilty().equalsIgnoreCase(Constants.Data.Discount.Eligibility.ALL))
				publicDiscountList.add(discount);
		}

		return publicDiscountList;
	}

	/**
	 * (3.2.a, 3.2.b) Get Discount Percentage for a Customer
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
			discount = member.getLoyaltyPoints() == Constants.Data.Member.LOYALTY_NEW_MEMBER ? getNewMemberDiscount()
					: getMemberDiscount();
		}

		return discount;
	}

	/**
	 * (3.2.d) Get Discount for Customer
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
	 * @throws IOException
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
	public boolean deleteDiscount(String code) throws DiscountException {

		if (hasDiscount(code)) {
			Discount discount = findDiscount(code);
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
		if (deleteDiscount(oldDiscount.getCode())) {
			return addDiscount(newDiscount);
		}
		return false;
	}
}
