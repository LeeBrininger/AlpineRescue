package classes;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TextDisplay extends JPanel{
	private JTextField text;
	private TitledBorder border;
	String title;

	public TextDisplay(String title){
		this.title = title;
		text = new JTextField("",10);
		text.setEditable(false);
		add(text);
		border = new TitledBorder(new EtchedBorder(), title);
		setBorder(border);	

	}

	public void setText(String input, String type){
		text.setText(input);
		border = new TitledBorder(new EtchedBorder(), type);
		setBorder(border);
	}

}
