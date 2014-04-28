package classes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setPreferredSize(new Dimension(180,180));
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
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx = .2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(addSearcherButton, gbc);
		gbc.gridx=1;
		add(searchers, gbc);
		gbc.gridx=2;
		gbc.weightx = .40;
		add(selected,gbc);

		gbc.weightx = .2;
		gbc.gridy=1;
		gbc.gridx=0;
		add (pauseButton,gbc);
		gbc.gridy=1;
		gbc.gridx=2;
		add(changeButton,gbc);
		
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
				else pauseButton.setText("Start");
			} else
				System.out.println("Button pressed");
		}
	}

}