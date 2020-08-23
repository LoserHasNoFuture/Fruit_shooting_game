import java.awt.image.BufferedImage;

public class Cannon extends FlyingObject{

	private int life;
	private int fire;
	public static final int WIDTH = 70;
	
	private static BufferedImage[] imgs;


	static{
		imgs = new BufferedImage[5];
		imgs[0] = readImage("cannon.png");
		for(int i = 1; i < imgs.length; i++)
			imgs[i] = readImage("bom"+i+".png");
	}


	public Cannon(){
		super(140,70);
		// this.x = x;
		this.x = (Game.WIDTH - this.width)/2;
		this.y = Game.HEIGHT - this.height;
		this.life = 1;
	}

	public int getFire(){
		return this.fire;
	}


	public void addFire(){
		this.fire += 3;
	}

	public void minusFire(){
		this.fire--;
	}

	public void resetFire(){
		this.fire = 0;
	}

	public void addLife(){
		this.life++;
	}

	public int getLife(){
		return this.life;
	}

	public void minusLife(){
		this.life--;
	}

	int index = 0;
	public BufferedImage getImage(){
		if(isAlive()) return this.imgs[0];
		if(isDead()){
			index++;
			if(index < this.imgs.length) return this.imgs[index];
			else this.status = FlyingObject.REMOVED;
		}
		return null;
	}

	public void move(){
	}

	public void moveTo(int x, int y){
		this.x = x-this.width/2;
		this.y = y-this.height/2;
	}
}