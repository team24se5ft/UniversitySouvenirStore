package sg.edu.nus.iss.universitystore.view.subpanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryPanel extends JPanel{
	public InventoryPanel(){
		setBackground(Color.BLUE);
		add(new JLabel("inventory"));
	}
}
