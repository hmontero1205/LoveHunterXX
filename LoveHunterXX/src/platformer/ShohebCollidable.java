package platformer;

import gui.components.Action;
import gui.components.Visible;

public interface ShohebCollidable extends Visible {

	public boolean isCollided();
	public void setAction(Action action);
	public void act();
	
}
