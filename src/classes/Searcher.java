package classes;

import java.util.ArrayList;

public class Searcher {
	private int speed;
	private Direction direction;
	private ArrayList<GridCell> visited;
	private String name;
	private GridCell cell;
	
	public Searcher(){}
	
	public Searcher(String name, String direction, int speed, int row, int column, Grid grid){
		this.name = name;
		visited = new ArrayList<GridCell>();
		cell = grid.getCellAt(row,column);
		cell.setOccupied(true);
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
	
	public void move(Grid grid){
		visited.add(new GridCell(cell));
		int row = cell.getRow();
		int column = cell.getColumn();
		switch (direction) {
		case NORTH:
			row -=speed;
			break;
		case SOUTH:
			row += speed;
			break;
		case EAST:
			column+=speed;
			break;
		case WEST:
			column-=speed;
			break;
		case NORTHEAST:
			column+=speed;
			row-=speed;
			break;
		case NORTHWEST:
			row-=speed;
			column-=speed;
			break;
		case SOUTHWEST:
			column-=speed;
			row+=speed;
			break;
		case SOUTHEAST:
			row+=speed;
			column+=speed;
			break;
		}

		cell.setSearched();
		cell.setOccupied(false);
		cell = grid.getCellAt(row,column);
		cell.setOccupied(true);
	}
	public void manualPositionUpdate(int row, int column, Grid grid){
		cell.setOccupied(false);

		cell.setSearched();
		visited.add(new GridCell(cell));
		cell = grid.getCellAt(row,column);
		cell.setOccupied(true);
	}
	public void changeDirection(String newDirection){
		visited.add(new GridCell(cell));
		direction = decodeDirection(newDirection);
	}
	public void Symbol(){
	}
	
	public GridCell getCell(){
		return cell;
	}
	public ArrayList<GridCell> getVisited(){
		return visited;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public String getName() {
		return name;
	}
	
}
