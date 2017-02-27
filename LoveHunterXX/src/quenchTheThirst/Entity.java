package quenchTheThirst;

import gui.components.Graphic;

public abstract class Entity extends Graphic {

	public Entity(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
	}

	private boolean canMove() {
		return true;
	}

	public void move() {

	}
	
	
}
