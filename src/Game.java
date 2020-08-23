import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Random;

public class Game extends JPanel{
	private static BufferedImage startImg;
	private static BufferedImage pauseImg;
	private static BufferedImage overImg;
	static{
		startImg=FlyingObject
				.readImage("start.png");
		pauseImg=FlyingObject
				.readImage("pause.png");
		overImg=FlyingObject
				.readImage("gameover.png");
	}

	public static final int WIDTH = 420;
	public static final int HEIGHT = 700;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	public static final int FRUIT_FREQUENCY = 3;
	public static final int BOMB_INITIAL_FREQUENCY = 4;

	private int bomb_frequency = BOMB_INITIAL_FREQUENCY;
	private static final int refresh_interval = 100;
	public static int number_of_fruits = 2;
	private int status = 0;


	private Background background = new Background();
	private Cannon cannon = new Cannon(); // x,y
	private Fruit[] fruits = {};
	private Bomb[] bombs = {};
	private int score = 0;


	public Game(){

	}

	public void start(){		
		launchWindow();

		MouseAdapter ma=new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				if(status == RUNNING) cannon.moveTo(x, y);
			}

			public void mouseClicked(MouseEvent e) {
				switch(status) {
				case START:
					status=RUNNING;
					break;
				case GAME_OVER:
					status=START;
					score=0;
					cannon=new Cannon();
					background=new Background();
					fruits=new Fruit[0];
					bombs=new Bomb[0];
					break;
				}
			}

			public void mouseExited(MouseEvent e) {
				if(status==RUNNING) {
					status=PAUSE;
				}
			}
			public void mouseEntered(MouseEvent e) {
				if(status==PAUSE) {
					status=RUNNING;
				}
			}
		};
		this.addMouseListener(ma);
		this.addMouseMotionListener(ma);

		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
				if(status == RUNNING){
					// move
					all_objects_move();
					// add fruits
					spawn_more_fruits();
					// add bombs
					spawn_more_bombs();
					// hanlde collisions
					handle_collisions();
					// check if cannon dies
					if(cannon.isRemoved()) status = GAME_OVER;
					// delete objs
					remove_objects();
				}
				// paint again
				repaint();
			}
		};
		timer.schedule(task,refresh_interval,refresh_interval);

		TimerTask speed_up = new TimerTask(){
			public void run(){
				number_of_fruits = (int) (number_of_fruits * 1.5);
			}
		};

		timer.schedule(speed_up,30000,30000);
	}

	public void handle_collisions(){
		// bomb and fruits collision
		for(int i = 0; i < fruits.length;i++){
			for(int j = 0; j < bombs.length;j++){
				if(fruits[i].isHit(bombs[j])){
					fruits[i].goDie();
					bombs[j].goDie();
					if(fruits[i] instanceof Point){
						Point point = (Point)fruits[i];
						this.score += point.getPoint();
					}
					if(fruits[i] instanceof Bonus){
						Bonus bonus = (Bonus)fruits[i];
						switch(bonus.getBonus()){
							case Bonus.FIRE:
								cannon.addFire();
								bomb_frequency = bomb_frequency/2;
								if(bomb_frequency < 2) bomb_frequency = 2;
								break;
							case Bonus.LIFE:
								cannon.addLife();
								break;
						}
					}
				}
			}
		}

		// cannon and fruits collision
		for(int i = 0; i < fruits.length; i++){
			if(fruits[i].isAlive() && cannon.isHit(fruits[i])){
				fruits[i].goDie();
				cannon.minusLife();
				cannon.resetFire();
				if(cannon.getLife() <= 0) cannon.goDie();
			} 
		}
	}

	public void remove_objects(){
		int index = 0;
		Fruit[] new_fruits = new Fruit[fruits.length];
		for(int i = 0; i < fruits.length; i++){
			if(!fruits[i].isOutOfBoundary() && !fruits[i].isDead()){
				new_fruits[index] = fruits[i]; 
				index++;
			}
		}
		fruits = Arrays.copyOf(new_fruits, index);

		index = 0;
		Bomb[] new_bombs = new Bomb[bombs.length];
		for(int i = 0; i < bombs.length; i++){
			if(!bombs[i].isOutOfBoundary() && !bombs[i].isRemoved()){
				new_bombs[index] = bombs[i]; 
				index++;
			}
		}
		bombs = Arrays.copyOf(new_bombs, index);

	}

	int bomb_index = 1;
	public void spawn_more_bombs(){
		if(cannon.getFire() == 0) bomb_frequency = BOMB_INITIAL_FREQUENCY;
		else cannon.minusFire();
		if(bomb_index % bomb_frequency == 0){
			bombs = Arrays.copyOf(bombs, bombs.length+1);
			bombs[bombs.length - 1] = new Bomb(cannon.x + (Cannon.WIDTH-Bomb.WIDTH)/2,cannon.y);
		}
		bomb_index++;
	}

	int fruit_index = 1;
	public void spawn_more_fruits(){
		if(fruit_index % Game.FRUIT_FREQUENCY == 0){
			Fruit[] new_fruit = create_fruits();
			fruits = Arrays.copyOf(fruits, fruits.length+number_of_fruits);
			System.arraycopy(new_fruit,0,fruits,fruits.length-number_of_fruits,number_of_fruits);
		}
		fruit_index++;
	}

	public Fruit[] create_fruits(){
		Fruit[] fruit = new Fruit[number_of_fruits];
		Random ran = new Random();
		for (int i = 0; i < number_of_fruits; i++){
			int num = ran.nextInt(100);
			if(num < 15) fruit[i] = new Cherry();
			else if(num < 35) fruit[i] = new Grape();
			else if(num < 60) fruit[i] = new Apple();
			else fruit[i] = new Pineapple();
		}
		return fruit;
	}

	public void all_objects_move(){
		background.move();
		cannon.move();
		for(int i = 0; i < fruits.length; i++) fruits[i].move();
		for(int i = 0; i < bombs.length; i++) bombs[i].move();
	}

	public void launchWindow(){
		// instanize a window
		JFrame jf = new JFrame("Shooting Fruits");
		// put this game into window;
		jf.add(this);
		// set the size of window;
		jf.setSize(Game.WIDTH,Game.HEIGHT);
		// when you click close bottom, the program is terminated
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口居中
		jf.setLocationRelativeTo(null);
		// 窗口显示
		jf.setVisible(true);
	}

	public void paint(Graphics sheet){
		if(status == RUNNING){
			background.drawObject(sheet);
			cannon.drawObject(sheet);
			for(int i = 0; i < fruits.length; i++) fruits[i].drawObject(sheet);
			for(int i = 0; i < bombs.length; i++) bombs[i].drawObject(sheet);			
		}

		sheet.drawString("SCORE:"+score, 10, 20);
		sheet.drawString("LIFE:"+cannon.getLife(), 10, 45);
		switch(status) {
		case START:
			sheet.drawImage(startImg,0,0,null);
			break;
		case PAUSE:
			sheet.drawImage(pauseImg, 0, 0, null);
			break;
		case GAME_OVER:
			sheet.drawImage(overImg, 0, 0, null);
			break;
		}
	}
}