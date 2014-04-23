package classes;
	
	
public class GridCell {
	private int row;
	private int column;
	private boolean isSearched;
	private boolean isOccupied;
	
	public GridCell () {
		super();
		isSearched = false;
	}
	
	public GridCell (int row, int column) {
		super();
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
			column +=speed;
			break;
		case SOUTH:
			column -= speed;
			break;
		case EAST:
			row+=speed;
			break;
		case WEST:
			row-=speed;
			break;
		case NORTHEAST:
			column+=speed/2;
			row+=speed/2;
			break;
		case NORTHWEST:
			column+=speed/2;
			row-=speed/2;
			break;
		case SOUTHWEST:
			column-=speed/2;
			row-=speed/2;
			break;
		case SOUTHEAST:
			column-=speed/2;
			row+=speed/2;
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
		return "Position: (" + row + ", " + column +")";
	}
}
