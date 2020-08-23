import java.awt.image.BufferedImage;

public class Cherry extends Fruit{
	public static final int WIDTH = 30;
	public static final int HEIGHT = 31;
	public static final int SPEED = 30;
	public static final int XSPEED = 30;

	private int xspeed;
	private static BufferedImage img;
	

	static{
		img = readImage("cherry.png");
	}

	public Cherry(){
		super(Cherry.HEIGHT,Cherry.WIDTH,Cherry.SPEED);
		this.xspeed = Cherry.XSPEED;
	}


	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}
	
	public void move(){
		this.y += this.speed;
		this.x += this.xspeed;
		if(x <= 0 || x >= Game.WIDTH - Cherry.WIDTH) this.xspeed = -this.xspeed;
	}
}