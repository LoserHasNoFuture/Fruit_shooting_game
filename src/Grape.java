import java.awt.image.BufferedImage;
public class Grape extends Fruit{
	public static final int WIDTH = 80;
	public static final int HEIGHT = 82;
	public static final int SPEED = 26;
	private static BufferedImage img;

	static{
		img = readImage("grape.png");
	}

	public Grape(){
		super(Grape.HEIGHT,Grape.WIDTH,Grape.SPEED);
	}

	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}
}