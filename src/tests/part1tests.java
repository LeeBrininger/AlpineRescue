package tests;



import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.AlpineRescue;
import classes.Direction;
import classes.Position;



public class part1tests {
	
	static AlpineRescue rescue;
	
	
	@BeforeClass
	public static void setup(){ 
		rescue = new AlpineRescue();
	}
	
	@Test 
	public void testLoad() {
		rescue = new AlpineRescue("AlpineRescuemap.jpg");
		
	}
	
	//Tests that the searchers are initialized correctly
	@Test 
	public void testSearchers() {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 10, 100, 100);
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		assertEquals("DogTeam1",rescue.getSearcher("DogTeam1").getName());
		assertEquals("HeleTeam1",rescue.getSearcher("HeleTeam1").getName());
		assertEquals("HikerTeam1",rescue.getSearcher("HikerTeam1").getName());
		assertEquals(new Position(100,100), rescue.getSearcher("DogTeam1").getPosition());
		assertEquals(new Position(200,500), rescue.getSearcher("HeleTeam1").getPosition());
		assertEquals(new Position(500,200), rescue.getSearcher("DogTeam1").getPosition());
		assertEquals(Direction.NORTH, rescue.getSearcher("DogTeam1").getDirection());
		assertEquals(Direction.SOUTH, rescue.getSearcher("HeleTeam1").getDirection());
		assertEquals(Direction.WEST, rescue.getSearcher("HikerTeam1").getDirection());
		
	}
	/*@Test 
	public void testGrid() {
		
	}*/
	
	/*@Test
	public void testImage() {
		rescue = new AlpineRescue("AlpineRescuemap.jpg");
		rescue.loadGrid();
		rescue.printGrid();
	}*/
	
	/*@Test 
	public void testAutoMovement() {
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		
	}*/
	
	//Tests manual movement of the searchers
	@Test
	public void testManualMovement() {
		
		rescue.addSearcher("DogTeam1", "dogteam", "north", 10, 100, 100);
		rescue.getSearcher("DogTeam1").manualPosition(110, 120);
		
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.getSearcher("HeleTeam1").manualPosition(150, 500);
		
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		rescue.getSearcher("HikerTeam1").manualPosition(500, 180);
		
		
		assertEquals(new Position(110,120), rescue.getSearcher("DogTeam1").getPosition());
		assertEquals(new Position(150,500), rescue.getSearcher("HeleTeam1").getPosition());
		assertEquals( new Position(500,180), rescue.getSearcher("HikerTeam1").getPosition());
		
	}
	
	
	//tests that the searcher keeps track of the places it has been
	@Test 
	public void testPathSave() {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 10, 100, 100);
		rescue.getSearcher("DogTeam1").manualPosition(110, 120);
		rescue.getSearcher("DogTeam1").manualPosition(130, 150);

		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.getSearcher("HeleTeam1").manualPosition(150, 500);
		rescue.getSearcher("HeleTeam1").manualPosition(100, 500);

		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		rescue.getSearcher("HikerTeam1").manualPosition(500, 180);
		rescue.getSearcher("HikerTeam1").manualPosition(500, 160);

		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new Position(100,100)));
		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new Position(110,120)));
		assertTrue(rescue.getSearcher("DogTeam1").getVisited().contains(new Position(130,150)));
		
		assertTrue(rescue.getSearcher("HeleTeam1").getVisited().contains(new Position(200,500)));
		assertTrue(rescue.getSearcher("HeleTeam1").getVisited().contains(new Position(150,500)));
		assertTrue(rescue.getSearcher("HeleTeam1").getVisited().contains(new Position(100,500)));
		
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new Position(500,200)));
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new Position(500,180)));
		assertTrue(rescue.getSearcher("HikerTeam1").getVisited().contains(new Position(500,160)));
		
		assertFalse(rescue.getSearcher("DogTeam1").getVisited().contains(new Position (111,111)));
		assertFalse(rescue.getSearcher("HeleTeam1").getVisited().contains(new Position (541,500)));
		assertFalse(rescue.getSearcher("HikerTeam1").getVisited().contains(new Position (100,200)));
	}
	
}
