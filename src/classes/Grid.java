package classes;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;

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
	
	public Grid(String files){
		this();
		file = files;
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