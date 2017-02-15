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
		this.x = x;
		this.y = y;
		velocity = 10;
		acceleration = -1;
	}
	
	@Override
	public void run() {
		while (velocity > 0) {
			if(dir == "east"){
				x += velocity;
				velocity += acceleration;
			}
			if(dir == "south"){
				y += velocity;
				velocity += acceleration;
			}
			if(dir == "west"){
				x -= velocity;
				velocity += acceleration;
			}
			if(dir == "north"){
				y -= velocity;
				velocity += acceleration;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void start() {
		/**
		 * thread updates projectile's position based on projectile's velocity and acceleration and direction
		 * every interval add velocity to x and subtract acceleration from velocity(east)
		 */
		Thread start = new Thread(this);
		start.start();
	}
}
