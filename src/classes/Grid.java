package classes;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JPanel;

public class Grid extends JPanel{
	private loadImage image;
	private String pictureFile;
	private ArrayList<GridCell> cells;
	
	//project should be set up so that these sizes can change
	public static int numRows = 50;
	public static int numColumns = 50;
	
	public Grid() {
		cells = new ArrayList<GridCell>();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				cells.add(new GridCell(i,j));
			}
		}
	}
	
	public Grid(AlpineRescue rescue, String gridFile, String mapFile, Map<String,String> searcherConfig, int speed, String direction){
		cells = new ArrayList<GridCell>();
		try {
			FileReader reader = new FileReader(gridFile);
			Scanner scan = new Scanner(reader);
			String line;
			String[] splitLine;
			
			line = scan.nextLine();
			splitLine = line.split(",");
			
			numRows = Integer.parseInt(splitLine[0]);
			numColumns = Integer.parseInt(splitLine[1]);
			
			int row = 0;
			int column = 0;
			int numSearchers = 1;
			
			while(scan.hasNextLine()) {
				
				line = scan.nextLine();
				splitLine = line.split(",");
				for (String s : splitLine) {
					GridCell cell = new GridCell(row,column);
					cells.add(cell);
					if (searcherConfig.containsKey(s)) {
						rescue.addSearcher("Searcher" + numSearchers, searcherConfig.get(s), direction, speed, row, column);
						numSearchers++;
						cell.setOccupied(true);
					}
					column++;
					if (column == numColumns) {
						row++;
						column=0;
					}
				}
				
			}
		
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	}
	

	public void printImage(){
		image.printImage(pictureFile);
	}
	
	// get the cell at column, row
	//defined as column first because player x coordinate is column
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
		for(GridCell i : cells){
			i.draw(g);//TODO
		}
	}
	
	
	//used for testing
	public ArrayList<GridCell> getCellsArray() {
		return cells;
	}
}