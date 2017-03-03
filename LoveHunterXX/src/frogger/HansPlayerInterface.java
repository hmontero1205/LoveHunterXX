/**
 * @author Hans
 *
 */
package frogger;

import java.util.ArrayList;
import gui.components.Visible;

public interface HansPlayerInterface extends Visible {

	void move(int k);

	boolean outOfBounds(int dir);

	void ridePlatform(HansCollisionInterface p);

	void setVx(double vx);
	
	Thread getThread();

	boolean isOnPlatform();

	void die();

	public void setTerrain(HansTerrain t);

	void setRunning(boolean b);

	void pickUpItem(HansPowerUp pu);

	void activatePower();

	void mouseScrolled(int wheelRotation);

	void setSuperStrength(boolean b);

	void setSwimming(boolean b);

	boolean getSuperStrength();

	boolean getSwimming();

	ArrayList<HansPowerUp> getInventory();
	
	void setInventory(ArrayList<HansPowerUp> pu);
	
	int getCurrentPowerUp();

}
