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
import javax.swing.JFrame;
import javax.swing.JPanel;

public class loadImage extends JPanel{
	BufferedImage img;
	private static JPanel imagePanel;
	private static JFrame frame;
	
	
	public loadImage(String files){
		try{
				img = ImageIO.read(new File(files));
			}catch (IOException e){
				System.out.println("not working");
			}
	}
	
	public Dimension getPreferredSize(){
		if(img == null){
			return new Dimension(100,100);
		}else{
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
		
	public static void printImage(String file){
		frame = new JFrame("Load Image Sample");
		imagePanel = new JPanel();
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		imagePanel.add(new loadImage(file));
		frame.add(imagePanel);
		frame.pack();
		frame.setVisible(true);
	}
	
}
