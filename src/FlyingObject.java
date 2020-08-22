import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FlyingObject{

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
}