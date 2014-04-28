package classes;

public class Hiker extends Searcher{
	
	private static final int DEFAULT_SPEED = 1;
	
	public Hiker(){}
	
	public Hiker(String name, String direction, int row, int column, Grid grid) {
		super(name,direction,DEFAULT_SPEED,row,column, grid);
	}
	
	public Hiker(String name, String direction, int speed, int row, int column, Grid grid){
		super(name,direction,speed,row,column, grid);
	}

	public void Symbol(){
		
	}

	public static int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}
	
	@Override
	public void move(Grid grid) {
		for (int i =0; i < getSpeed(); i++) updatePosition(grid);
	}
}
