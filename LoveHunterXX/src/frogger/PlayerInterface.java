/**
 * @author Hans
 *
 */
package frogger;

import java.util.ArrayList;
import gui.components.Visible;

public interface PlayerInterface extends Visible {

	void move(int k);

	boolean outOfBounds(int dir);

	void ridePlatform(CollisionInterface p);

	void setVx(double vx);

	boolean isOnPlatform();

	void die();

	public void setTerrain(Terrain t);

	void setRunning(boolean b);

	void pickUpItem(PowerUp pu);

	void activatePower();

	void mouseScrolled(int wheelRotation);

	void setSuperStrength(boolean b);

	void setSwimming(boolean b);

	boolean getSuperStrength();

	boolean getSwimming();

	ArrayList<PowerUp> getInventory();

	int getCurrentPowerUp();

	void removeItem(PowerUp powerUp);


}
