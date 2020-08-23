import java.awt.image.BufferedImage;
public class Apple extends Fruit implements Point{

	private static BufferedImage img;

	static{
		img = readImage("apple.png");
	}


	public Apple(){
		super(60,60,20,15);
	}


	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}
	
	public int getPoint(){
		return this.score;
	}
}