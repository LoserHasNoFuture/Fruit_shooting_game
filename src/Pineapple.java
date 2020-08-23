import java.awt.image.BufferedImage;
public class Pineapple extends Fruit implements Point{

	private static BufferedImage img;

	static{
		img = readImage("pineapple.png");
	}

	public Pineapple(){
		super(100,70,18,5);
	}

	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}

	public int getPoint(){
		return this.score;
	}

}
