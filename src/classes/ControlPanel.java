package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel{

	private JComboBox<String> searchers;
	private JButton addSearcher;
	private JButton change;
	private SelectedSearcher selected;

	public ControlPanel(){
		searchers = new JComboBox<String>();
		addSearcher = new JButton("Add Searcher");
		change = new JButton("Change Attributes");
		selected = new SelectedSearcher();
		addSearcher.addActionListener(new ButtonListener());
		change.addActionListener(new ButtonListener());
		
		
		searchers.addItem("Helicopter");
		searchers.addItem("Dog Team");
		searchers.addItem("Hikers");
		
		
		add(searchers);
		add(addSearcher);
		add(change);
		add(selected);
		
	}
	
	public void setSelectedSearcherName(String inputText) {
		selected.setName(inputText);
	}
	
	public void setSelectedSearcherSpeed(String inputText) {
		selected.setSpeed(inputText);
	}
	
	public void setSelectedSearcherLocation(String inputText) {
		selected.setLocation(inputText);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Button pressed");
		}
	}

}