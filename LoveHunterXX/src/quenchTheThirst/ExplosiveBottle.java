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
		int centerX = this.getCenterX();
		int centerY = this.getCenterY();
		
		loadImages(frames[0], .3);
		
		this.setX(centerX - this.getWidth() / 2);
		this.setY(centerY - this.getHeight() / 2);
		
		for (int i = 0; i < frames.length; i++) {
			loadImages(frames[i], 0.3);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Entity e : ShooterGame.shooterScreen.getEntities()) {
			if (e instanceof LivingEntity && distance(e, 30)) {
				LivingEntity live = (LivingEntity) e; // why do we need this
														// part?
				live.damage(100);
			}
		}

		ShooterGame.shooterScreen.kill(this);
	}

	@Override
	public void land() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		explode();
	}

}
