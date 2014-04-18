package classes;

public class Grid {
	private String file;
	private loadImage image;
	
	public Grid(){
		
	}
	public Grid(String files){
		file = files;
	}

	public void printImage(){
		image.printImage(file);
	}
}