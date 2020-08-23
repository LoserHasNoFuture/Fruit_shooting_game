import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;


public abstract class FlyingObject{
	public static final int ALIVE = 0;
	public static final int DEAD = 1;
	public static final int REMOVED = 2;


	protected int status = 0;
	protected int height;
	protected int width;
	protected int x; // x axis
	protected int y; // y axis

	public FlyingObject(){

	}

	public FlyingObject(int height, int width){
		this.height = height;
		this.width = width;
	}

	public FlyingObject(int height, int width, int x, int y){
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y; 
	}


	// read figures into java program
	public static BufferedImage readImage(String imgFile){
		try{
			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(imgFile));
			return img;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	// get images from array
	public abstract BufferedImage getImage();

	// draw this object on graphics
	public void drawObject(Graphics sheet){
		sheet.drawImage(getImage(),this.x,this.y,null);
	}

	public abstract void move();


	public boolean isAlive(){
		return this.status == FlyingObject.ALIVE;
	}

	public boolean isDead(){
		return this.status == FlyingObject.DEAD;
	}

	public boolean isRemoved(){
		return this.status == FlyingObject.REMOVED;
	}

	public void goDie(){
		this.status = FlyingObject.DEAD;
	}

	public boolean isHit(FlyingObject f){
		int x1=f.x-this.width;
		int x2=f.x+f.width;
		int y1=f.y-this.height;
		int y2=f.y+f.height;
		return this.x>x1 && this.x<x2
				&&
		       this.y>y1 && this.y<y2;
	}

}