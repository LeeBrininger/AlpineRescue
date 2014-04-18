package classes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AlpineRescue {
	private int time;
	private String file = "AlpineRescuemap.jpg";
	private Grid grid;
	private Searcher searcher;
	private Map<String, Searcher> searchers = new HashMap<String, Searcher>();
	private static AlpineRescue rescue = new AlpineRescue();
	
	public AlpineRescue(){
	}
	
	public AlpineRescue(String mapfile){
		file = mapfile;
	}
	
	public void loadGrid(){
		grid = new Grid(file);
	}
	public void addSearcher(String name, String type, String direction, int speed, int xpos, int ypos) {
		if(type == "dogteam"){
			searcher = new DogTeam(direction, speed, xpos, ypos);
		}
		if(type == "helecopter"){
			searcher = new Helicopter(direction, speed, xpos, ypos);
		}
		if(type == "hiker"){
			searcher = new Hiker(direction, speed, xpos, ypos);
		}
		searchers.put(name, searcher);
	}
	
	public Searcher getSearcher(String name){
		return searchers.get(name);
	}
	
	public void printGrid(){
		grid.printImage();
	}
	public static void main(String Args[]){
		rescue.loadGrid();
		rescue.printGrid();
	}


}
