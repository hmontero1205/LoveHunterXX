package quenchTheThirst;

public abstract class LivingEntity extends Entity {
	
	public LivingEntity(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
	}

	public int getHealth() {
		return 0;
	}

	public void damage(int damage) {
		
	}
	
}
