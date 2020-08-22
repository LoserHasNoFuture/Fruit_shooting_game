public class Cannon extends FlyingObject{

	private int life;
	private int doubleFire;
	private final int y = -500;

	public Cannon(){

	}

	public Cannon(int x, int y){
		super(159,314);
		this.x = x;
		life = 3;
		// double fire is set as default
	}

	public void show(){
		System.out.println("height is: " + this.height + ", width is: " + this.width);
		System.out.println("x is: " + this.x + ", y is: " + this.y);
		System.out.println("I have life: " + this.life + ", nad double fire is: " + this.doubleFire);
	}
}