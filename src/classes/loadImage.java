package classes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class loadImage{
	static BufferedImage img;
	private static JPanel imagePanel;
	private static JFrame frame;
	private ImageIcon image; 
	
	
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
	
	public static void paint(Graphics g){
		g.drawImage(img, 0, 0, Grid.numColumns *GridCell.CELL_WIDTH, Grid.numRows *GridCell.CELL_WIDTH, null);
	}
}