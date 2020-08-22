public class Game{
	
	public void start(){
		Background backgournd = new Background();
		Cannon cannon = new Cannon();
		Bomb[] bombs = new Bomb[10];
		bombs[0] = new Bomb();

		Fruit[] fruits = new Fruit[4];
		fruits[0] = new Apple();
		fruits[1] = new Pineapple();
		fruits[2] = new Cherry();
		fruits[3] = new Grape();

		for(int i = 0; i < fruits.length; i++)
			fruits[i].show();
	}
}