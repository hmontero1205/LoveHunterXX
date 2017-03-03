package quenchTheThirst;

import java.util.Iterator;

import main.LoveHunterXX;

public class BillyExplosiveBottle extends BillyProjectile {
	
	private static String[] frames = new String[8];
	static {
		for (int i = 0; i < frames.length; i++) {
			frames[i] = "resources/qtt/explode/frame_" + i + "_delay-0.01s.gif";
		}
	}
	
	private static final int DAMAGE = 50;
	private static final int RADIUS = 30;

	public BillyExplosiveBottle(int x, int y, String dir) {
		super(x, y, 0.03, "resources/qtt/bottle.png", dir);
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

		Iterator<KevinEntity> entIterator = LoveHunterXX.qtts.getEntities().iterator();
		while (entIterator.hasNext()) {
			KevinEntity e = entIterator.next();
			if (e instanceof AriqLivingEntity && distance(e, RADIUS)) {
				AriqLivingEntity live = (AriqLivingEntity) e;
				live.damage(DAMAGE);
			}
		}

		LoveHunterXX.qtts.kill(this);
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
