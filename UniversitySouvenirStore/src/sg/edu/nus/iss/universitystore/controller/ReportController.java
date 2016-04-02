/**
 * 
 */
package sg.edu.nus.iss.universitystore.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.universitystore.constants.ViewConstants;
import sg.edu.nus.iss.universitystore.data.InventoryManager;
import sg.edu.nus.iss.universitystore.data.MemberManager;
import sg.edu.nus.iss.universitystore.data.TransactionManager;
import sg.edu.nus.iss.universitystore.exception.InventoryException;
import sg.edu.nus.iss.universitystore.exception.MemberException;
import sg.edu.nus.iss.universitystore.exception.TransactionException;
import sg.edu.nus.iss.universitystore.messages.Messages;
import sg.edu.nus.iss.universitystore.model.Category;
import sg.edu.nus.iss.universitystore.model.Member;
import sg.edu.nus.iss.universitystore.model.Product;
import sg.edu.nus.iss.universitystore.model.TransactionReport;
import sg.edu.nus.iss.universitystore.utility.TableDataUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils;
import sg.edu.nus.iss.universitystore.utility.UIUtils.DialogType;
import sg.edu.nus.iss.universitystore.validation.TransactionValidation;
import sg.edu.nus.iss.universitystore.view.intf.IReportDelegate;
import sg.edu.nus.iss.universitystore.view.subpanel.ReportPanel;
import sg.edu.nus.iss.universitystore.view.subpanel.TransactionPanel;

/**
 * @author Samrat
 *
 */
public class ReportController implements IReportDelegate {

	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * Instance of the Inventory Manager for retrieving the data from the dB.
	 */
	private InventoryManager inventoryManager;

	/**
	 * Instance of the Member Manager for retrieving the data from the dB.
	 */
	private MemberManager memberManager;

	/**
	 * Holds the reference to the view associated to show the report.
	 */
	private ReportPanel reportPanel;

	/**
	 * The list of all categories.
	 */
	private ArrayList<Category> arrCategory;

	/**
	 * The list of all products.
	 */
	private ArrayList<Product> arrProduct;

	/**
	 * The list of all products.
	 */
	private ArrayList<Member> arrMember;

	/**
	 * The list of all transaction item.
	 */
	private ArrayList<TransactionReport> arrTransaction;

	/**
	 * The reference to the main frame on which the current panel is added.
	 */
	private JFrame topFrame;

	/***********************************************************/
	// Constructors
	/***********************************************************/

	/**
	 * Inventory Controller Constructor
	 */
	public ReportController() {
		// Initialize the panel associated with this controller
		reportPanel = new ReportPanel(this);

		// Get main frame reference
		topFrame = (JFrame) SwingUtilities.getWindowAncestor(reportPanel);
	}

	/***********************************************************/
	// Getters & setters
	/***********************************************************/
	public ReportPanel getReportPanel() {
		return reportPanel;
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/
	private void initializeComponents() {
		try {
			// Initialize the instance variables.
			inventoryManager = InventoryManager.getInstance();
			memberManager = MemberManager.getInstance();
			arrCategory = inventoryManager.getAllCategories();
			arrProduct = inventoryManager.getAllProducts();
			arrMember = memberManager.getAllMembers();
			arrTransaction = TransactionManager.getInstance().getTransactionReport();

		// Update Inventory Panel with retrieved data
		reportPanel.setCategoryTableData(TableDataUtils.getFormattedCategoryListForTable(arrCategory),
				TableDataUtils.getHeadersForCategoryTable());
		reportPanel.setProductTableData(TableDataUtils.getFormattedProductListForTable(arrProduct),
				TableDataUtils.getHeadersForProductTable());
		reportPanel.setMemberTableData(TableDataUtils.getFormattedMemberListForTable(arrMember),
				TableDataUtils.getHeadersForMemberTable());
		reportPanel.setTransactionTableData(TableDataUtils.getFormattedTransactionListForTable(arrTransaction),
				TableDataUtils.getHeadersForTransactionTable());
		
		} catch (InventoryException | MemberException | TransactionException exp) {
			UIUtils.showMessageDialog(reportPanel, ViewConstants.StatusMessage.ERROR,
					exp.getMessage(), DialogType.ERROR_MESSAGE);
		}
	}

	/***********************************************************/
	// IReportDelegate
	/***********************************************************/

	/**
	 * Will be called when the report panel is visble.
	 */
	public void reportPanelVisible() {
		// Everytime the view is visible we will initialize the components.
		initializeComponents();
	}

	@Override
	public void onTransactionQuery(String startDate, String endDate) {
		// When fields are empty
		if(startDate.isEmpty() && endDate.isEmpty()){
			try {
				arrTransaction = TransactionManager.getInstance().getTransactionReport();
				reportPanel.setTransactionTableData(TableDataUtils.getFormattedTransactionListForTable(arrTransaction),
						TableDataUtils.getHeadersForTransactionTable());
			} catch (TransactionException transactionExp) {
				UIUtils.showMessageDialog(reportPanel, ViewConstants.StatusMessage.ERROR,
						transactionExp.getMessage(), DialogType.ERROR_MESSAGE);
			}
			return;
		}
		
		try {
			if(TransactionValidation.isValidData(startDate, endDate)){
				// Convert to LocalDate Java Object
				LocalDate start=LocalDate.parse(startDate);
				LocalDate end=LocalDate.parse(endDate);
				arrTransaction=TransactionManager.getInstance().getTransactionReport(start,end);
				reportPanel.setTransactionTableData(TableDataUtils.getFormattedTransactionListForTable(arrTransaction),
						TableDataUtils.getHeadersForTransactionTable());	
				if(arrTransaction.size()==0){
					UIUtils.showMessageDialog(reportPanel, ViewConstants.StatusMessage.ERROR,
							Messages.Error.Controller.NO_TRANSACTION_FOUND, DialogType.ERROR_MESSAGE);
				}
			}
		} catch (TransactionException transactionExp) {
			UIUtils.showMessageDialog(reportPanel, ViewConstants.StatusMessage.ERROR,
					transactionExp.getMessage(), DialogType.ERROR_MESSAGE);
		}
	}
}
