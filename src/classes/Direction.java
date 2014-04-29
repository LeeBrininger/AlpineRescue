package classes;

public enum Direction {
	
	// Directions are defined with integers describing their change of row and column
	NORTHEAST(-1, 1), NORTH(-1,0), NORTHWEST(-1,-1), WEST(0,-1), SOUTHWEST(1,-1), SOUTH(1,0), SOUTHEAST(1,1), EAST(0,1);
	
	private int horizontal, vertical;
	
	private Direction(int vertical, int horizontal) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}
	
	public int getHorizontal() {
		return horizontal;
	}
	
	public int getVertical() {
		return vertical;
	}
	
}
