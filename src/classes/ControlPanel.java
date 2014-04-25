package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{

	private JComboBox<String> searchers;
	private JButton addSearcher;

	public ControlPanel(){
		searchers = new JComboBox<String>();
		addSearcher = new JButton("Add Searcher");
		addSearcher.addActionListener(new ButtonListener());
		
		searchers.addItem("Helicopter");
		searchers.addItem("Dog Team");
		searchers.addItem("Hikers");
		add(searchers);
		add(addSearcher);
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Button pressed");
		}
	}

}