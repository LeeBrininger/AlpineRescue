package classes;

import java.util.ArrayList;

public class Searcher {
	private int speed;
	private Direction direction;
	private ArrayList<GridCell> visited;
	private String name;
	private GridCell cell;
	
	public Searcher(){}
	
	public Searcher(String name, String direction, int speed, int xpos, int ypos){
		this.name = name;
		visited = new ArrayList<GridCell>();
		cell = new GridCell(xpos, ypos);
		this.speed = speed;
		this.direction = decodeDirection(direction);
	}
	
	public Direction decodeDirection(String dir) {
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
	
	public void updatePosition(){
		visited.add(new GridCell(cell));
		cell.updatePosition(speed, direction);
		
	}
	public void manualPosition(int row, int column){
		visited.add(new GridCell(cell));
		cell.changeCell(row, column);
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
