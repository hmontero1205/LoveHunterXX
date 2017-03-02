/**
 * @author Hans
 *
 */
package frogger;

import gui.components.Visible;

public interface CollisionInterface extends Visible {
	boolean isTouchingPlayer(PlayerInterface p);

	void setRunning(boolean b);

	double getVx();

	boolean isRunning();
	
	Thread getThread();

	void play();
}
