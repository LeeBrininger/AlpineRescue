package classes;

public enum Direction {
	NORTHEAST(-1, 1), NORTH(-1,0), NORTHWEST(-1,-1), WEST(0,-1), SOUTHWEST(1,-1), SOUTH(1,0), SOUTHEAST(1,1), EAST(0,1);
	
	private Direction(int row, int column) {
		
	}
}
