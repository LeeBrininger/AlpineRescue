package classes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public String getSelectedType() {
		return (String)searchers.getSelectedItem();
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
			} else if (e.getSource() == addSearcherButton) {
				JTextField name = new JTextField();
				JTextField direction = new JTextField();
				JTextField speedField = new JTextField();
				JTextField rowField = new JTextField();
				JTextField columnField = new JTextField();
				
				Object[] inputs = {
						"Type: ", getSelectedType(),
						"Name: ", name,
						"Direction:", direction,
						"Speed: ", speedField,
						"Row: ", rowField,
						"Column: ", columnField
				};

				int choice = JOptionPane.showConfirmDialog(rescue, inputs, "Add Searcher", JOptionPane.OK_CANCEL_OPTION);
				
				if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) return;
				
				boolean check = checkFields(name, direction ,speedField, rowField, columnField);
				
				if (check) {
					int row = Integer.parseInt(rowField.getText());
					int column = Integer.parseInt(columnField.getText());
					int speed = Integer.parseInt(speedField.getText());
					if (row < 0 || row >= rescue.getGrid().getNumRows() || column < 0 || column >= rescue.getGrid().getNumColumns()) {
						JOptionPane.showMessageDialog(rescue, "Row/Column out of bounds!", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					rescue.addSearcher(name.getText(), getSelectedType(), direction.getText(), speed, row, column);
				}
				
			} else {
				if (rescue.getSelectedSearcher() == null) { 
					JOptionPane.showMessageDialog(rescue, "There's no searcher selected!" , "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JTextField name = new JTextField();
				name.setText(rescue.getSelectedSearcher().getName());
				name.setEditable(false);
				JTextField direction = new JTextField();
				JTextField speedField = new JTextField();
				JTextField rowField = new JTextField();
				JTextField columnField = new JTextField();
				
				Object[] inputs = {
						"Name: ", name,
						"Direction: ", direction,
						"Speed: ", speedField,
						"Row: ", rowField,
						"Column: ", columnField
				};
			
				int choice = JOptionPane.showConfirmDialog(rescue, inputs, "Change Attributes", JOptionPane.OK_CANCEL_OPTION);
				
				if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) return;
				
				boolean check = checkFields(name, direction, speedField, rowField, columnField);
				
				if (check) {
					int row = Integer.parseInt(rowField.getText());
					int column = Integer.parseInt(columnField.getText());
					int speed = Integer.parseInt(speedField.getText());
					if (row < 0 || row >= rescue.getGrid().getNumRows() || column < 0 || column >= rescue.getGrid().getNumColumns()) {
						JOptionPane.showMessageDialog(rescue, "Row/Column out of bounds!", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					rescue.getSelectedSearcher().manualPositionUpdate(row, column, rescue.getGrid());
					rescue.getSelectedSearcher().setSpeed(speed);
					rescue.getSelectedSearcher().setDirection(Searcher.decodeDirection(direction.getText()));
				}
			}
		}
		
		public boolean checkFields(JTextField name, JTextField direction, JTextField speed, JTextField rowField, JTextField columnField) {
			if (name.getText().equals("") || direction.getText().equals("") || speed.getText().equals("") || rowField.getText().equals("") || columnField.getText().equals("")) {
				JOptionPane.showMessageDialog(rescue, "All fields need to be filled!", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (Searcher.decodeDirection(direction.getText()) == null) {
				JOptionPane.showMessageDialog(rescue, "That is not a valid direction!", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				try {
					Integer.parseInt(rowField.getText());
					Integer.parseInt(columnField.getText());
					Integer.parseInt(speed.getText());
				} catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(rescue, "Row, column, and speed need to be integers!", "ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			return true;
		}
	}

}