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
	
	public void setSelectedSearcherDirection(String inputText) {
		selected.setDirection(inputText);
	}
	
	public String getSelectedType() {
		return (String)searchers.getSelectedItem();
	}
	
	class ButtonListener implements ActionListener {
		private AlpineRescue rescue;

		public ButtonListener(AlpineRescue rescue) {
			this.rescue = rescue;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == pauseButton) {
				rescue.pause();
				if (!rescue.isPaused())
					pauseButton.setText("Pause");
				else
					pauseButton.setText("Start");
			} else {
				JTextField name = new JTextField();
				JTextField direction = new JTextField();
				JTextField speedField = new JTextField();
				JTextField rowField = new JTextField();
				JTextField columnField = new JTextField();
				Searcher selectedSearcher = rescue.getSelectedSearcher();
				String type;
				
				//set the values of row and column based on what cell is selected
				rowField.setText(Integer.toString(rescue.getSelectedCell().getRow()));
				columnField.setText(Integer.toString(rescue.getSelectedCell().getColumn()));				
				
				if (e.getSource() == addSearcherButton) {
					type = getSelectedType();
				} else {
					if (selectedSearcher == null) {
						JOptionPane.showMessageDialog(rescue, "There is no searcher selected!", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					type = rescue.getSelectedSearcher().getType();
					name.setEditable(false);
					name.setText(selectedSearcher.getName());
				}

				Object[] inputs = { "Type: ", type, "Name: ", name,
						"Direction:", direction, "Speed: ", speedField,
						"Row: ", rowField, "Column: ", columnField };

				int choice;

				if (e.getSource() == addSearcherButton) {
					choice = JOptionPane.showConfirmDialog(rescue, inputs,
							"Add Searcher", JOptionPane.OK_CANCEL_OPTION);
				} else {
					choice = JOptionPane.showConfirmDialog(rescue, inputs,
							"Change Attributes", JOptionPane.OK_CANCEL_OPTION);
				}

				if (choice == JOptionPane.CANCEL_OPTION
						|| choice == JOptionPane.CLOSED_OPTION)
					return;

				boolean check = checkFields(name, direction, speedField,
						rowField, columnField);
				if (check) {
					int row = Integer.parseInt(rowField.getText());
					int column = Integer.parseInt(columnField.getText());
					int speed = Integer.parseInt(speedField.getText());
					if (row < 0 || row >= rescue.getGrid().getNumRows()
							|| column < 0
							|| column >= rescue.getGrid().getNumColumns()) {
						JOptionPane.showMessageDialog(rescue,
								"Row/Column out of bounds!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (e.getSource() == addSearcherButton) {
						type = getSelectedType().replaceAll("\\s+", "");
						rescue.addSearcher(name.getText(), type,
								direction.getText(), speed, row, column);
					} else {
						selectedSearcher.manualPositionUpdate(row, column,
								rescue.getGrid());
						selectedSearcher.setSpeed(speed);
						selectedSearcher.setDirection(Searcher
								.decodeDirection(direction.getText()));
					}
				}

			}
		}

		public boolean checkFields(JTextField name, JTextField direction,
				JTextField speed, JTextField rowField, JTextField columnField) {
			if (name.getText().equals("") || direction.getText().equals("")
					|| speed.getText().equals("")
					|| rowField.getText().equals("")
					|| columnField.getText().equals("")) {
				JOptionPane.showMessageDialog(rescue,
						"All fields need to be filled!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (Searcher.decodeDirection(direction.getText()) == null) {
				JOptionPane.showMessageDialog(rescue,
						"That is not a valid direction!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				try {
					Integer.parseInt(rowField.getText());
					Integer.parseInt(columnField.getText());
					Integer.parseInt(speed.getText());
				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(rescue,
							"Row, column, and speed need to be integers!",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			return true;
		}
	}

}