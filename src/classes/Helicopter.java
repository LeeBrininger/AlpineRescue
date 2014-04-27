package classes;

public class Helicopter extends Searcher{
	
	public Helicopter(){}
	
	
	public Helicopter(String name, String direction, int speed, int row, int column, Grid grid){
		super(name,direction,speed,row,column, grid);
	}
	
	public void Symbol(){
		
	}


	@Override
	public void move(Grid grid) {
		updatePosition(grid);
	}

}
