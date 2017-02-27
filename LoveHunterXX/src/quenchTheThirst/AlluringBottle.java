package quenchTheThirst;

public class AlluringBottle extends Projectile {
	
	private static String[] frames = new String[7];
	static {
		for (int i = 0; i < frames.length; i++) {
			frames[i] = "resources/pool/pool" + i + ".png";
		}
	}
	

	public AlluringBottle(int x, int y, String dir) {
		super(x, y, 0.05, "resources/bottle.png", dir);

	}

	@Override
	public void hit() {
		for (int i = 0; i < 100; i++) {
			loadImages(frames[i % frames.length], 0.5);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		ShooterGame.shooterScreen.kill(this);
	}

}
