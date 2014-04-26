package classes;

import java.awt.Color;
import java.awt.Graphics;
	
	
public class GridCell{
	private int row;
	private int column;
	private boolean isSearched;
	private boolean isOccupied;
	
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
	
	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}
	
	public boolean isOccupied() {
		return isOccupied;
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
	
	public void draw(Graphics g) {
	
		if (isOccupied) {
			g.setColor(Color.YELLOW);
			g.fillRect(getColumn()*12, getRow()*12, 12, 12);
		} else if (isSearched) {
			g.setColor(Color.RED);
			g.fillRect(getColumn()*12,getRow()*12,12,12);
		}
		
			g.setColor(Color.BLACK);
			g.drawRect(getColumn()*12, getRow()*12, 12, 12);
		
		
	}
}
