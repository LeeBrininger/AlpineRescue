package classes;

public class Helicopter extends Searcher{
	
	private static final int DEFAULT_SPEED = 3;
	
	public Helicopter(){}
	
	public Helicopter(String name, String direction, int row, int column, Grid grid) {
		super(name,direction,DEFAULT_SPEED,row,column,grid);
		setFlyOver(true);
	}
	
	public Helicopter(String name, String direction, int speed, int row, int column, Grid grid){
		super(name,direction,speed,row,column, grid);
		setFlyOver(true);
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
