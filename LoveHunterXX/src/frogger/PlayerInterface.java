package frogger;


import java.util.ArrayList;

import gui.components.MovingComponent;
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

	void pickUpItem(PowerUpInterface pu);
	
	int getCurrentPowerUp();

	void setCurrentPowerUp(int currentPowerUp);
	
	ArrayList<PowerUpInterface> getInventory();

	void setInventory(ArrayList<PowerUpInterface> inventory);
}
