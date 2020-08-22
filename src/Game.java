import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel{
	
	public Game(){
		// instanize a window
		JFrame jf = new JFrame("Shooting Fruits");
		// put this game into window;
		jf.add(this);
		// set the size of window;
		jf.setSize(420,700);
		// when you click close bottom, the program is terminated
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口居中
		jf.setLocationRelativeTo(null);
		// 窗口显示
		jf.setVisible(true);
	}

	public void start(){
		
	}
}