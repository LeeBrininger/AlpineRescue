package classes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AlpineRescue {
	private int time;
	private String file = "AlpineRescuemap.jpg";
	static loadImage image;
	
	public AlpineRescue(String mapfile){
		file = mapfile;
		
		

	}
	
	public void loadGrid(){
		loadImage image = new loadImage(file);
		image.setFile(file);
	}
	public void addSearcher(){
		
	}
	public static void main(String Args[]){
		//loadGrid();
		image.printImage();
	}
}
