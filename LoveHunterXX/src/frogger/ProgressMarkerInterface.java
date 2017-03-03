package frogger;

import gui.components.Visible;

public interface ProgressMarkerInterface extends Visible {
	
	void nextLevel();
	boolean isTouchingPlayer(PlayerInterface p);
	
}
