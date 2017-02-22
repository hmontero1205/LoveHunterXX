package frogger;

import gui.components.AnimatedComponent;

public class AnimatedPlatform extends AnimatedComponent implements CollisionInterface {

	public AnimatedPlatform(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTouchingPlayer(Player p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isApproachingPlayer(Player p) {
		// TODO Auto-generated method stub
		return false;
	}

}
