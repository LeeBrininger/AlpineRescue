package classes;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{

	private JComboBox<String> searchers;

	public ControlPanel(){
		searchers = new JComboBox<String>();
		
		searchers.addItem("Helicopter");
		searchers.addItem("Dog Team");
		searchers.addItem("Hikers");
		add(searchers);
	}

}