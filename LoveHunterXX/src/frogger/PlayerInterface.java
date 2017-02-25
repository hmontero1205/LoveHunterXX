package frogger;


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

}
