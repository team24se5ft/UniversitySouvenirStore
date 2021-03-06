package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.IDiscountDelegate;

/**
 * 
 * @author linby
 *
 */
public class DiscountPanel extends BaseTablePanel {
	/***********************************************************/
	// Constants
	/***********************************************************/
	private static final long serialVersionUID = 1L;
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
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
				if (table.getSelectedRow() != -1) {
					delegate.updateDiscount(table.getSelectedRow());
				}
				else {
					delegate.rowNotSelected();
				}
			}
		};
	}

	@Override
	protected ActionListener deleteAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					delegate.deleteDiscount(table.getSelectedRow());
				}
				else {
					delegate.rowNotSelected();
				}
			}
		};
	}

}
