package frogger;

import gui.components.AnimatedComponent;

public class AnimatedPlatform extends AnimatedComponent implements CollisionInterface {

	public AnimatedPlatform(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTouchingPlayer(PlayerInterface p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isApproachingPlayer(PlayerInterface p) {
		// TODO Auto-generated method stub
		return false;
	}


}
