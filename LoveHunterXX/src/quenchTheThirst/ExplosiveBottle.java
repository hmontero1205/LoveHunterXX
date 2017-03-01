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
		
		for(Entity e: ShooterGame.shooterScreen.entities){
			if(e instanceof LivingEntity && distance(e,10)){
				LivingEntity live = (LivingEntity)e; //why do we need this part?
				live.damage(100);
			}
		}
		
		ShooterGame.shooterScreen.kill(this);
	}

	private boolean distance(Entity e, int i) {
		//x2 = this.getX() x1 = e.getX()
		if(Math.sqrt((Math.pow(this.getX()-e.getX(), 2))+(Math.pow(this.getY()-e.getY(), 2)))<i){
			return true;
		}
		return false;
		
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
