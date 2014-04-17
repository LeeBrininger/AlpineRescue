package classes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class loadImage extends Component{
	BufferedImage img;
	
	public loadImage(){
		
	}
	
	public void paint(Graphics g){

		g.drawImage(img, 100, 100, 500, 500, null);
	}
	
	public loadImage(String file){
		try{
			img = ImageIO.read(new File(file));
		}catch (IOException e){
			
		}
	}
	
	
	public static void main(String[] args){
		JFrame f = new JFrame("Load Image Sample");
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.add(new loadImage());
		f.pack();
		f.setVisible(true);
	}
}
