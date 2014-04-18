package classes;

public class Position {
	private int xpos;
	private int ypos;
	
	public Position(){}
	
	public Position(int x, int y){
		xpos = x;
		ypos = y;
	}
	
	public Position (Position pos) {
		this.xpos = pos.xpos;
		this.ypos = pos.ypos;
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

	public void updatePosition(int speed, Direction direction) {
		switch (direction) {
		case NORTH:
			ypos+=speed;
			break;
		case SOUTH:
			ypos -= speed;
			break;
		case EAST:
			xpos+=speed;
			break;
		case WEST:
			xpos-=speed;
			break;
		case NORTHEAST:
			ypos+=speed/2;
			xpos+=speed/2;
			break;
		case NORTHWEST:
			ypos+=speed/2;
			xpos-=speed/2;
			break;
		case SOUTHWEST:
			ypos-=speed/2;
			xpos-=speed/2;
			break;
		case SOUTHEAST:
			ypos-=speed/2;
			xpos+=speed/2;
			break;
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;


		if (this.getClass() != obj.getClass()) return false;

		Position newPos = (Position) obj;

		if (this.xpos == newPos.xpos && this.ypos == newPos.ypos) return true;
		else return false;
	}

	@Override
	public String toString() {
		return "Position: (" + xpos + ", " + ypos +")";
	}
}

