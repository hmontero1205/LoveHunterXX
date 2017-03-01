package quenchTheThirst;

public abstract class LivingEntity extends Entity {
	
	private int health;
	
	public LivingEntity(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
		
		health = 100;
	}

	public int getHealth() {
		return 100;
	}

	public void damage(int damage) {
		health -= damage;
	}
	
}
