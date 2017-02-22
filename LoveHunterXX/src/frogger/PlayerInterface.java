package frogger;

import java.awt.event.KeyEvent;

import gui.components.Visible;

public interface PlayerInterface extends Visible {

	void move(int k);
	
	boolean outOfBounds(int dir);
	
	void ridePlatform(Log p);
	
	void die();

	void setVx(double vx);

}
