package frogger;

import gui.components.Visible;

public interface JiaMingProgressMarkerInterface extends Visible {
	
	void nextLevel();
	boolean isTouchingPlayer(HansPlayerInterface p);
	String getSrc();
	
}
