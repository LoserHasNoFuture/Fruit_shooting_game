import java.awt.image.BufferedImage;

public class Cannon extends FlyingObject{

	private int life;
	private int doubleFire;
	public static final int WIDTH = 70;
	public static final int HEIGHT = 140;
	public static final int INITIAL_LIFE = 3;


	private static BufferedImage img;

	static {
		img = readImage("cannon.png");
	}


	public Cannon(){
		super(Cannon.HEIGHT,Cannon.WIDTH);
		// this.x = x;
		this.x = (Game.WIDTH - Cannon.WIDTH)/2;
		this.y = Game.HEIGHT - Cannon.HEIGHT;
		this.life = Cannon.INITIAL_LIFE;
		// double fire is set as default
	}

	public int getDoubleFire(){
		return this.doubleFire;
	}

	public void setDoubleFire(int doubleFire){
		this.doubleFire = doubleFire;
	}


	public BufferedImage getImage(){
		if(isAlive()) return this.img;
		else return null;
	}

	public void move(){
	}
}