package classes;

import java.util.ArrayList;

public class Grid {
	private String file;
	private loadImage image;
	private ArrayList<GridCell> cells;
	
	//project should be set up so that these sizes can change
	public static final int numRows = 10;
	public static final int numColumns = 100;
	
	public Grid() {
		cells = new ArrayList<GridCell>();
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				cells.add(new GridCell());
			}
		}
	}
	
	public Grid(String files){
		this();
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