package sg.edu.nus.iss.universitystore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.exception.MemberNotFound;
import sg.edu.nus.iss.universitystore.exception.StoreException;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;

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
		CODE(0), DESCRIPTION(1), START_DATE(2), PERIOD(3), PERCENTAGE(4), ELIGIBILITY(5);

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
	public static DiscountManager getInstance() throws FileNotFoundException, IOException, StoreException {
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
	public DiscountManager() throws FileNotFoundException, IOException, MemberNotFound, StoreException {
		initialize();
		memberManager = MemberManager.getInstance();
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
	// Public Methods for Discount for Transaction Page
	/***********************************************************/

	/**
	 * Gets all discounts from Data File
	 * 
	 * @return Discount List
	 * @throws IOException
	 */
	public ArrayList<Discount> getAllDiscounts() throws IOException {
		ArrayList<Discount> discountList = new ArrayList<>();
		String[] discountStrLst = discountData.getAll();

		for (String discountStr : discountStrLst) {

			// Checks if line in Data file is invalid, skip line
			if (!isValidData(discountStr))
				continue;

			String[] discountStrSplt = discountStr.split(Constants.Data.FILE_SEPTR);

			// Add Discount
			discountList.add(new Discount(discountStrSplt[DiscountArg.CODE.ordinal()],
					discountStrSplt[DiscountArg.DESCRIPTION.ordinal()],
					discountStrSplt[DiscountArg.START_DATE.ordinal()], discountStrSplt[DiscountArg.PERIOD.ordinal()],
					discountStrSplt[DiscountArg.PERCENTAGE.ordinal()],
					discountStrSplt[DiscountArg.ELIGIBILITY.ordinal()]));
		}

		return discountList;
	}

	/**
	 * Checks if the data line is of valid format and if the period is <=365 and
	 * if percentage is < 100
	 * 
	 * @param dataLine
	 * @return Boolean
	 */
	private boolean isValidData(String dataLine) {
		boolean isValid = false;

		// If data line matches format
		if (dataLine.matches(Constants.Data.Discount.Pattern.LINE_MATCH)) {
			String[] dataLineSplt = dataLine.split(Constants.Data.FILE_SEPTR);
			int period = Integer.parseInt(dataLineSplt[DiscountArg.PERIOD.ordinal()]);
			float percentage = Float.parseFloat(dataLineSplt[DiscountArg.PERCENTAGE.ordinal()]);

			// Is Valid if period within range 0 to 365
			// Is Valid if percentage is within range 1 to 100
			isValid = (period >= 0 && period <= 365) && (percentage > 0 && percentage <= 100);
		}
		return isValid;
	}

	/**
	 * (3.2.c) Get all Valid Discounts applicable for current system date
	 * 
	 * @return List of Discounts
	 * @throws IOException
	 */
	public ArrayList<Discount> getAllMemberDiscounts() throws IOException {
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
	public ArrayList<Discount> getAllPublicDiscounts() throws IOException {
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
	public Discount getCustomerDiscount(String memberID) throws MemberNotFound, IOException, StoreException {
		// Customer who is not a Member will not get a discount
		Discount discount = new Discount(Constants.Data.Discount.Member.Public.CODE,
				Constants.Data.Discount.Member.Public.DESCRIPTION,
				Constants.DateTime.CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Constants.Data.Discount.Member.Public.PERIOD, Constants.Data.Discount.Member.Public.DEFAULT_DISCOUNT,
				Constants.Data.Discount.Eligibility.ALL);

		// Check if member is valid
		if (memberManager.isMember(memberID)) {
			Member member = memberManager.getMember(memberID);

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
	public Discount getDiscount(String memberID) throws MemberNotFound, IOException, StoreException {
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
	public Discount findDiscount(String code) throws IOException {
		ArrayList<Discount> discountList = getAllDiscounts();
		Discount discountFound = null;

		for (Discount discount : discountList) {
			if (discount.getCode().equals(code.toUpperCase())) {
				discountFound = discount;
				break;
			}
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
	public boolean hasDiscount(String code) throws IOException {
		return findDiscount(code) != null;
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
	public boolean addDiscount(Discount discount) throws IOException {

		if (hasDiscount(discount.getCode()))
			return false;

		return discountData.add(new Discount(discount.getCode(), discount.getDescription(), discount.getStartDate(),
				discount.getPeriod(), discount.getPercentage(), discount.getEligibilty()));
	}

	/**
	 * Delete Discount
	 * 
	 * @param code
	 * @return Status
	 * @throws IOException
	 */
	public boolean deleteDiscount(String code) throws IOException {

		if (!hasDiscount(code))
			return false;

		Discount discount = findDiscount(code);

		return discountData.delete(discount.toString());
	}

	/**
	 * Update Discount
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
	public boolean updateDiscount(Discount oldDiscount, Discount newDiscount) throws IOException {
		boolean status = false;

		if (deleteDiscount(oldDiscount.getCode())) {
			status = addDiscount(newDiscount);
		}

		return status;
	}

}
