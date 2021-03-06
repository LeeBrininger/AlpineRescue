package classes;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Grid extends JPanel{
	@SuppressWarnings("unused")
	private loadImage image;
	private String pictureFile;
	private ArrayList<GridCell> cells;
	
	//project should be set up so that these sizes can change
	public static int numRows = 50;
	public static int numColumns = 50;
	
	public Grid(String file) {
		pictureFile = file;
		image = new loadImage(pictureFile);
		cells = new ArrayList<GridCell>();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				cells.add(new GridCell(i,j));
			}
		}
	}
	
	// Loads a board configuration from a file.
	public void loadConfig(AlpineRescue rescue, String gridFile, String mapFile, Map<String,String> searcherConfig, String direction) {
		try {
			FileReader reader = new FileReader(gridFile);
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(reader);
			String line;
			String[] splitLine;
			
			line = scan.nextLine();
			splitLine = line.split(",");
			try {
				numRows = Integer.parseInt(splitLine[0]);
				numColumns = Integer.parseInt(splitLine[1]);
			} catch (NumberFormatException e) {
				throw new BadConfigFormatException();
			}
		
			int index = 0;
			int numSearchers = 1;
			
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				splitLine = line.split(",");
				if (splitLine.length > numColumns) throw new BadConfigFormatException();
				for (String s : splitLine) {
					GridCell cell = cells.get(index);
					if (searcherConfig.containsKey(s)) {
						rescue.addSearcher("Searcher" + numSearchers, searcherConfig.get(s), direction, cell.getRow(), cell.getColumn());
						numSearchers++;
						cell.setOccupied(true);
					}
					else{
						cell.setOccupied(false);
					}
					index++;
				}
				
			}
		
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	public GridCell getCellAt(int row, int column) {
		return cells.get(row*this.getNumColumns() + column);
	}
	
	public int getNumRows() {
		return Grid.numRows;
	}
	
	public int getNumColumns() {
		return Grid.numColumns;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		loadImage.paint(g);
		for(GridCell i : cells){
			i.draw(g);
		}
	} 
	
	// Takes the current configuration of the grid and saves it to a specified file.
	public void saveGrid(AlpineRescue rescue) {
		JTextField filename = new JTextField();
		Object[] inputs = { "Filename (.csv): ", filename};
		
		int choice = JOptionPane.showConfirmDialog(rescue, inputs, "Save Search", JOptionPane.OK_CANCEL_OPTION);
		String path = filename.getText();
		if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) return;
		else if (path.length()<4) {
			JOptionPane.showMessageDialog(rescue, "File not valid.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (!path.substring(path.length()-4).equals(".csv")){
			JOptionPane.showMessageDialog(rescue, "File wasn't a .csv!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		else {
			try {
				PrintWriter writer = new PrintWriter(path);
				writer.println(numRows + "," + numColumns);
				int index = -1;
				for (GridCell cell : cells) {
					if (index == numColumns) {
						index=0;
						writer.println();
					} else if (index != -1) writer.print(",");
					else index++;
					
					if (cell.isOccupied()) {
						String type = cell.getSearcher().getType().replaceAll("\\s+", "");
						System.out.println(type);
						if (rescue.getSearcherConfig().containsValue(type)) {
							type = AlpineRescue.getKeyByValue(rescue.getSearcherConfig(), type);
							writer.print(type);
							System.out.println(type);
						} 
					} else {
						writer.print("E");
					}
					index++;
				}
				writer.close();
			} catch (FileNotFoundException e) {
				e.getStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	public ArrayList<GridCell> getCells() {
		return cells;
	}
}