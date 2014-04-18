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
	
	public Searcher(String name, String direction, int speed, int xpos, int ypos){
		
	}
	
	public void updatePosition(){
		
	}
	
	public void manualPosition(int xpos, int ypos){
	}
	public void changeDirection(String newDirection){
	}
	public void Symbol(){
		
	}
	
	public Position getPosition(){
		return null;
	}

	public ArrayList<Position> getVisited(){
		return null;
	}
	
	public Direction getDirection() {
		return null;
	}
	
	public String getName() {
		return null;
	}
	
}
