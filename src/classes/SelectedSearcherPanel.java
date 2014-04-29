package classes;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SelectedSearcherPanel extends JPanel{
	private JTextField selectedSearcher;
	private TextDisplay name;
	private TextDisplay speed;
	private TextDisplay location;
	private TextDisplay direction;

	//panel that allows the user to change attributes of the current searchers
	public SelectedSearcherPanel(){
		selectedSearcher = new JTextField("Selected Searcher",20);
		selectedSearcher.setEditable(false);
		name = new TextDisplay("Name");
		speed = new TextDisplay("Speed");
		location = new TextDisplay("Location");
		direction = new TextDisplay("Direction");
		setLayout(new GridLayout(2,2));
		add(selectedSearcher);
		add(name);
		add(speed);
		add(location);
		add(direction);
		speed.setSize(100,20);
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
	
	public void setDirection(String text){
		direction.setText(text);
	}
}
