package classes;

import java.util.HashMap;
import java.util.Map;

public class AlpineRescue {
	private int time;
	private String file = "AlpineRescuemap.jpg";
	private Grid grid;
	private Searcher searcher;
	private Map<String, Searcher> searchers;
	
	public AlpineRescue(){
		searchers = new HashMap<String, Searcher>();
	}
	
	public AlpineRescue(String mapfile){
		file = mapfile;
		searchers = new HashMap<String, Searcher>();
	}
	
	public void loadGrid(){
		grid = new Grid(file);
	}
	public void addSearcher(String name, String type, String direction, int speed, int xpos, int ypos) {
		if(type.equals("dogteam")){
			searcher = new DogTeam(name, direction, speed, xpos, ypos);
		}
		if(type.equals("helicopter")){
			searcher = new Helicopter(name, direction, speed, xpos, ypos);
		}
		if(type.equals("hiker")){
			searcher = new Hiker(name, direction, speed, xpos, ypos);
		}
		searchers.put(name, searcher);
	}
	
	public Searcher getSearcher(String name){
		return searchers.get(name);
	}
	
	public void printGrid(){
		grid.printImage();
	}
	
	
	public Grid getGrid() {
		return grid;
	}

}
