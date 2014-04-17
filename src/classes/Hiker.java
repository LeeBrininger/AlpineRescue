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
	
	public void Move() {
		hikepos.updatePosition(speed, direction);
	}
	
	public void manualPosition(int xpos, int ypos){
		hikepos.changeX(xpos);
		hikepos.changeY(ypos);
	}
	public void hikerSymbol(){
		
	}

}
