package classes;

public class Searcher {
	private int speed;
	private String type;
	private String direction;
	private int xpos;
	private int ypos;
	private Searcher searcher;
	private Position pos;
	
	public Searcher(){}
	
	public Searcher(String type, String direction, int speed, int xpos, int ypos){
		pos = new Position(xpos, ypos);
	}
	
	public void updatePosition(){
		pos.updatePosition(speed, direction);
		
	}
	public void manualPosition(int xpos, int ypos){
		pos.changeX(xpos);
		pos.changeY(ypos);
	}
	public void changeDirection(String newDirection){
		direction = newDirection;
	}
}
