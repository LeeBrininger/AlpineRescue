package classes;

public class Hiker extends Searcher{
	
	public Hiker(){}
	
	public Hiker(String name, String direction, int speed, int row, int column, Grid grid){
		super(name,direction,speed,row,column, grid);
	}

	public void Symbol(){
		
	}

	@Override
	public void move(Grid grid) {
		updatePosition(grid);
	}
}
