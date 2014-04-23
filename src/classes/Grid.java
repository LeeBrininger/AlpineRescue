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
	
	//used for testing
	public ArrayList<GridCell> getCellsArray() {
		return cells;
	}
	
}