package classes;

import java.awt.Color;
import java.awt.Graphics;

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


	@Override
	public void draw(int rectX, int rectY, int width, int height, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(rectX, rectY, width, height);
		
	}

	@Override
	public String getType() {
		return "Helicopter";
	}

}
