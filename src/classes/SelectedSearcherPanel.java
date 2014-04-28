package classes;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectedSearcherPanel extends JPanel{
	private JTextField selectedSearcher;
	private TextDisplay name;
	private TextDisplay speed;
	private TextDisplay location;

	public SelectedSearcherPanel(){
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
	
	public void setName(String text) {
		name.setText(text);
	}
	
	public void setSpeed(String text) {
		speed.setText(text);
	}
	
	public void setLocation(String text) {
		location.setText(text);
	}
}
