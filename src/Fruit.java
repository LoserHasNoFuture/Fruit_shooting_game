import java.util.Random;

public class Fruit extends FlyingObject{

	int speed;

	public Fruit(){
	}

	public Fruit(int height, int width, int speed){
		Random ran = new Random();
		super(height,width,ran.nextInt(420-width),-width);
		this.speed = speed;
	}

	public void show(){
		System.out.println("I am a Fruit");
	}




}