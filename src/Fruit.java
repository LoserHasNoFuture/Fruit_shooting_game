import java.util.Random;

public abstract class Fruit extends FlyingObject{

	protected int speed;

	public Fruit(){
	}

	public Fruit(int height, int width, int speed){
		super(height,width);
		Random ran = new Random();
		this.x = ran.nextInt(Game.WIDTH-width);
		this.y = -height;
		this.speed = speed;
	}

	public void move(){
		this.y += this.speed;
	}


}