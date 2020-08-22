import java.util.Random;

public class Fruit extends FlyingObject{

	protected int speed;

	public Fruit(){
	}

	public Fruit(int height, int width, int speed){
		super(height,width);
		Random ran = new Random();
		this.x = ran.nextInt(420-width);
		this.y = -width;
		this.speed = speed;
	}

	public void show(){
		System.out.println("I am a Fruit");
	}




}