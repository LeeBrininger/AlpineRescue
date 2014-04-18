package tests;



import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.AlpineRescue;
import classes.Position;



public class part1tests {
	
	static AlpineRescue rescue;
	
	// Makes a new game and loads configuration files
	@BeforeClass
	public static void setup(){ 
		rescue = new AlpineRescue();
	}
	
	@Test
	public void testLoad() {
		rescue = new AlpineRescue("AlpineRescuemap.jpg");
		
	}
	@Test //Just tests if they were all correctly assigned
	public void testSearchers() {
		rescue.addSearcher("DogTeam1", "dogteam", "north", 10, 100, 100);
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		equals(rescue.getSearcher("DogTeam1").Test() == 1);
		equals(rescue.getSearcher("HeleTeam1").Test() == 2);
		equals(rescue.getSearcher("HikerTeam1").Test() == 3);
	}
	@Test
	public void testGrid() {

		
		
	}
	@Test
	public void testImage() {

		
		
	}
	@Test //need timer class to test
	public void testAutoMovement() {
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		
	}
	//makes sure the manual position writes in correctly
	@Test
	public void testManualMovement() {
		Position dogPos = new Position(110,120);
		Position helePos = new Position(150,500);
		Position hikerPos = new Position(500,180);
		
		rescue.addSearcher("DogTeam1", "dogteam", "north", 10, 100, 100);
		System.out.println(rescue.getSearcher("DogTeam1").getPosition());
		rescue.getSearcher("DogTeam1").manualPosition(110, 120);
		
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.getSearcher("HeleTeam1").manualPosition(150, 500);
		
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		rescue.getSearcher("DogTeam1").manualPosition(500, 180);
		
		
		equals(rescue.getSearcher("DogTeam1").getPosition() == dogPos);
		equals(rescue.getSearcher("DogTeam1").getPosition() == helePos);
		equals(rescue.getSearcher("DogTeam1").getPosition() == hikerPos);
		
	}
	
	
	@Test 
	public void testPathSave() {
		Position dogvisited = new Position(110, 120);
		Position helevisited = new Position(200, 500);
		Position hikervisited = new Position(110, 120);
		
		rescue.addSearcher("DogTeam1", "dogteam", "north", 10, 100, 100);
		rescue.getSearcher("DogTeam1").manualPosition(110, 120);
		rescue.getSearcher("DogTeam1").manualPosition(130, 150);
		
		rescue.addSearcher("HeleTeam1", "helicopter", "south", 20, 200, 500);
		rescue.getSearcher("HeleTeam1").manualPosition(150, 500);
		rescue.getSearcher("HeleTeam1").manualPosition(100, 500);
		
		rescue.addSearcher("HikerTeam1", "hiker", "west", 5, 500, 200);
		rescue.getSearcher("DogTeam1").manualPosition(500, 180);
		rescue.getSearcher("DogTeam1").manualPosition(500, 160);
		
		equals(rescue.getSearcher("DogTeam1").getPosition() == dogvisited);
		equals(rescue.getSearcher("DogTeam1").getPosition() == helevisited);
		equals(rescue.getSearcher("DogTeam1").getPosition() == hikervisited);
		
	}
	
}
