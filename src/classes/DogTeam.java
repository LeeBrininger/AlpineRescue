package classes;

public class DogTeam extends Searcher{
	private String direction;
	private int speed;
	private Position dogpos;
	
	
	public DogTeam(){}
	
	public DogTeam(String directions, int mph, int ypos, int xpos){
		direction = directions;
		speed = mph;
		dogpos = new Position(xpos, ypos);
	}
	
	public void Symbol(){
		
	}

}
