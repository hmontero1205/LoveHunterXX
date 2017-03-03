/**
 * @author Hans
 *
 */
package frogger;

import gui.components.Visible;

public interface HansCollisionInterface extends Visible {
	boolean isTouchingPlayer(HansPlayerInterface p);

	void setRunning(boolean b);

	double getVx();

	boolean isRunning();
	
	Thread getThread();

	void play();
}
