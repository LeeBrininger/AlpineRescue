package classes;

public class Hiker extends Searcher{
	private String direction;
	private int speed;
	private Position hikepos;
	
	
	public Hiker(){}
	
	public Hiker(String directions, int mph, int ypos, int xpos){
		direction = directions;
		speed = mph;
		hikepos = new Position(xpos, ypos);
	}

	public void Symbol(){
		
	}
	public int Test(){
		return 3;
	}
}
