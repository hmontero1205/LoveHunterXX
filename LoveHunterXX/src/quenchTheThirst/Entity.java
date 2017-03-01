package quenchTheThirst;

import gui.components.Graphic;

public abstract class Entity extends Graphic {

	public Entity(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
	}

	private boolean canMove(int dir) {
		
		return true;
	}
	

	public void move(int dir) {

	}
	
	public int getCenterX() {
		return (int) (this.getX() + this.getWidth() / 2D);
	}
	
	public int getCenterY() {
		return (int) (this.getY() + this.getHeight() / 2D);
	}

	public int getDistanceFrom(Entity e) {
		return (int) Math.sqrt(Math.pow(this.getCenterX() - e.getCenterX(), 2) + Math.pow(this.getCenterY() - e.getCenterY(), 2));
	}
}
