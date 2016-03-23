package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sg.edu.nus.iss.universitystore.view.intf.IReportDelegate;

public class ReportPanel extends JPanel{
	
	/***********************************************************/
	// Constants
	/***********************************************************/
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	/**
	 * The main tabbed pane of the screen. 
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Panel which displays all the functionalities related to the category.
	 */
	private CategoryPanel categoryPanel;

	/**
	 * Panel which displays all the functionalities related to the product.
	 */
	private ProductPanel productPanel;

	/**
	 * Panel which displays all the functionalities related to the member.
	 */
	private MemberPanel memberPanel;
	
	/**
	 * Delegate for informing the controller about the various events.
	 */
	private IReportDelegate delegate;
	/***********************************************************/
	// Constructors
	/***********************************************************/
	public ReportPanel(IReportDelegate delegate){
		// Set the delegate
		this.delegate = delegate;
		// Set layout of main component
		BorderLayout borderLayout = new BorderLayout();
		// Height & width gap
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		setLayout(borderLayout);

		// Start adding other components
		categoryPanel = new CategoryPanel(null);
		categoryPanel.removeButtonPanel();
		
		productPanel = new ProductPanel(null);
		productPanel.removeButtonPanel();
		
		memberPanel = new MemberPanel(null);
		memberPanel.removeButtonPanel();
		
		createTabbedPane(categoryPanel, productPanel, memberPanel);
		
		// Add the component listener since this class needs to be updated when there is a change on the other fields.
		addComponentListener( new ComponentAdapter ()
	    {
	        public void componentShown(ComponentEvent e )
	        {
	        	// Inform the controller that the panel is visible.
	            delegate.reportPanelVisible();
	        }
	    });
	}
	
	/***********************************************************/
	// Public Methods
	/***********************************************************/
	/**
	 * Method to set the table data for Category Panel
	 * @param content The table content to displayed as part of category panel.
	 * @param headers The table headers to displayed as part of category panel.
	 */
	public void setCategoryTableData(String[][] content, String[] headers) {
		categoryPanel.updateTable(content, headers);
	}
	
	/**
	 * Method to set the table data for Product Panel
	 * @param content The table content to displayed as part of product panel.
	 * @param headers The table headers to displayed as part of product panel.
	 */
	public void setProductTableData(String[][] content, String[] headers) {
		productPanel.updateTable(content, headers);
	}
	
	/**
	 * Method to set the table data for Member Panel
	 * @param content The table content to displayed as part of member panel.
	 * @param headers The table headers to displayed as part of member panel.
	 */
	public void setMemberTableData(String[][] content, String[] headers) {
		memberPanel.updateTable(content, headers);
	}
	
	/***********************************************************/
	// Private Methods
	/***********************************************************/

	private void createTabbedPane(JPanel categoryPanel, JPanel productPanel, JPanel memberPanel) {
		tabbedPane = new JTabbedPane();
		// TODO - Add image to the tabbed pane?
		tabbedPane.addTab("Category", null, categoryPanel,
				"Get all categories.");
		tabbedPane.addTab("Product", null, productPanel,
				"Get all products.");
		tabbedPane.addTab("Member", null, memberPanel,
				"Get all members.");
		add(tabbedPane, BorderLayout.CENTER);
	}
}
