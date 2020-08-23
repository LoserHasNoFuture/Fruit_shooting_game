import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Random;

public class Game extends JPanel{

	public static final int WIDTH = 420;
	public static final int HEIGHT = 700;
	public static final int FRUIT_FREQUENCY = 10;
	public static final int BOMB_FREQUENCY = 15;
	private static final int refresh_interval = 100;


	private Background background = new Background();
	private Cannon cannon = new Cannon(); // x,y
	private Fruit[] fruits = {};
	private Bomb[] bombs = {};

	public Game(){

	}

	public void start(){
		launchWindow();

		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
				// move
				all_objects_move();
				// add fruits
				spawn_more_fruits();
				// add bombs
				spawn_more_bombs();
				// paint again
				repaint();
			}
		};
		timer.schedule(task,refresh_interval,refresh_interval);
	}

	int bomb_index = 1;
	public void spawn_more_bombs(){
		if(bomb_index * (cannon.getDoubleFire() + 1) % Game.BOMB_FREQUENCY == 0){
			bombs = Arrays.copyOf(bombs, bombs.length+1);
			bombs[bombs.length - 1] = new Bomb(cannon.x + (Cannon.WIDTH-Bomb.WIDTH)/2,cannon.y);
		}
		bomb_index++;
	}

	int fruit_index = 1;
	public void spawn_more_fruits(){
		if(fruit_index % Game.FRUIT_FREQUENCY == 0){
			Fruit fruit = create_a_fruit();
			fruits = Arrays.copyOf(fruits, fruits.length+1);
			fruits[fruits.length - 1] = fruit; 
		}
		fruit_index++;
	}

	public Fruit create_a_fruit(){
		Fruit fruit = null;
		Random ran = new Random();
		int num = ran.nextInt(100);
		if(num < 15) fruit = new Cherry();
		else if(num < 35) fruit = new Grape();
		else if(num < 60) fruit = new Apple();
		else fruit = new Pineapple();
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
		background.drawObject(sheet);
		cannon.drawObject(sheet);
		for(int i = 0; i < fruits.length; i++) fruits[i].drawObject(sheet);
		for(int i = 0; i < bombs.length; i++) bombs[i].drawObject(sheet);
	}
}