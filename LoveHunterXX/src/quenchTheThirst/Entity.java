package quenchTheThirst;

import gui.components.Graphic;

public abstract class Entity extends Graphic {

	public Entity(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
	}

	public boolean canMove(String dir) {
		int x = this.getCenterX(), y = this.getCenterY();
		switch (dir) {
		case "north":
			y -= 5;
			break;
		case "east":
			x += 5;
			break;
		case "south":
			y += 5;
			break;
		case "west":
			x -= 5;
			break;
		default:
			return false;
		}

		for (Entity e : ShooterGame.shooterScreen.getEntities()) {
			if (e instanceof Projectile || e == this) {
				continue;
			}

			if (e.collideWith(x, y, this.getWidth(), this.getHeight())) {
				System.out.println("FINISHED");
				return false;
			}
		}

		System.out.println("FINISHED");
		return true;
	}

	public boolean collideWith(Entity e) {
		return collideWith(e.getCenterX(), e.getCenterY(), e.getWidth(), e.getHeight());
	}

	public boolean collideWith(int x, int y, int width, int height) {
		return Math.abs(this.getCenterX() - x) * 2 < this.getWidth() + width
				&& Math.abs(this.getCenterY() - y) * 2 < this.getHeight() + height;
	}

	public void move(String dir) {
		if (!canMove(dir))
			return;

		switch (dir) {
		case "north":
			this.setY(this.getY() - 5);
			break;
		case "east":
			this.setX(this.getX() + 5);
			break;
		case "south":
			this.setY(this.getY() + 5);
			break;
		case "west":
			this.setX(this.getX() - 5);
			break;
		}
	}

	public boolean distance(Entity e, int i) {
		if (Math.abs(Math.sqrt((Math.pow(this.getCenterX() - e.getCenterX(), 2))
				+ (Math.pow(this.getCenterY() - e.getCenterY(), 2)))) < i) {
			return true;
		}
		return false;
	}

	public int getCenterX() {
		return (int) (this.getX() + this.getWidth() / 2D);
	}

	public int getCenterY() {
		return (int) (this.getY() + this.getHeight() / 2D);
	}
}
