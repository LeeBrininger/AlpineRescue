package classes;

import java.awt.Color;
import java.awt.Graphics;

public class Hikers extends Searcher{
	
	private static final int DEFAULT_SPEED = 1;
	
	public Hikers(){}
	
	public Hikers(String name, String direction, int row, int column, Grid grid) {
		super(name,direction,DEFAULT_SPEED,row,column, grid);
	}
	
	public Hikers(String name, String direction, int speed, int row, int column, Grid grid){
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

	@Override
	public void draw(int rectX, int rectY, int width, int height, Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(rectX, rectY, width, height);		
	}

	@Override
	public String getType() {
		return "Hikers";
	}

}
