package classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private final static String DEFAULT_FILE = "AlpineRescuemap.jpg"; 
	private Grid grid;
	private Searcher searcher;
	private Map<String, Searcher> searchers;
	private boolean isPaused;
	
	public AlpineRescue(){
		this(DEFAULT_FILE);
		/*searchers = new HashMap<String, Searcher>();
		grid = new Grid();
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		timer.start();
		isPaused = false;
		
		ControlPanel control = new ControlPanel();
		add(control);*/
	}
	
	public AlpineRescue(String mapfile){
		searchers = new HashMap<String, Searcher>();
		file = mapfile;
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		timer.start();
		isPaused = false;
		loadGrid();
		
		//GUI initialization
		setSize(new Dimension(900, 720));
		setTitle("Alpine Rescue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ControlPanel control = new ControlPanel();
		add(BorderLayout.SOUTH, control);
	}
	
	public static void main(String[] args){
		AlpineRescue rescue = new AlpineRescue();
		rescue.setVisible(true);
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
