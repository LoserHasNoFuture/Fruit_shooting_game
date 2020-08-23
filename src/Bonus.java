public interface Bonus{
	int FIRE = 0;
	int LIFE = 1;
	// when return 1, reward an extra life;
	// when return 0, reward fire;
	int getBonus();
}