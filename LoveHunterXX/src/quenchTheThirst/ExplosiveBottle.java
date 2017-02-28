package quenchTheThirst;

public class ExplosiveBottle extends Projectile {
	
	private static String[] frames = new String[8];
	static {
		for (int i = 0; i < frames.length; i++) {
			frames[i] = "resources/explode/frame_" + i + "_delay-0.01s.gif";
		}
	}

	public ExplosiveBottle(int x, int y, String dir) {
		super(x, y, 0.03, "resources/bottle.png", dir);
	}
	
	public void explode() {
		for (int i = 0; i < frames.length; i++) {
			loadImages(frames[i], 0.3);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ShooterGame.shooterScreen.kill(this);
	}

	@Override
	public void hit() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		explode();
	}

}
