import java.awt.image.BufferedImage;

public class Bomb extends FlyingObject{
	public static final int speed = 20;
	public static final int WIDTH = 44;

	private static BufferedImage[] imgs;


	static{
		imgs = new BufferedImage[5];
		imgs[0] = readImage("bomb.png");
		for(int i = 1; i < imgs.length; i++)
			imgs[i] = readImage("bom"+i+".png");
	}

	public Bomb(){
	}

	public Bomb(int x, int y){
		super(40,44,x,y);
	}

	int index = 0;
	public BufferedImage getImage(){
		if(isAlive()) return this.imgs[0];
		if(isDead()){
			index++;
			if(index < this.imgs.length) return this.imgs[index];
			else this.status = 2;
		}
		return null;
	}

	public void move(){
		this.y -= this.speed;
	}	

	public boolean isOutOfBoundary(){
		return (this.y < -this.width);
	}
}