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
		super();
		searchers = new HashMap<String, Searcher>();
		grid = new Grid();
	}
	
	public AlpineRescue(String mapfile){
		this();
		file = mapfile;
	}
	
	public void loadGrid(){
		grid = new Grid(file);
	}
	public void addSearcher(String name, String type, String direction, int speed, int row, int column) {
		if(type.equals("dogteam")){
			searcher = new DogTeam(name, direction, speed, row, column);
		}
		if(type.equals("helicopter")){
			searcher = new Helicopter(name, direction, speed, row, column);
		}
		if(type.equals("hiker")){
			searcher = new Hiker(name, direction, speed, row, column);
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
