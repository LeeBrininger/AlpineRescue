package classes;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AlpineRescue {
	private int time;
	private String file;

	
	public AlpineRescue(String mapfile){
		
		loadImage image = new loadImage();
		file = mapfile;
		
		

	}
	
	public void loadGrid(){
		loadImage image = new loadImage(file);
	}
	public void addSearcher(){
		
	}
	public static void main(String Args[]){
		
	}
}
