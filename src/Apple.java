import java.awt.image.BufferedImage;
public class Apple extends Fruit{
	public static final int WIDTH = 60;
	public static final int HEIGHT = 60;
	public static final int SPEED = 20;
	private static BufferedImage img;

	static{
		img = readImage("apple.png");
	}


	public Apple(){
		super(Apple.HEIGHT,Apple.WIDTH,Apple.SPEED);
	}


	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}
	
}