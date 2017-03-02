package quenchTheThirst;

public abstract class LivingEntity extends Entity {
	
	private int health;
	
	public LivingEntity(int x, int y, double scale, String imageLocation, int speed) {
		super(x, y, scale, imageLocation, speed);
		
		health = 100;
	}
	
	public abstract void die();

	public int getHealth() {
		return 100;
	}

	public void damage(int damage) {
		health -= damage;
		
		if (health <= 0) {
			die();
		}
	}
	
}
