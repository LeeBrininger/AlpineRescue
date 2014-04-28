package classes;

import java.awt.Color;
import java.awt.Graphics;

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


	@Override
	public void draw(int rectX, int rectY, int width, int height, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(rectX, rectY, width, height);
		
	}

}
