package platformer;

import java.awt.Graphics2D;

import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.AnimatedComponent;
import gui.components.MovingComponent;

public class Obstacle extends AnimatedComponent implements Collidable, Action {

	private int x;
	private int y;
	private int w;
	private int h;
	private Image image;
	private String imgLoc;
	private ImageIcon icon;
	private boolean load;
	private Action action;
	private boolean collided;
	private int id;

	public Obstacle(int x, int y, int w, int h, int vx, int vy,  String imageLocation) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.imgLoc = imageLocation;
		load = false;
		setX(x);
		setY(y);
		setVx(vx);
		setVy(vy);
		loadImage();
		this.play();
	}

	private void loadImage() {
		try {
			image = new ImageIcon(imgLoc).getImage();
			load = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void update(Graphics2D g) {
		if (load) {
			if (isCollided() && !collided && !PlatformerGame.cs.player.invuln) {
				collided = true;
				act();
			}
			if (getX() < w*-1) {
				PlatformerGame.cs.obstacles.remove(this);
				PlatformerGame.cs.remove(this);
			} else {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null),
						null);
				setPosx(getPosx() + getVx());
				setPosy(getPosy() + getVy());
				super.setX((int) getPosx());
				super.setY((int) getPosy());
			}
		}
	}

	public void run() {
		setRunning(true);
		while (isRunning()) {
			try {
				Thread.sleep(REFRESH_RATE);
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isCollided() {
		// TODO Auto-generated method stud
		Player playTemp = PlatformerGame.cs.player;
		if (((playTemp.getX() + playTemp.getWidth()) > getPosx())
				&& (playTemp.getX() + playTemp.getWidth()) < (getPosx() + w)
				&& (playTemp.getY() + playTemp.getHeight()) > getPosy()) {
			return true;
		}
		return false;
	}

	public void act() {
		action.act();
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
