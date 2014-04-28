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
	
	public Direction decodeDirection(String dir) {
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
	
	public boolean isValidCell(int row,int column, Grid grid) {
		if (row < 0 || row >= grid.getNumRows()) return false;
		else if (column < 0 || column >= grid.getNumColumns()) return false;
		GridCell cell = grid.getCellAt(row, column);
		if ((cell.isOccupied() || cell.isSearched()) && !canFlyOver) return false;
		else return true;
	}
	
	public void updatePosition(Grid grid) {
		visited.add(new GridCell(cell));
		int row = cell.getRow();
		int column = cell.getColumn();
		row += direction.getVertical();
		column += direction.getHorizontal();
		
		if (isValidCell(row,column,grid)) {
			manualPositionUpdate(row,column,grid);
		}
	}
	
	public void manualPositionUpdate(int row, int column, Grid grid){
		cell.setOccupied(false);
		cell.setSearcher(null);
		cell.setSearched();
		visited.add(new GridCell(cell));
		cell = grid.getCellAt(row,column);
		cell.setOccupied(true);
		cell.setSearcher(this);
	}
	public void changeDirection(String newDirection){
		visited.add(new GridCell(cell));
		direction = decodeDirection(newDirection);
	}
	
	public void Symbol(){
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
	
	public int getSpeed() {
		return speed;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean canFlyOver() {
		return canFlyOver;
	}
	
	public void setFlyOver(boolean canFlyOver) {
		this.canFlyOver = canFlyOver;
	}
	
}
