package classes;

import java.awt.Color;
import java.awt.Graphics;
	
	
public class GridCell{
	private int row;
	private int column;
	private boolean isSearched;
	private boolean isOccupied;
	public static final int CELL_WIDTH = 12;
	private boolean isSelected;
	private Searcher searcher;
	
	public GridCell () {
		isSearched = false;
	}
	
	public GridCell (int row, int column) {
		isSearched = false;
		this.row = row;
		this.column = column;
		isOccupied=false;
	}
	
	public GridCell (GridCell cell) {
		this.row = cell.row;
		this.column = cell.column;
	}
	//set the grid cell to having been searched
	public void setSearched() {
		isSearched = true;
	}
	
	public int getRow(){
		return row;
	}
	public int getColumn(){
		return column;
	}
	
	public void setSelected(boolean selected) {
		isSelected = selected;
	}
	
	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public boolean isSearched() {
		return isSearched;
	}
	
	public Searcher getSearcher() {
		return searcher;
	}
	
	//can be used to set isSearched to either true or false
	public void setSearched(boolean setValue) {
		isSearched = setValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;


		if (this.getClass() != obj.getClass()) return false;

		GridCell newCell = (GridCell) obj;

		if (this.row == newCell.row && this.column == newCell.column) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + column +")";
	}
	
	public static int getCellWidth() {
		return CELL_WIDTH;
	}
	
	public void draw(Graphics g) {
		Color transparent = new Color(0,0,0, 0);
		
		//clear previous borders shown from being selected
		g.setColor(transparent);
		g.drawRect(getColumn()*CELL_WIDTH + 1, getRow()*CELL_WIDTH + 1,
				CELL_WIDTH - 2, CELL_WIDTH - 2);
		g.drawRect(getColumn()*CELL_WIDTH + 2, getRow()*CELL_WIDTH + 2, 
				CELL_WIDTH - 4, CELL_WIDTH - 4);
		
		if (isOccupied) {
			searcher.draw(getColumn()*CELL_WIDTH, getRow()*CELL_WIDTH, CELL_WIDTH, CELL_WIDTH, g);
		} else if (isSearched) {
			g.setColor(Color.RED);
			g.fillRect(getColumn()*CELL_WIDTH,getRow()*CELL_WIDTH,CELL_WIDTH,CELL_WIDTH);
	
		}
		
		//draw cell border
		g.setColor(Color.BLACK);
		g.drawRect(getColumn()*CELL_WIDTH, getRow()*CELL_WIDTH,
				CELL_WIDTH, CELL_WIDTH);
		if (isSelected) {
			g.setColor(Color.GREEN);
			g.drawRect(getColumn()*CELL_WIDTH + 1, getRow()*CELL_WIDTH + 1,
					CELL_WIDTH - 2, CELL_WIDTH - 2);
			g.drawRect(getColumn()*CELL_WIDTH + 2, getRow()*CELL_WIDTH + 2, 
					CELL_WIDTH - 4, CELL_WIDTH - 4);
		}
		
	}
}
