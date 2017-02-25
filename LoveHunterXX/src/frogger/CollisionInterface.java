package frogger;

import gui.components.Visible;

public interface CollisionInterface extends Visible{
	boolean isTouchingPlayer(PlayerInterface p);
	
	boolean isApproachingPlayer(PlayerInterface p);

	int getX();

	void play();

	double getVx();
}
