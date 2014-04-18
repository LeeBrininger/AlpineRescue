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
	}
	
	public Dimension getPreferredSize(){
		return null;
	}
	public void paint(Graphics g){
	}
		
	public static void printImage(String file){
	}
	
}
