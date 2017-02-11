package frogger;


public class Obstacle extends Platform {

	public Obstacle(int x, int y, int w, int h, int vx, String imgLoc) {
		super(x, y, w, h, vx, imgLoc);
		setTouchable(false);
	}
	
	public void play() {
		createImage();
		if(!isRunning()){
			Thread go = new Thread(this);
			go.start();
		}
	}
}
