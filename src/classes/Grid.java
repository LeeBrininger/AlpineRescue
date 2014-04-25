package classes;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Grid {
	private String file;
	private loadImage image;
	private ArrayList<GridCell> cells;
	
	//project should be set up so that these sizes can change
	public static final int numRows = 50;
	public static final int numColumns = 50;
	
	public Grid() {
		cells = new ArrayList<GridCell>();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				cells.add(new GridCell(i,j));
			}
		}
	}
	
	public Grid(String file){
		this.file = file;
		try {
			FileReader reader = new FileReader(file);
			Scanner scan = new Scanner(reader);
			String line = scan.nextLine();
			String[] splitLine = line.split(",");
			int rows = Integer.parseInt(splitLine[0]);
			int columns = Integer.parseInt(splitLine[1]);
			
			for (int i=0; i<rows; i++) {
				for (int j=0; j<columns; j++) {
					
				}
			}
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void printImage(){
		image.printImage(file);
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