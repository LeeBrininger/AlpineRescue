package classes;

import java.util.ArrayList;

public class Searcher {
	private int speed;
	private Direction direction;
	private Position pos;
	private ArrayList<Position> visited;
	private String name;
	
	public Searcher(){}
	
	public Searcher(String name, String direction, int speed, int xpos, int ypos){
		this.name = name;
		visited = new ArrayList<Position>();
		pos = new Position(xpos, ypos);
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
		visited.add(pos);
		pos.updatePosition(speed, direction);
		
	}
	public void manualPosition(int xpos, int ypos){
		visited.add(pos);
		pos.changeX(xpos);
		pos.changeY(ypos);
	}
	public void changeDirection(String newDirection){
		visited.add(pos);
		direction = decodeDirection(newDirection);
	}
	public void Symbol(){
		
	}
	
	public Position getPosition(){
		return pos;
	}

	public ArrayList<Position> getVisited(){
		return visited;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public String getName() {
		return name;
	}
	
}
