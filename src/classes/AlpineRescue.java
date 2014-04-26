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
	private final static String DEFAULT_FILE = "AlpineRescuemap.jpg"; 
	private static final String DEFAULT_SEARCHER_CONFIG = "searcherConfig.csv";
	private final int DEFAULT_SPEED = 1;
	private final String DEFAULT_DIRECTION = "SOUTH";
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
		ControlPanel control = new ControlPanel();
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
		grid.loadConfig(this, gridFile, mapFile, searcherMap, DEFAULT_SPEED, DEFAULT_DIRECTION);
		
		//GUI initialization
		setSize(new Dimension(625, 875));
		setTitle("Alpine Rescue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ControlPanel control = new ControlPanel();
		add(BorderLayout.SOUTH, control);
		add(BorderLayout.CENTER, grid);
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
	
	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}
	
	public String getDefaultDirection() {
		return DEFAULT_DIRECTION;
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
		rescue.pause();
		rescue.setVisible(true);
	}
	

}
