package classes;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Searcher {
	private int speed;
	private Direction direction;
	private ArrayList<GridCell> visited;
	private String name;
	private GridCell cell;
	private boolean canFlyOver = false;
	
	public Searcher(){}
	
	public Searcher(String name, String direction, int speed, int row, int column, Grid grid){
		this.name = name;
		visited = new ArrayList<GridCell>();
		cell = grid.getCellAt(row,column);
		cell.setOccupied(true);
		cell.setSearcher(this);
		this.speed = speed;
		this.direction = decodeDirection(direction);
		grid.repaint();
	}
	
	// Decodes the direction from a String into its proper enumerated type
	public static Direction decodeDirection(String dir) {
		dir = dir.toLowerCase();
		Direction direction;
		switch(dir) {
		case "north":
			direction = Direction.NORTH;
			break;
		case "south":
			direction = Direction.SOUTH;
			break;
		case "west":
			direction = Direction.WEST;
			break;
		case "east":
			direction = Direction.EAST;
			break;
		case "northwest":
			direction = Direction.NORTHWEST;
			break;
		case "northeast":
			direction = Direction.NORTHEAST;
			break;
		case "southwest":
			direction = Direction.SOUTHWEST;
			break;
		case "southeast":
			direction = Direction.SOUTHEAST;
			break;
		default:
			direction = null;
		}
		return direction;
	}
	
	public abstract void move(Grid grid);
	
	// Determines whether the cell is valid to be moved to.
	public boolean isValidCell(int row,int column, Grid grid) {
		if (row < 0 || row >= grid.getNumRows()) return false;
		else if (column < 0 || column >= grid.getNumColumns()) return false;
		GridCell cell = grid.getCellAt(row, column);
		if (cell.isSearched() && !canFlyOver) return false;
		else if (cell.isOccupied()) return false;
		else return true;
	}
	
	// Updates the searcher's position based on its current direction and speed
	public void automaticUpdatePosition(Grid grid) {
		visited.add(new GridCell(cell));
		int row = cell.getRow();
		int column = cell.getColumn();
		row += direction.getVertical();
		column += direction.getHorizontal();
		
		if (isValidCell(row,column,grid)) {
			updatePosition(row,column,grid);
		}
	}
	
	// Manually updates the searcher's position to a specific cell.
	public void manualPositionUpdate(int row, int column, Grid grid){
		// Every cell between the previous cell and the new one is presumed to be searched.
		if (cell.getRow() < row) 
			for (int i = cell.getRow(); i<row; i++) grid.getCellAt(i, cell.getColumn()).setSearched();
		else 
			for (int i = cell.getRow(); i>row; i--) grid.getCellAt(i, cell.getColumn()).setSearched();
		if (cell.getColumn() < column)
			for (int i = cell.getColumn(); i < column; i++) grid.getCellAt(row, i).setSearched();
		else
			for (int i = cell.getColumn(); i > column; i--) grid.getCellAt(row, i).setSearched();
		updatePosition(row,column,grid);
	}
	
	// Updates the searcher's current position
	public void updatePosition(int row, int column, Grid grid) {
		cell.setOccupied(false);
		cell.setSearcher(null);
		cell.setSearched();
		visited.add(new GridCell(cell));
		cell = grid.getCellAt(row,column);
		cell.setOccupied(true);
		cell.setSearcher(this);
	}
	
	// Changes the searcher's direction.
	public void changeDirection(String newDirection){
		visited.add(new GridCell(cell));
		direction = decodeDirection(newDirection);
	}
	
	public abstract void draw(int rectX, int rectY, int width, int height, Graphics g);
	
	public GridCell getCell(){
		return cell;
	}
	
	public ArrayList<GridCell> getVisited(){
		return visited;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String getType();
	
	// Returns whether or not the searcher can fly over searched cells.
	public boolean canFlyOver() {
		return canFlyOver;
	}
	
	// Sets whether or not the searcher can fly over searched cells.
	public void setFlyOver(boolean canFlyOver) {
		this.canFlyOver = canFlyOver;
	}
	
}
