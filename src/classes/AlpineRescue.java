package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class AlpineRescue extends JFrame{
	private Timer timer;
	// timer delay is in MILLISECONDS
	private final int TIMER_DELAY = 400;
	private String file = "AlpineRescuemap.jpg";
	private Grid grid;
	private Searcher searcher;
	private Map<String, Searcher> searchers;
	private boolean isPaused;
	
	public AlpineRescue(){
		searchers = new HashMap<String, Searcher>();
		grid = new Grid();
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		timer.start();
		isPaused = false;
	}
	
	public AlpineRescue(String mapfile){
		file = mapfile;
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		timer.start();
		isPaused = false;
	}
	
	public void loadGrid(){
		grid = new Grid(file);
	}
	public void addSearcher(String name, String type, String direction, int speed, int row, int column) {
		if(type.equals("dogteam")){
			searcher = new DogTeam(name, direction, speed, row, column, grid);
		}
		if(type.equals("helicopter")){
			searcher = new Helicopter(name, direction, speed, row, column, grid);
		}
		if(type.equals("hiker")){
			searcher = new Hiker(name, direction, speed, row, column,grid);
		}
		searchers.put(name, searcher);
	}
	
	public Searcher getSearcher(String name){
		return searchers.get(name);
	}
	
	public Map<String,Searcher> getSearchers() {
		return searchers;
	}
	
	public void printGrid(){
		grid.printImage();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void pause() {
		isPaused = !isPaused;
		if (isPaused) timer.start();
		else timer.stop();
	}
	
	class TimerListener implements ActionListener {

		private AlpineRescue rescue;
		
		public TimerListener(AlpineRescue rescue) {
			this.rescue = rescue;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Searcher searcher : rescue.getSearchers().values()) searcher.move(grid);
		}
		
	}

}
