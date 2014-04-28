package classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class AlpineRescue extends JFrame{
	private Timer timer;
	// timer delay is in MILLISECONDS
	private final int TIMER_DELAY = 1000;
	private String mapFile = "AlpineRescuemap.jpg";
	private static final String DEFAULT_FILE = "AlpineRescuemap.jpg"; 
	private static final String DEFAULT_SEARCHER_CONFIG = "searcherConfig.csv";
	private Grid grid;
	private Searcher searcher;
	private Map<String, Searcher> searchers;
	private Map<String, String> searcherMap;
	private boolean isPaused;
	
	public AlpineRescue(){
		searchers = new HashMap<String, Searcher>();
		grid = new Grid();
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		isPaused = true;
		searcherMap = new HashMap<String,String>();
		loadConfig(DEFAULT_SEARCHER_CONFIG);
		ControlPanel control = new ControlPanel(this);
		add(control);
	}
	
	public AlpineRescue(String gridFile, String searcherConfig, String mapFile){
		searchers = new HashMap<String, Searcher>();
		this.mapFile = mapFile;
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		isPaused = true;
		searcherMap = new HashMap<String,String>();
		loadConfig(searcherConfig);
		grid = new Grid();
		grid.loadConfig(this, gridFile, mapFile, searcherMap, "North");
		
		//GUI initialization
		int width = grid.getNumColumns()*GridCell.getCellWidth() + 20;
		int height = grid.getNumRows()*GridCell.getCellWidth() + 275;
		setSize(new Dimension(width, height));
		setTitle("Alpine Rescue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ControlPanel control = new ControlPanel(this);
		
		add(BorderLayout.CENTER, grid);
		add(BorderLayout.SOUTH, control);
	}
	
	public void loadConfig(String searcherConfig) {
		try {
			FileReader reader = new FileReader(searcherConfig);
			Scanner scan = new Scanner(reader);
			
			String line;
			String[] splitLine;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				splitLine = line.split(",");
				searcherMap.put(splitLine[0], splitLine[1]);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void addSearcher(String name, String type, String direction, int row, int column) {
		int speed;
		if(type.equals("DogTeam")){
			speed = DogTeam.getDefaultSpeed();
			searcher = new DogTeam(name, direction, speed, row, column, grid);
		} else if(type.equals("Helicopter")){
			speed = Helicopter.getDefaultSpeed();
			searcher = new Helicopter(name, direction, speed, row, column, grid);
		} else if(type.equals("Hiker")){
			speed = Hiker.getDefaultSpeed();
			searcher = new Hiker(name, direction, speed, row, column,grid);
		}
		searchers.put(name, searcher);
}
	
	public void addSearcher(String name, String type, String direction, int speed, int row, int column) {
			if(type.equals("DogTeam")){
				searcher = new DogTeam(name, direction, speed, row, column, grid);
			} else if(type.equals("Helicopter")){
				searcher = new Helicopter(name, direction, speed, row, column, grid);
			} else if(type.equals("Hiker")){
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
	
	public Map<String,String> getSearcherConfig() {
		return searcherMap;
	}
	
	public void pause() {
		isPaused = !isPaused;
		if (!isPaused) timer.start();
		else timer.stop();
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public int getTimerDelay() {
		return TIMER_DELAY;
	}
	
	class TimerListener implements ActionListener {

		private AlpineRescue rescue;
		
		public TimerListener(AlpineRescue rescue) {
			this.rescue = rescue;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Searcher searcher : rescue.getSearchers().values()) searcher.move(grid);
			grid.repaint();
		}
		
	}
	
	public static void main(String[] args){
		AlpineRescue rescue = new AlpineRescue("occupiedgrid.csv", "searcherConfig.csv", "AlpineRescuemap.jpg");
		rescue.setVisible(true);
	}
	

}
