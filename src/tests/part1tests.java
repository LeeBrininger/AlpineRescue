package tests;



import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.AlpineRescue;



public class part1tests {
	
	static AlpineRescue rescue;
	
	// Makes a new game and loads configuration files
	@BeforeClass
	public static void setup(){ 
		rescue = new AlpineRescue("AlpineRescuemap.jpg");
	}
	
	
	@Test
	public void testLoad() {
		assertTrue(rescue.loadgrid() != null);
		
		
	}
	
}
