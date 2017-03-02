package quenchTheThirst;

public class AlluringBottle extends Projectile {
	
	private static String[] frames = new String[10];
	static {
		for (int i = 0; i < frames.length; i++) {
			frames[i] = "resources/qtt/pool/frame_" + i + "_delay-0.01s.gif";
		}
	}
	private boolean isActive;

	public AlluringBottle(int x, int y, String dir) {
		super(x, y, 0.04, "resources/qtt/bottle.png", dir);
	}

	public void allure() {
		isActive = true;
		
		int centerX = this.getCenterX();
		int centerY = this.getCenterY();
		
		loadImages(frames[0], .5);
		
		this.setX(centerX - this.getWidth() / 2);
		this.setY(centerY - this.getHeight() / 2);
		
		for (int i = 0; i < 100; i++) {
			loadImages(frames[i % frames.length], .5);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ShooterGame.shooterScreen.kill(this);
	}
	
	public boolean isActive() {
		return isActive;
	}

	@Override
	public void land() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		allure();
	}

}
