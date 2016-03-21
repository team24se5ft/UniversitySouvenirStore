package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IDiscountDelegate delegate;// to communicate with Discount
										// controller

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public DiscountPanel(IDiscountDelegate delegate) {
		//setBackground(Color.WHITE);
		this.delegate = delegate;
		BorderLayout borderLayout = new BorderLayout();
		// set height and width gap
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		this.setLayout(borderLayout);
		add(getScrollPaneWithTable(null,null),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
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
