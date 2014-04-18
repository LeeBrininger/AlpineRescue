package classes;

import java.util.ArrayList;

public class Searcher {
	private int speed;
	private String direction;
	private int xpos;
	private int ypos;
	private Position pos;
	private ArrayList<Position> visited = new ArrayList<Position>();
	
	public Searcher(){}
	
	public Searcher(String type, String direction, int speed, int xpos, int ypos){
		pos = new Position(xpos, ypos);
		System.out.println(pos.getY());
	}
	
	public void updatePosition(){
		visited.add(pos);
		pos.updatePosition(speed, direction);
		
	}
	public void manualPosition(int xpos, int ypos){
		pos.changeX(xpos);
		pos.changeY(ypos);
	}
	public void changeDirection(String newDirection){
		visited.add(pos);
		direction = newDirection;
	}
	public void Symbol(){
		
	}
	
	public Position getPosition(){
		return pos;
	}

	public ArrayList<Position> getVisited(){
		return visited;
	}
	
	//only exists for the tests
	public int Test() {
		return 0;
	}
}
