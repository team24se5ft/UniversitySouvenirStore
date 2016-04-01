package sg.edu.nus.iss.universitystore.utility;

/**
 * @author Samrat
 *
 */
import java.util.ArrayList;

import sg.edu.nus.iss.universitystore.constants.Constants;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.Transaction;
import sg.edu.nus.iss.universitystore.model.TransactionItem;
import sg.edu.nus.iss.universitystore.model.TransactionReport;

public class TableDataUtils {

	/**
	 * Method to get the table content for Category table.
	 * 
	 * @param categoryList
	 *            The array containing categories that need to be displayed in
	 *            the table.
	 * @return The 2D string array which will be input to the table.
	 */
	public static String[][] getFormattedCategoryListForTable(ArrayList<Category> categoryList) {
		String[][] list = new String[categoryList.size()][Constants.TableData.NUMBER_OF_CATEGORY_TABLE_COLUMNS];
		for (int row = 0; row < categoryList.size(); row++) {
			Category category = categoryList.get(row);
			list[row][Constants.TableData.FIRST_COLUMN] = category.getCode();
			list[row][Constants.TableData.SECOND_COLUMN] = category.getName();
		}
		return list;
	}

	/**
	 * Method to get the table headers for category. It is important that this
	 * is in sync with the content method.
	 * 
	 * @return The string array with the table headers.
	 */
	public static String[] getHeadersForCategoryTable() {
		String[] list = new String[] { Constants.TableData.STR_CATEGORY_CODE, Constants.TableData.STR_CATEGORY_NAME };
		return list;
	}

	/**
	 * Method to get the table content for Product table.
	 * 
	 * @param productList
	 *            The array containing products that need to be displayed in the
	 *            table.
	 * @return The 2D string array which will be input to the table.
	 */
	public static String[][] getFormattedProductListForTable(ArrayList<Product> productList) {
		String[][] list = new String[productList.size()][Constants.TableData.NUMBER_OF_PRODUCT_TABLE_COLUMNS];
		for (int row = 0; row < productList.size(); row++) {
			Product product = productList.get(row);
			list[row][Constants.TableData.FIRST_COLUMN] = product.getIdentifier();
			list[row][Constants.TableData.SECOND_COLUMN] = product.getName();
			list[row][Constants.TableData.THIRD_COLUMN] = product.getDescription();
			list[row][Constants.TableData.FOURTH_COLUMN] = String.valueOf(product.getQuantity());
			list[row][Constants.TableData.FIFTH_COLUMN] = String.valueOf(product.getPrice());
			list[row][Constants.TableData.SIXTH_COLUMN] = String.valueOf(product.getBarCode());
			list[row][Constants.TableData.SEVENTH_COLUMN] = String.valueOf(product.getReorderThreshold());
			list[row][Constants.TableData.EIGHTH_COLUMN] = String.valueOf(product.getReorderQuantity());
		}
		return list;
	}

	/**
	 * Method to get the table headers for product. It is important that this is
	 * in sync with the content method.
	 * 
	 * @return The string array with the table headers.
	 */
	public static String[] getHeadersForProductTable() {
		String[] list = new String[] { Constants.TableData.STR_PRODUCT_CODE, Constants.TableData.STR_PRODUCT_NAME,
				Constants.TableData.STR_PRODUCT_DESCRIPTION, Constants.TableData.STR_PRODUCT_QUANTITY,
				Constants.TableData.STR_PRODUCT_PRICE, Constants.TableData.STR_PRODUCT_BARCODE,
				Constants.TableData.STR_PRODUCT_REORDER_THRESHOLD, Constants.TableData.STR_PRODUCT_REORDER_QUANTITY };
		return list;
	}

	/**
	 * Method to get the table content for transactionItem table
	 * 
	 * @param transactionList
	 *            The array containing products that need to be displayed in the
	 *            table.
	 * 
	 * @return The 2D string array which will be input to the table.
	 */
	public static String[][] getFormattedTransactionItemListForTable(ArrayList<TransactionItem> transactionList) {
		String[][] list = new String[transactionList
				.size()][Constants.TableData.NUMBER_OF_TRANSACTION_ITEM_TABLE_COLUMNS];
		for (int row = 0; row < transactionList.size(); row++) {
			TransactionItem item = transactionList.get(row);
			list[row][Constants.TableData.FIRST_COLUMN] = item.getProduct().getIdentifier();
			list[row][Constants.TableData.SECOND_COLUMN] = item.getProduct().getName();
			list[row][Constants.TableData.THIRD_COLUMN] = String.valueOf(item.getQuantity());
			list[row][Constants.TableData.FOURTH_COLUMN] = String.valueOf(item.getProduct().getPrice());
			list[row][Constants.TableData.FIFTH_COLUMN] = String.valueOf(item.getTotal());
		}
		return list;
	}

	/**
	 * Method to get the table headers for transactionItem. It is important that
	 * this is in sync with the content method.
	 * 
	 * @return The string array with the table headers.
	 */
	public static String[] getHeadersForTransactionItemTable() {
		String[] list = new String[] { Constants.TableData.STR_TRANSACTIONITEM_PRODUCT_IDENTIFIER,
				Constants.TableData.STR_TRANSACTIONITEM_PRODUCT_NAME,
				Constants.TableData.STR_TRANSACTIONITEM_PURCHASE_QUANTITY,
				Constants.TableData.STR_TRANSACTIONITEM_PRICE, Constants.TableData.STR_TRANSACTIONITEM_TOTAL };
		return list;
	}

	/**
	 * Method to get the table content for transaction table
	 * 
	 * @param transactionList
	 *            The array containing products that need to be displayed in the
	 *            table.
	 * 
	 * @return The 2D string array which will be input to the table.
	 */
	public static String[][] getFormattedTransactionListForTable(ArrayList<TransactionReport> transactionList) {
		String[][] list = new String[transactionList
				.size()][Constants.TableData.NUMBER_OF_TRANSACTION_ITEM_TABLE_COLUMNS];
		for (int row = 0; row < transactionList.size(); row++) {
			TransactionReport transaction = transactionList.get(row);
			list[row][Constants.TableData.FIRST_COLUMN] = transaction.getItem().getProduct().getIdentifier();
			list[row][Constants.TableData.SECOND_COLUMN] = transaction.getItem().getProduct().getName();
			list[row][Constants.TableData.THIRD_COLUMN] = transaction.getItem().getProduct().getDescription();
			list[row][Constants.TableData.FOURTH_COLUMN] = transaction.getMemberId();
			list[row][Constants.TableData.FIFTH_COLUMN] = String.valueOf(transaction.getItem().getQuantity());
			list[row][Constants.TableData.SIXTH_COLUMN] = String.valueOf(transaction.getDate());
		}
		return list;
	}

	/**
	 * Method to get the table headers for transaction. It is important that
	 * this is in sync with the content method.
	 * 
	 * @return The string array with the table headers.
	 */
	public static String[] getHeadersForTransactionTable() {
		String[] list = new String[] { Constants.TableData.STR_TRANSACTION_PRODUCT_IDENTIFIER,
				Constants.TableData.STR_TRANSACTION_PRODUCT_NAME,
				Constants.TableData.STR_TRANSACTION_PRODUCT_DESCRIPTION, Constants.TableData.STR_MEMBER_IDENTIFIER,
				Constants.TableData.STR_TRANSACTION_PURCHASE_QUANTITY, Constants.TableData.STR_TRANSACTION_DATE };
		return list;
	}

	/**
	 * Method to get the table content for Discount table.
	 * 
	 * @param productList
	 *            The array containing discounts that need to be displayed in
	 *            the table.
	 * @return The 2D string array which will be input to the table.
	 */
	public static String[][] getFormattedDiscountListForTable(ArrayList<Discount> discountList) {
		String[][] list = new String[discountList.size()][Constants.TableData.NUMBER_OF_DISCOUNT_TABLE_COLUMNS];
		for (int row = 0; row < discountList.size(); row++) {
			Discount discount = discountList.get(row);
			list[row][Constants.TableData.FIRST_COLUMN] = discount.getCode();
			list[row][Constants.TableData.SECOND_COLUMN] = discount.getDescription();
			list[row][Constants.TableData.THIRD_COLUMN] = discount.getStartDate();
			list[row][Constants.TableData.FOURTH_COLUMN] = String.valueOf(discount.getPeriod() == Constants.Data.Discount.ALWAYS_VAL ? Constants.Data.Discount.ALWAYS : discount.getPeriod());
			list[row][Constants.TableData.FIFTH_COLUMN] = String.valueOf(discount.getPercentage());
			list[row][Constants.TableData.SIXTH_COLUMN] = discount.getEligibilty();
		}
		return list;
	}

	/**
	 * Method to get the table headers for discount. It is important that this
	 * is in sync with the content method.
	 * 
	 * @return The string array with the table headers.
	 */
	public static String[] getHeadersForDiscountTable() {
		String[] list = new String[] { Constants.TableData.STR_DISCOUNT_CODE,
				Constants.TableData.STR_DISCOUNT_DESCRIPTION, Constants.TableData.STR_DISCOUNT_START_DATE,
				Constants.TableData.STR_DISCOUNT_PERIOD, Constants.TableData.STR_DISCOUNT_PERCENTAGE,
				Constants.TableData.STR_DISCOUNT_ELIGIBILITY };
		return list;
	}

	/**
	 * Method to get the table content for Member table.
	 * 
	 * @param productList
	 *            The array containing discounts that need to be displayed in
	 *            the table.
	 * @return The 2D string array which will be input to the table.
	 */
	public static String[][] getFormattedMemberListForTable(ArrayList<Member> memberList) {
		String[][] list = new String[memberList.size()][Constants.TableData.NUMBER_OF_MEMBER_TABLE_COLUMNS];
		for (int row = 0; row < memberList.size(); row++) {
			Member member = memberList.get(row);
			list[row][Constants.TableData.FIRST_COLUMN] = member.getIdentifier();
			list[row][Constants.TableData.SECOND_COLUMN] = member.getName();
			// If the value of loyalty points is less than 0(i.e. -1), we display as 0. The logic remains the same.
			if(member.getLoyaltyPoints() < 0) {
				list[row][Constants.TableData.THIRD_COLUMN] = "New Member";
			}else {
				list[row][Constants.TableData.THIRD_COLUMN] = String.valueOf(member.getLoyaltyPoints());
			}
		}
		return list;
	}

	/**
	 * Method to get the table headers for member. It is important that this is
	 * in sync with the content method.
	 * 
	 * @return The string array with the table headers.
	 */
	public static String[] getHeadersForMemberTable() {
		String[] list = new String[] { Constants.TableData.STR_MEMBER_IDENTIFIER, Constants.TableData.STR_MEMBER_NAME,
				Constants.TableData.STR_MEMBER_LOYALTY_POINTS };
		return list;
	}

}
