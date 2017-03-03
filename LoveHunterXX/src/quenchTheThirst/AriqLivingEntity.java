package quenchTheThirst;

public abstract class AriqLivingEntity extends KevinEntity {

	private int health;

	public AriqLivingEntity(int x, int y, double scale, String imageLocation, int speed) {
		super(x, y, scale, imageLocation, speed);

		health = 100;
	}

	public abstract void die();

	public int getHealth() {
		return health;
	}

	public void damage(int damage) {
		health -= damage;
		health = health < 0 ? 0 : health;

		if (health == 0) {
			die();
		}
	}

}
