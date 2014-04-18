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
	
	
	
}
