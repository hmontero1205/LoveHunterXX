package frogger;


public class Obstacle extends Platform{
	
	/**
	 * @param  x  initial x location of this component
	 * @param  y  initial y location of this component
	 * @param  w  width of this component
	 * @param  h  height of this component
	 * @param  vx  x velocity of this component
	 * @param  imgLoc  location of the image for this component
	 */

	public Obstacle(int x, int y, int w, int h, int vx, String imgLoc) {
		super(x, y, w, h, vx, imgLoc);
		setTouchable(false);
	}
	
	public void play() {
		if(!isRunning()){
			Thread go = new Thread(this);
			go.start();
		}
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
