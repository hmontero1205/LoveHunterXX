package platformer;

import gui.components.Visible;

public interface Collidable extends Visible {

	public boolean isCollided(int x1, int x2, int h);

	public void act();
}
