package tests;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.AlpineRescue;
import classes.Direction;
import classes.Grid;
import classes.GridCell;


public class Tests {
	
	private AlpineRescue rescue;
	private Grid grid;
	
	@Before
	public void setup(){ 
		rescue = new AlpineRescue();
		grid = rescue.getGrid();
	}


	//Tests that the searchers are initialized correctly
	@Test 
	public void testSearchers() {
		rescue.addSearcher("DogTeam1", "DogTeam", "north", 1, 10, 10);
		rescue.addSearcher("HeliTeam1", "Helicopter", "south", 2, 49, 20);
		rescue.addSearcher("HikerTeam1", "Hiker", "west", 5, 20, 49);
		assertEquals("DogTeam1",rescue.getSearcher("DogTeam1").getName());
		assertEquals("HeliTeam1",rescue.getSearcher("HeliTeam1").getName());
		assertEquals("HikerTeam1",rescue.getSearcher("HikerTeam1").getName());

		assertEquals(new GridCell(10,10), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(49,20), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals(new GridCell(20,49), rescue.getSearcher("HikerTeam1").getCell());
		assertEquals(Direction.NORTH, rescue.getSearcher("DogTeam1").getDirection());
		assertEquals(Direction.SOUTH, rescue.getSearcher("HeliTeam1").getDirection());
		assertEquals(Direction.WEST, rescue.getSearcher("HikerTeam1").getDirection());

	}

	@Test 
	public void testGrid() {
		Grid grid = rescue.getGrid();
		assertEquals(grid.getCellsArray().size(), 
				grid.getNumRows() * grid.getNumColumns());

	}

	@Test 
	public void testAutoMovement() throws InterruptedException {
		rescue.pause();
		rescue.addSearcher("DogTeam1", "DogTeam", "north", 1, 10, 10);
		rescue.addSearcher("HeliTeam1", "Helicopter", "south", 3, 20, 49);
		rescue.addSearcher("HikerTeam1", "Hiker", "west", 2, 49,20);
		long time = System.nanoTime();
		int delay = rescue.getTimerDelay();
		while((System.nanoTime() - time) < delay*10000000);
		assertEquals(new GridCell(23,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals( new GridCell(49,18), rescue.getSearcher("HikerTeam1").getCell());
		while(System.nanoTime() - time < delay*15000000);
		assertEquals(new GridCell(26,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals( new GridCell(49,16), rescue.getSearcher("HikerTeam1").getCell());
	}

	//Tests manual movement of the searchers
	@Test
	public void testManualMovement() {

		rescue.addSearcher("DogTeam1", "DogTeam", "north", 1, 10, 10);

		rescue.getSearcher("DogTeam1").manualPositionUpdate(11, 12, grid);
		
		rescue.addSearcher("HeliTeam1", "Helicopter", "south", 2, 20, 00);
		rescue.getSearcher("HeliTeam1").manualPositionUpdate(15, 49,grid);
		
		rescue.addSearcher("HikerTeam1", "Hiker", "west", 5, 49, 20);
		rescue.getSearcher("HikerTeam1").manualPositionUpdate(49, 18,grid);
		
		
		assertEquals(new GridCell(11,12), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(15,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals( new GridCell(49,18), rescue.getSearcher("HikerTeam1").getCell());

	}


	//tests that the searcher keeps track of the places it has been
	@Test 
	public void testPathSave() {
		rescue.addSearcher("DogTeam1", "DogTeam", "north", 1, 10, 10);
		rescue.getSearcher("DogTeam1").manualPositionUpdate(11, 12,grid);
		rescue.getSearcher("DogTeam1").manualPositionUpdate(13, 15,grid);

		rescue.addSearcher("HeliTeam1", "Helicopter", "south", 2, 49, 20);
		rescue.getSearcher("HeliTeam1").manualPositionUpdate(49,15,grid);
		rescue.getSearcher("HeliTeam1").manualPositionUpdate(49,10,grid);

		rescue.addSearcher("HikerTeam1", "Hiker", "west", 5, 20, 49);
		rescue.getSearcher("HikerTeam1").manualPositionUpdate(18,49, grid);
		rescue.getSearcher("HikerTeam1").manualPositionUpdate(16,49, grid);

		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(10,10)));
		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(11,12)));

		
		assertTrue(rescue.getSearcher("HeliTeam1").getVisited().contains(new GridCell(49,20)));
		assertTrue(rescue.getSearcher("HeliTeam1").getVisited().contains(new GridCell(49,15)));
		
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(20,49)));
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(18,49)));
		
		assertFalse(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(11,11)));
		assertFalse(rescue.getSearcher("HeliTeam1").getVisited().contains(new GridCell(30,49)));
		assertFalse(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(10,20)));
	}
	
	@Test
	public void testEmptyGrid() {
		grid.loadConfig(rescue, "defaultconfig.csv", "AlpineRescuemap.jpg", rescue.getSearcherConfig(), "SOUTH");
		for (GridCell cell : grid.getCellsArray()) assertFalse(cell.isOccupied());
	}
	
	@Test
	public void testNonEmptyGridConfig() {
		grid=new Grid();
		grid.loadConfig(rescue, "occupiedgrid.csv", "AlpineRescuemap.jpg", rescue.getSearcherConfig(), "SOUTH");
		for (GridCell cell : grid.getCellsArray()) {
			
			if (cell.getRow() == 20 && cell.getColumn() ==20)
				assertTrue(cell.isOccupied());
			else if (cell.getRow() == 1 && cell.getColumn() == 36) {
				assertTrue(cell.isOccupied());
			}
			else if (cell.getRow() == 14 && cell.getColumn() == 17) assertTrue(cell.isOccupied());
			else {
				assertFalse(cell.isOccupied());
			}
		}
	}
	
}
