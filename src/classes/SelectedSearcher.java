package classes;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectedSearcher extends JPanel{
	private JTextField selectedSearcher;
	private TextDisplay name;
	private TextDisplay speed;
	private TextDisplay location;

	public SelectedSearcher(){
		selectedSearcher = new JTextField("Selected Searcher",20);
		selectedSearcher.setEditable(false);
		name = new TextDisplay("Name");
		speed = new TextDisplay("Speed");
		location = new TextDisplay("Location");
		setLayout(new GridLayout(4,1));
		add(selectedSearcher);
		add(name);
		add(speed);
		add(location);
	}
}
