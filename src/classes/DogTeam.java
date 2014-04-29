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
	
	// Dog movement is erratic, so they have an equal chance of moving the same direction
	// or changing direction by 45 degrees left or right.
	public void erratic() {
		int directionChange = rand.nextInt(3)-1;
		int ordinal = getDirection().ordinal() + directionChange;
		if (ordinal == Direction.values().length) ordinal = 0;
		else if (ordinal == -1) ordinal = Direction.values().length-1;
		setDirection(Direction.values()[ordinal]);
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

	@Override
	public String getType() {
		return "Dog Team";
	}

}
