package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
	
	
public class GridCell{
	private int row;
	private int column;
	private boolean isSearched;
	private boolean isOccupied;
	private boolean isSelected;
	private ArrayList<Searcher> searchers;
	
	public GridCell () {
		isSearched = false;
		searchers = new ArrayList<Searcher>();
	}
	
	public GridCell (int row, int column) {
		isSearched = false;
		this.row = row;
		this.column = column;
		isOccupied=false;
		searchers = new ArrayList<Searcher>();
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
	
	public void addSearcher(Searcher s) {
		searchers.add(s);
	}
	
	public void removeSearcher(Searcher s) {
		searchers.remove(s);
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
	
	public ArrayList<Searcher> getSearchers() {
		return searchers;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column +")";
	}
	
	public void draw(Graphics g) {
		
		//clear previous borders shown from being selected
		g.setColor(Color.WHITE);
		g.drawRect(getColumn()*Grid.cellWidth + 1, getRow()*Grid.cellHeight + 1,
				Grid.cellWidth - 2, Grid.cellHeight - 2);
		g.drawRect(getColumn()*Grid.cellWidth + 2, getRow()*Grid.cellHeight + 2, 
				Grid.cellWidth - 4, Grid.cellHeight - 4);
		
		if (isOccupied) {
			for (Searcher s : searchers) {
				s.draw(getColumn()*Grid.cellWidth, getRow()*Grid.cellHeight, 
						Grid.cellWidth ,Grid.cellHeight, g);
			}
			//g.setColor(Color.YELLOW);
			//g.fillRect(getColumn()*12, getRow()*12, 12, 12);
		} else if (isSearched) {
			g.setColor(Color.RED);
			g.fillRect(getColumn()*Grid.cellWidth,getRow()*Grid.cellHeight,
					Grid.cellWidth,Grid.cellHeight);
		}
		
		//draw cell boarder
		g.setColor(Color.BLACK);
		g.drawRect(getColumn()*Grid.cellWidth, getRow()*Grid.cellHeight,
				Grid.cellWidth, Grid.cellHeight);
		if (isSelected) {
			g.setColor(Color.GREEN);
			g.drawRect(getColumn()*Grid.cellWidth + 1, getRow()*Grid.cellHeight + 1,
					Grid.cellWidth - 2, Grid.cellHeight - 2);
			g.drawRect(getColumn()*Grid.cellWidth + 2, getRow()*Grid.cellHeight + 2, 
					Grid.cellWidth - 4, Grid.cellHeight - 4);
		}
		
	}
}
