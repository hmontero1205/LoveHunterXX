package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.MovingComponent;

public class Obstacle extends MovingComponent implements Collidable {

	private int x;
	private int y;
	private int w;
	private int h;
	private Image image;
	private String imgLoc;
	private ImageIcon icon;
	private boolean load;
	private Action action;

	public Obstacle(int x, int y, int w, int h, int vx, String imageLocation) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imgLoc = imageLocation;
		load = false;
		setX(x);
		setY(y);
		setVx(vx);
		loadImage();
		this.play();
	}

	private void loadImage() {
		try {
			image = new ImageIcon(imgLoc).getImage();
			load = true;
			update();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Graphics2D g) {
		if (load) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
			long currentTime = System.currentTimeMillis();
			int diff = (int) (currentTime - getMoveTime());
			if (diff >= REFRESH_RATE) {
				setMoveTime(currentTime);
				setPosx(getPosx() + getVx());
				super.setX((int) getPosx());
			}

		}
	}

	public void run() {
		setRunning(true);
		while(isRunning()){
			try {
				Thread.sleep(REFRESH_RATE);
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isCollided(int x1, int x2, int h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void act() {
		action.act();
	}

}
