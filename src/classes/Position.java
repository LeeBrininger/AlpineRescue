package classes;

public class Position {
	private int xpos;
	private int ypos;
	
	public Position(){}
	
	public Position(int x, int y){
		xpos = x;
		ypos = y;
	}
	
	public int getY(){
		return ypos;
	}
	public int getX(){
		return xpos;
	}
	
	public void changeX(int x){
		xpos = x;
	}
	
	public void changeY(int y){
		ypos = y;
	}

	public void updatePosition(int speed, String direction) {
		if(direction == "north"){
			ypos = ypos + speed;
		}
		if(direction == "south"){
			ypos = ypos - speed;
		}
		if(direction == "east"){
			xpos = xpos + speed;
		}
		if(direction == "west"){
			xpos = xpos - speed;
		}
		
	}

}
