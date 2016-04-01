package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.universitystore.view.BaseTablePanel;
import sg.edu.nus.iss.universitystore.view.intf.IReportDelegate;

public class TransactionPanel extends BaseTablePanel {
	/***********************************************************/
	// Instance Variables
	/***********************************************************/
	// the query start date
	private JTextField startDate;
	// the query end date
	private JTextField endDate;
	// query button
	private JButton queryBtn;

	private IReportDelegate delegate;

	/***********************************************************/
	// Constructors
	/***********************************************************/
	public TransactionPanel(IReportDelegate delegate) {
		this.delegate = delegate;
		setLayout(new BorderLayout());
		add(getScrollPaneWithTable(null, null), BorderLayout.CENTER);
		add(getQueryInputPanel(), BorderLayout.NORTH);
	}

	/**
	 * init the query info panel
	 * 
	 * @return
	 */
	private JPanel getQueryInputPanel() {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		startDate = new JTextField();
		endDate = new JTextField();
		queryBtn = new JButton("query");
		startDate.setPreferredSize(new Dimension(100, 30));
		endDate.setPreferredSize(new Dimension(100, 30));
		queryBtn.setPreferredSize(new Dimension(80, 30));
		jpanel.add(new JLabel("Start_date:"));
		jpanel.add(startDate);
		jpanel.add(new JLabel("End_date:"));
		jpanel.add(endDate);
		jpanel.add(queryBtn);
		queryBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delegate.onTransactionQuery(startDate.getText(), endDate.getText());
			}
		});
		return jpanel;
	}

	@Override
	protected ActionListener addAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionListener editAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionListener deleteAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
