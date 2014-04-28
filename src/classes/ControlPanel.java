package classes;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel{

	private JComboBox<String> searchers;
	private JButton addSearcherButton;
	private JButton changeButton;
	private JButton pauseButton;
	private SelectedSearcherPanel selected;

	public ControlPanel(AlpineRescue rescue){
		setLayout(new GridLayout(2,3));
		setPreferredSize(new Dimension(200,200));
		searchers = new JComboBox<String>();
		addSearcherButton = new JButton("Add Searcher");
		changeButton = new JButton("Change Attributes");
		selected = new SelectedSearcherPanel();
		pauseButton = new JButton("Start");
		addSearcherButton.addActionListener(new ButtonListener(rescue));
		changeButton.addActionListener(new ButtonListener(rescue));
		pauseButton.addActionListener(new ButtonListener(rescue));
		
		searchers.addItem("Helicopter");
		searchers.addItem("Dog Team");
		searchers.addItem("Hikers");
		

		add (pauseButton);

		add(new JPanel());
		add(selected);
		add(searchers);
		add(addSearcherButton);
		add(changeButton);
		
	}
	
	class ButtonListener implements ActionListener {
		private AlpineRescue rescue;
		
		public ButtonListener(AlpineRescue rescue) {
			this.rescue = rescue;
		}

		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == pauseButton) {
				rescue.pause();
				if (!rescue.isPaused()) pauseButton.setText("Pause");
				else pauseButton.setName("Text");
			} else
				System.out.println("Button pressed");
		}
	}

}