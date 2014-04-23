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
	
	//set the grid cell to having been searched
	public void setSearched() {
		isSearched = true;
	}
	
	//can be used to set isSearched to either true or false
	public void setSearched(boolean setValue) {
		isSearched = setValue;
	}
}
