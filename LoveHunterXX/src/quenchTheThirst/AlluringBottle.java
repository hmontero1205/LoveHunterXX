package quenchTheThirst;

public class AlluringBottle extends Projectile {

	public AlluringBottle(int x, int y, String dir) {
		super(x, y, 0.05, "resources/bottle.png", dir);
	}

	@Override
	public void hit() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		ShooterGame.shooterScreen.kill(this);
	}

}
