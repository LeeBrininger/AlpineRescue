package classes;

import java.util.ArrayList;

public class Grid {
	private String file;
	private loadImage image;
	private ArrayList<GridCell> cells;
	
	public static final int numRows = 10;
	public static final int numColumns = 10;
	
	public Grid() {
		cells = new ArrayList<GridCell>();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numRows; j++) {
				cells.add(new GridCell());
			}
		}
	}
	
	public Grid(String files){
		file = files;
	}

	public void printImage(){
		image.printImage(file);
	}
	
	// get the cell at column, row
	//defined as column first because player x coordinate is column
	public GridCell getCellAt(int column, int row) {
		return cells.get(row*this.getNumRows() + column);
	}
	
	public int getNumRows() {
		return Grid.numRows;
	}
	
	public int getNumColumns() {
		return Grid.numColumns;
	}
	
	//used for testing
	public ArrayList<GridCell> getCellsArray() {
		return cells;
	}
	
}