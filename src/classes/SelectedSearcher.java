package classes;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectedSearcher extends JPanel{
	private JTextField selectedSearcher;
	private TextDisplay name;
	private TextDisplay speed;
	private TextDisplay location;

	public SelectedSearcher(){
		selectedSearcher = new JTextField("Selected Searcher",20);
		name = new TextDisplay("Name");
		speed = new TextDisplay("Speed");
		location = new TextDisplay("Location");
		
		add(selectedSearcher);
		add(name);
		add(speed);
		add(location);
	}
}
