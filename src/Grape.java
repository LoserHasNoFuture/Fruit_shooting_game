import java.awt.image.BufferedImage;
public class Grape extends Fruit implements Point{

	private static BufferedImage img;

	static{
		img = readImage("grape.png");
	}

	public Grape(){
		super(82,80,25,10);
	}

	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}

	public int getPoint(){
		return this.score;
	}
}