package tests;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.AlpineRescue;
import classes.Direction;
import classes.Grid;
import classes.GridCell;


public class part1tests {
	
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
		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);
		rescue.addSearcher("HeliTeam1", "helicopter", "south", 2, 49, 20);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 20, 49);
		assertEquals("DogTeam1",rescue.getSearcher("DogTeam1").getName());
		assertEquals("HeliTeam1",rescue.getSearcher("HeliTeam1").getName());
		assertEquals("HikerTeam1",rescue.getSearcher("HikerTeam1").getName());

		assertEquals(new GridCell(10,10), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(20,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals(new GridCell(49,20), rescue.getSearcher("HikerTeam1").getCell());
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

	/*@Test
	public void testImage() {
		rescue = new AlpineRescue("AlpineRescuemap.jpg");
		rescue.loadGrid();
		rescue.printGrid();
	}*/

	@Test 
	public void testAutoMovement() throws InterruptedException {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);
		rescue.addSearcher("HeliTeam1", "helicopter", "south", 3, 49, 20);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 2, 20, 49);
		long time = System.nanoTime();
		while(System.nanoTime() - time < 700000000);
		assertEquals(new GridCell(9,10), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(23,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals( new GridCell(49,18), rescue.getSearcher("HikerTeam1").getCell());
		while(System.nanoTime() - time < 1000000000);
		assertEquals(new GridCell(8,10), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(26,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals( new GridCell(49,16), rescue.getSearcher("HikerTeam1").getCell());
	}

	//Tests manual movement of the searchers
	@Test
	public void testManualMovement() {

		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);

		rescue.getSearcher("DogTeam1").manualPositionUpdate(11, 12, grid);
		
		rescue.addSearcher("HeliTeam1", "helicopter", "south", 2, 20, 00);
		rescue.getSearcher("HeliTeam1").manualPositionUpdate(15, 49,grid);
		
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 49, 20);
		rescue.getSearcher("HikerTeam1").manualPositionUpdate(49, 18,grid);
		
		
		assertEquals(new GridCell(11,12), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(15,49), rescue.getSearcher("HeliTeam1").getCell());
		assertEquals( new GridCell(49,18), rescue.getSearcher("HikerTeam1").getCell());

	}


	//tests that the searcher keeps track of the places it has been
	@Test 
	public void testPathSave() {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);
		rescue.getSearcher("DogTeam1").manualPositionUpdate(11, 12,grid);
		rescue.getSearcher("DogTeam1").manualPositionUpdate(13, 15,grid);

		rescue.addSearcher("HeliTeam1", "helicopter", "south", 2, 49, 20);
		rescue.getSearcher("HeliTeam1").manualPositionUpdate(15, 49,grid);
		rescue.getSearcher("HeliTeam1").manualPositionUpdate(10, 49,grid);

		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 20, 49);
		rescue.getSearcher("HikerTeam1").manualPositionUpdate(49, 18, grid);
		rescue.getSearcher("HikerTeam1").manualPositionUpdate(49, 16, grid);

		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(10,10)));
		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(11,12)));

		
		assertTrue(rescue.getSearcher("HeliTeam1").getVisited().contains(new GridCell(20,49)));
		assertTrue(rescue.getSearcher("HeliTeam1").getVisited().contains(new GridCell(15,49)));
		
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(49,20)));
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(49,18)));
		
		assertFalse(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(11,11)));
		assertFalse(rescue.getSearcher("HeliTeam1").getVisited().contains(new GridCell(30,49)));
		assertFalse(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(10,20)));
	}
	
	/*@Test
	public void testNonEmptyGridConfig() {
		
	}*/
	
}
