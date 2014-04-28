package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class DogTeam extends Searcher{
	
	private Random rand;
	private static final int DEFAULT_SPEED = 1;
	
	public DogTeam(){}
	
	public DogTeam(String name, String direction, int row, int column, Grid grid) {
		super(name,direction,DEFAULT_SPEED, row, column, grid);
	}
	
	public DogTeam(String name, String direction, int speed, int row, int column, Grid grid){
		super(name,direction,speed,row,column,grid);
		rand = new Random();
	}
	
	public void Symbol(){
		//TODO
	}
	
	public void erratic() {
		int directionChange = rand.nextInt(3)-1;
		int ordinal = getDirection().ordinal() + directionChange;
		if (ordinal == getDirection().values().length) ordinal = 0;
		else if (ordinal == -1) ordinal = getDirection().values().length-1;
		setDirection(getDirection().values()[ordinal]);
	}
	
	public static int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

	@Override
	public void move(Grid grid) {
		erratic();
		for (int i =0; i < getSpeed(); i++) updatePosition(grid);
	}

	@Override
	public void draw(int rectX, int rectY, int width, int height, Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(rectX, rectY, width, height);
		
	}

}
