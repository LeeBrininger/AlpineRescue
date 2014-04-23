package tests;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.AlpineRescue;
import classes.Direction;
import classes.Grid;
import classes.GridCell;



public class part1tests {
	
	static AlpineRescue rescue;
	
	
	@BeforeClass
	public static void setup(){ 
		rescue = new AlpineRescue();
	}

	
	//Tests that the searchers are initialized correctly
	@Test 
	public void testSearchers() {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 2, 20, 50);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 50, 20);
		assertEquals("DogTeam1",rescue.getSearcher("DogTeam1").getName());
		assertEquals("HeleTeam1",rescue.getSearcher("HeleTeam1").getName());
		assertEquals("HikerTeam1",rescue.getSearcher("HikerTeam1").getName());
		
		System.out.println(rescue.getSearcher("DogTeam1").getCell().getRow());
		System.out.println(rescue.getSearcher("DogTeam1").getCell().getColumn());
		System.out.println(new GridCell(10,10).getRow());
		
		
		assertEquals(new GridCell(10,10), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(20,50), rescue.getSearcher("HeleTeam1").getCell());
		assertEquals(new GridCell(50,20), rescue.getSearcher("HikerTeam1").getCell());
		assertEquals(Direction.NORTH, rescue.getSearcher("DogTeam1").getDirection());
		assertEquals(Direction.SOUTH, rescue.getSearcher("HeleTeam1").getDirection());
		assertEquals(Direction.WEST, rescue.getSearcher("HikerTeam1").getDirection());
		
	}
	
	@Test 
	public void testGrid() {
		assertEquals(rescue.getGrid().getCellsArray().size(), 
				rescue.getGrid().getNumRows() * rescue.getGrid().getNumColumns());
		
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
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 3, 20, 50);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 2, 50, 20);
		rescue.wait(5);
		assertEquals(new GridCell(10,15), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(20,35), rescue.getSearcher("HeleTeam1").getCell());
		assertEquals( new GridCell(40,20), rescue.getSearcher("HikerTeam1").getCell());
		rescue.wait(1);
		assertEquals(new GridCell(10,16), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(20,32), rescue.getSearcher("HeleTeam1").getCell());
		assertEquals( new GridCell(38,20), rescue.getSearcher("HikerTeam1").getCell());
	}
	
	//Tests manual movement of the searchers
	@Test
	public void testManualMovement() {
		
		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);
		rescue.getSearcher("DogTeam1").manualPosition(11, 12);
		
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 2, 20, 00);
		rescue.getSearcher("HeleTeam1").manualPosition(15, 50);
		
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 50, 20);
		rescue.getSearcher("HikerTeam1").manualPosition(50, 18);
		
		
		assertEquals(new GridCell(11,12), rescue.getSearcher("DogTeam1").getCell());
		assertEquals(new GridCell(15,50), rescue.getSearcher("HeleTeam1").getCell());
		assertEquals( new GridCell(50,18), rescue.getSearcher("HikerTeam1").getCell());
		
	}
	
	
	//tests that the searcher keeps track of the places it has been
	@Test 
	public void testPathSave() {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 1, 10, 10);
		rescue.getSearcher("DogTeam1").manualPosition(11, 12);
		rescue.getSearcher("DogTeam1").manualPosition(13, 15);

		rescue.addSearcher("HeleTeam1", "helicopter", "south", 2, 20, 50);
		rescue.getSearcher("HeleTeam1").manualPosition(15, 50);
		rescue.getSearcher("HeleTeam1").manualPosition(10, 50);

		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 50, 20);
		rescue.getSearcher("HikerTeam1").manualPosition(50, 18);
		rescue.getSearcher("HikerTeam1").manualPosition(50, 16);

		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(10,10)));
		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(11,12)));
		
		assertTrue(rescue.getSearcher("HeleTeam1").getVisited().contains(new GridCell(20,50)));
		assertTrue(rescue.getSearcher("HeleTeam1").getVisited().contains(new GridCell(15,50)));
		
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(50,20)));
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(50,18)));
		
		assertFalse(rescue.getSearcher("DogTeam1").getVisited().contains(new GridCell(11,11)));
		assertFalse(rescue.getSearcher("HeleTeam1").getVisited().contains(new GridCell(54,50)));
		assertFalse(rescue.getSearcher("HikerTeam1").getVisited().contains(new GridCell(10,20)));
	}
	
}
