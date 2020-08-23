import java.awt.image.BufferedImage;
import java.util.Random;

public class Cherry extends Fruit implements Bonus,Point{

	private int xspeed;
	private static BufferedImage img;
	

	static{
		img = readImage("cherry.png");
	}

	public Cherry(){
		super(31,30,30,50);
		this.xspeed = 30;
	}


	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}
	
	public void move(){
		this.y += this.speed;
		this.x += this.xspeed;
		if(x <= 0 || x >= Game.WIDTH - this.width) this.xspeed = -this.xspeed;
	}

	public int getBonus(){
		Random ran = new Random();
		return ran.nextInt(2);
	}

	public int getPoint(){
		return this.score;
	}
}