import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Background extends FlyingObject{
	
	private int speed;
	private int y1; // second pic
	public static final int SPEED = 2;


	private static BufferedImage img;

	static{
		img = readImage("background.png");
	}

	
	public Background(){
		super(Game.HEIGHT,Game.WIDTH,0,0);
		this.speed = Background.SPEED;
		y1 = -Game.HEIGHT;
	}

	public BufferedImage getImage(){
		return this.img;
	}

	public void drawObject(Graphics sheet){
		sheet.drawImage(getImage(),this.x,this.y,null);
		sheet.drawImage(getImage(),this.x,this.y1,null);
	}

	public void move(){
		this.y += this.speed;
		this.y1 += this.speed;
		if(this.y >= Game.HEIGHT) this.y = -Game.HEIGHT;
		if(this.y1 >= Game.HEIGHT) this.y1 = -Game.HEIGHT;
	}

}