import java.awt.image.BufferedImage;
public class Pineapple extends Fruit{
	public static final int WIDTH = 70;
	public static final int HEIGHT = 100;
	public static final int SPEED = 18;
	private static BufferedImage img;

	static{
		img = readImage("pineapple.png");
	}

	public Pineapple(){
		super(Pineapple.HEIGHT,Pineapple.WIDTH,Pineapple.SPEED);
	}

	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}

}
