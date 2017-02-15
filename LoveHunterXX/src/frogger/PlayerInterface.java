package frogger;

import java.awt.event.KeyEvent;

import gui.components.Visible;

public interface PlayerInterface extends Visible {

	void move(int k);
	
	boolean outOfBounds(int dir);
	
	void ridePlatform(Platform p);
	
	void die();

}
