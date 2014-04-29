package classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import javax.imageio.ImageIO;

public class loadImage{
	static BufferedImage img;
	
	
	public loadImage(String files){
		try{
				img = ImageIO.read(new File(files));
			}catch (IOException e){
				System.out.println("Could not find specified image file: " + files);
			}
	}
	
	public Dimension getPreferredSize(){
		if(img == null){
			return new Dimension(100,100);
		}else{
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}
	
	//draws the image behind the grid, the scaled to the size of the grid
	public static void paint(Graphics g){
		g.drawImage(img, 0, 0, Grid.numColumns *GridCell.CELL_WIDTH, Grid.numRows *GridCell.CELL_WIDTH, null);
	}
}