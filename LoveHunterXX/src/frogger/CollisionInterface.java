package frogger;

import gui.components.Visible;

public interface CollisionInterface extends Visible {
	boolean isTouchingPlayer(PlayerInterface p);

	boolean isApproachingPlayer(PlayerInterface p);

	void setRunning(boolean b);

	double getVx();

	boolean isRunning();

	void play();
}
