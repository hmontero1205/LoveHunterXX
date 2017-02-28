package quenchTheThirst;

public class AlluringBottle extends Projectile {
	
	private static String[] frames = new String[10];
	static {
		for (int i = 0; i < frames.length; i++) {
			frames[i] = "resources/pool/frame_" + i + "_delay-0.01s.gif";
		}
	}

	public AlluringBottle(int x, int y, String dir) {
		super(x, y, 0.04, "resources/bottle.png", dir);

	}
	
	public void allure(){
		for (int i = 0; i < frames.length; i++) {
			loadImages(frames[i], .5);
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
		
		allure();
	}

}
