import java.util.Random;

public abstract class Fruit extends FlyingObject{

	protected int speed;
	protected int score;

	public Fruit(){
	}

	public Fruit(int height, int width, int speed, int score){
		super(height,width);
		Random ran = new Random();
		this.x = ran.nextInt(Game.WIDTH-width);
		this.y = -height;
		this.speed = speed;
		this.score = score;
	}

	public void move(){
		this.y += this.speed;
	}

	public boolean isOutOfBoundary(){
		return (this.y > Game.HEIGHT);
	}

}