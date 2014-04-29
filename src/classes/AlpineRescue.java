package classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class AlpineRescue extends JFrame{
	private Timer timer;
	// timer delay is in MILLISECONDS
	private final int TIMER_DELAY = 1000;
	private String mapFile = "AlpineRescuemap.jpg";
	private static final String DEFAULT_MAP_FILE = "AlpineRescuemap.jpg"; 
	private static final String DEFAULT_SEARCHER_CONFIG = "searcherConfig.csv";
	private static final String DEFAULT_GRID_FILE = "occupiedgrid.csv";
	private Grid grid;
	private Searcher searcher;
	private Map<String, Searcher> searchers;
	private Map<String, String> searcherMap;
	private boolean isPaused;
	private ControlPanel control;
	private Searcher selectedSearcher; //is currently set to null if no searcher selected
	private GridCell selectedCell;
	
	//default constructor that calls parameterized constructor with default options
	public AlpineRescue(){
		this(DEFAULT_GRID_FILE, DEFAULT_SEARCHER_CONFIG, DEFAULT_MAP_FILE);
	}
	
	public AlpineRescue(String gridFile, String searcherConfig, String mapFile){
		searchers = new HashMap<String, Searcher>();
		this.mapFile = mapFile;
		timer = new Timer(TIMER_DELAY, new TimerListener(this));
		isPaused = true;
		searcherMap = new HashMap<String,String>();
		loadConfig(searcherConfig);
		grid = new Grid(mapFile);
		grid.loadConfig(this, gridFile, mapFile, searcherMap, "South");

		control = new ControlPanel(this);
		add(BorderLayout.SOUTH, control);
		add(BorderLayout.CENTER, grid);
		grid.addMouseListener(new AlpineListener());
		//so that selectedCell is not null
		selectedCell = grid.getCellAt(0, 0);
		
		//GUI initialization
		int width = grid.getNumColumns()*GridCell.getCellWidth() + 250;
		int height = grid.getNumRows()*GridCell.getCellWidth() + 250;
		setSize(new Dimension(width, height));
		setTitle("Alpine Rescue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem saveSearch = new JMenuItem("Save Search");
		JMenuItem loadSearch = new JMenuItem("Load Existing Search");
		JMenuItem exit = new JMenuItem("Exit");
		file.add(saveSearch);
		file.add(loadSearch);
		file.add(exit);
		menu.add(file);
		saveSearch.addActionListener(new MenuListener());
		loadSearch.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		setJMenuBar(menu);

	}
	
	// loads the different types of searchers from a configuration file
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
	
	// adds a searcher to the interface (without specified speed)
	public void addSearcher(String name, String type, String direction, int row, int column) {
		int speed;
		if(type.equals("DogTeam")){
			speed = DogTeam.getDefaultSpeed();
			searcher = new DogTeam(name, direction, speed, row, column, grid);
		} else if(type.equals("Helicopter")){
			speed = Helicopter.getDefaultSpeed();
			searcher = new Helicopter(name, direction, speed, row, column, grid);
		} else if(type.equals("Hikers")){
			speed = Hikers.getDefaultSpeed();
			searcher = new Hikers(name, direction, speed, row, column,grid);
		}
		searchers.put(name, searcher);
	}
	
	// adds a seracher to the interface (with speed specified)
	public void addSearcher(String name, String type, String direction, int speed, int row, int column) {
			if(type.equals("DogTeam")){
				searcher = new DogTeam(name, direction, speed, row, column, grid);
			} else if(type.equals("Helicopter")){
				searcher = new Helicopter(name, direction, speed, row, column, grid);
			} else if(type.equals("Hikers")){
				searcher = new Hikers(name, direction, speed, row, column,grid);
			}
			searchers.put(name, searcher);
	}
	
	public Searcher getSearcher(String name){
		return searchers.get(name);
	}
	
	public Map<String,Searcher> getSearchers() {
		return searchers;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Map<String,String> getSearcherConfig() {
		return searcherMap;
	}
	
	//Template-ed function for accessing maps 
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	// pauses the timer
	public void pause() {
		isPaused = !isPaused;
		if (!isPaused) timer.start();
		else timer.stop();
	}
	
	// returns whether or not the timer is paused
	public boolean isPaused() {
		return isPaused;
	}
	
	// returns the delay of the timer (time between fired events in milliseconds)
	public int getTimerDelay() {
		return TIMER_DELAY;
	}
	
	public Searcher getSelectedSearcher() {
		return selectedSearcher;
	}
	
	public GridCell getSelectedCell() {
		return selectedCell;
	}
	
	public AlpineRescue getInstance() {
		return this;
	}
	
	// sets the cell that has been selected via mouse click
	public void setSelectedCell() {
		selectedCell.setSelected(true);
		
		if (selectedCell.isOccupied()) {		
			selectedSearcher = selectedCell.getSearcher();
			if (selectedSearcher!= null) {
				control.setSelectedSearcherName(selectedSearcher.getName());
				control.setSelectedSearcherSpeed(Integer.toString(selectedSearcher.getSpeed()));
				control.setSelectedSearcherLocation(selectedCell.toString());
				control.setSelectedSearcherDirection(selectedSearcher.getDirection().toString());
			}
		} else {
			selectedSearcher = null;
			control.setSelectedSearcherName("(none selected)");
			control.setSelectedSearcherSpeed("");
			control.setSelectedSearcherLocation(selectedCell.toString());
			control.setSelectedSearcherDirection("");
		}
		grid.repaint();
	}
	
	// listener class for the timer
	class TimerListener implements ActionListener {

		private AlpineRescue rescue;
		
		public TimerListener(AlpineRescue rescue) {
			this.rescue = rescue;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Searcher searcher : rescue.getSearchers().values()) searcher.move(grid);
			setSelectedCell();
			grid.repaint();
		}
		
	}
	
	// listener for the JMenu
	class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (((JMenuItem) arg0.getSource()).getText().equals("Exit"))
				System.exit(0);
			else if (((JMenuItem) arg0.getSource()).getText().equals("Save Search")) {
				grid.saveGrid(getInstance());
			} else {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				File file = j.getSelectedFile();
				String filename = file.getAbsolutePath();
				AlpineRescue rescue = getInstance();
				rescue.dispose();
				rescue = new AlpineRescue(filename, "searcherConfig.csv", mapFile);
				rescue.setVisible(true);
			}
		}

	}
	
	// Mouse Listener
	private class AlpineListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			selectedCell.setSelected(false); //previous selected cell is not longer selected
			
			selectedCell = grid.getCellAt(arg0.getY()/GridCell.getCellWidth(), arg0.getX()/GridCell.getCellWidth());
			
			setSelectedCell();
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
	}
	
	
	public static void main(String[] args){
		AlpineRescue rescue = new AlpineRescue("defaultconfig.csv", "searcherConfig.csv", "AlpineRescuemap.jpg");
		rescue.setVisible(true);
	}
	
}
