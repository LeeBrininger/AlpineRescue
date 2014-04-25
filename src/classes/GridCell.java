package classes;
	
	
public class GridCell {
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
		return column;
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
}
