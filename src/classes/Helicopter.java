package classes;

public class Helicopter extends Searcher{
	private String direction;
	private int speed;
	private Position helpos;
	
	
	public Helicopter(){}
	
	public Helicopter(String directions, int mph, int ypos, int xpos){
		direction = directions;
		speed = mph;
		helpos = new Position(xpos, ypos);
	}
	
	public void Move() {
		helpos.updatePosition(speed, direction);
	}
	public void manualPosition(int xpos, int ypos){
		helpos.changeX(xpos);
		helpos.changeY(ypos);
	}
	
	public void Symbol(){
		
	}

}
