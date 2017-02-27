package quenchTheThirst;

public class ExplosiveBottle extends Projectile {

	public ExplosiveBottle(int x, int y, String dir) {
		super(x, y, 0.03, "resources/bottle.png", dir);
	}
	
	public void explode() {
		System.out.println("BOOM");
	}

	@Override
	public void hit() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		explode();
	}

}
