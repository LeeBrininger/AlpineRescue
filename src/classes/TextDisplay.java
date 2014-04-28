package classes;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TextDisplay extends JPanel{
	private JTextField text;
	private TitledBorder border;
	String title;

	public TextDisplay(String title){
		this.title = title;
		text = new JTextField("",10);
		text.setEditable(false);
		setVisible(true);
		add(text);
		border = new TitledBorder(new EtchedBorder(), title);
		setBorder(border);	
		setSize(new Dimension(20,20));
		}
	
	public void setText(String textInput) {
		text.setText(textInput);
	}

	public void setText(String input, String type){
		text.setText(input);
		border = new TitledBorder(new EtchedBorder(), type);
		setBorder(border);
	}

}
