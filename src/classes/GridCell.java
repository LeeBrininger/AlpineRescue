package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
	
	
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
		return row;
	}
	//can be used to set isSearched to either true or false
	public void setSearched(boolean setValue) {
		isSearched = setValue;
	}
	
	public void changeCell(int row, int column){
		this.row = row;
		this.column = column;
	}
	public void updatePosition(int speed, Direction direction) {
		switch (direction) {
		case NORTH:
			row +=speed;
			break;
		case SOUTH:
			row -= speed;
			break;
		case EAST:
			column+=speed;
			break;
		case WEST:
			column-=speed;
			break;
		case NORTHEAST:
			column+=speed;
			row+=speed;
			break;
		case NORTHWEST:
			row+=speed;
			column-=speed;
			break;
		case SOUTHWEST:
			column-=speed;
			row-=speed;
			break;
		case SOUTHEAST:
			row-=speed;
			column+=speed;
			break;
		}
		
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
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect = new Rectangle(getColumn()*25, getRow()*25, 25, 25);
		g2.setColor(Color.BLACK);
		g2.draw(rect);
	}
}
