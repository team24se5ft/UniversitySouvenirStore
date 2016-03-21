package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitystore.model.Discount;
import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.IDiscountDelegate;

/**
 * 
 * @author linby
 *
 */
public class DiscountPanel extends BaseTablePanel {

	private IDiscountDelegate delegate;// to communicate with Discount
										// controller

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DiscountPanel(IDiscountDelegate delegate) {
		setBackground(Color.WHITE);
		this.delegate = delegate;
		BorderLayout borderLayout = new BorderLayout();
		// set height and width gap
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		initDiscountTable();
		// add(new JLabel("discount"));
	}

	/***********************************************************/
	// Private Methods
	/***********************************************************/
	// FIXME latter use our own table component
	private void initDiscountTable() {
		// FIXME hardcode need to fix after use formal data
		String[] headers = { "code", "percentage", "type", "description", "startDate", "period" };
		String data[][] = {};
		add(getScrollPaneWithTable(data, headers),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
	}


	/***********************************************************/
	// Public Methods
	/***********************************************************/

	/**
	 * entrance of discount tableData,should be invoke by controller
	 * 
	 * @param list
	 */
	public void setDiscountTableData(ArrayList<Discount> list) {
		for (Discount entity : list) {
			onAddDiscount(entity);
		}
	}

	/**
	 * after confirm in dialog to remove item
	 * 
	 * @param row
	 *            which row should be deleted
	 */
	public void onRemoveDiscount(int row) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (table.getSelectedRow() != -1) {
			model.removeRow(row);
		}
	}

	/**
	 * after click ok in AddDiscountDialog to addDiscount
	 * 
	 * @param discount
	 */
	public void onAddDiscount(Discount discount) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { discount.getCode(), discount.getPercentage() + "%", discount.getEligibilty(),
				discount.getDescription(), discount.getStartDate().toString(), discount.getPeriod() });
	}

	/**
	 * after click ok in ModifyDiscountDialog to updateDiscount
	 * 
	 * @param discount
	 */
	public void onUpdateDiscount(Discount discount, int row) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.removeRow(row);
		model.insertRow(row,
				new Object[] { discount.getCode(), discount.getPercentage() + "%", discount.getEligibilty(),
						discount.getDescription(), discount.getStartDate().toString(), discount.getPeriod() });
	}

	public void updateUI(String[] header, String[][] content) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setDataVector(content, header);
	}


	/***********************************************************/
	// Abstract Methods Implementation
	/***********************************************************/
	@Override
	protected ActionListener addAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.addDiscount();
			}
		};
	}

	@Override
	protected ActionListener editAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.updateDiscount(table.getSelectedRow());
			}
		};
	}

	@Override
	protected ActionListener deleteAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.deleteDiscount(table.getSelectedRow());
			}
		};
	}

}
