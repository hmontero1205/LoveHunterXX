package quenchTheThirst;

public abstract class Projectile extends Entity implements Runnable {

	private double velocity;
	private double acceleration;
	private String dir;
	private int x;
	private int y;

	public Projectile(int x, int y, double scale, String imageLocation, String dir) {
		super(x, y, scale, imageLocation);
		this.dir = dir;
		velocity = 10;
		acceleration = -0.5;
	}
	
	// called when projectile hit destination
	public abstract void hit();

	@Override
	public void run() {
		while (velocity > 0) {
			if (dir.equals("east")) {
				this.setX((int) (this.getX() + velocity));
				velocity += acceleration;
			}
			if (dir.equals("south")) {
				this.setY((int) (this.getY() + velocity));
				velocity += acceleration;
			}
			if (dir.equals("west")) {
				this.setX((int) (this.getX() - velocity));
				velocity += acceleration;
			}
			if (dir.equals("north")) {
				this.setY((int) (this.getY() - velocity));
				velocity += acceleration;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		hit();
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public void start() {
		/**
		 * thread updates projectile's position based on projectile's velocity
		 * and acceleration and direction every interval add velocity to x and
		 * subtract acceleration from velocity(east)
		 */
		Thread start = new Thread(this);
		start.start();
	}
}
