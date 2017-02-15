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
	private boolean collided;

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
			if (isCollided() && !collided) {
				collided = true;
				this.act();
			}
			if (getX() < -50) {
				PlatformerGame.cs.obstacles.remove(this);
				PlatformerGame.cs.remove(this);
			} else {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null),
						null);
				long currentTime = System.currentTimeMillis();
				int diff = (int) (currentTime - getMoveTime());
				if (diff >= REFRESH_RATE) {
					setMoveTime(currentTime);
					setPosx(getPosx() + getVx());
					super.setX((int) getPosx());
				}
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
		//playTemp.getX() + playTemp.getWidth() > x && playTemp.getX() > x
		//((playTemp.getX() + playTemp.getWidth()) > (x+w))
		if (((playTemp.getX() + playTemp.getWidth()) > getPosx()) && (playTemp.getX() < (getPosx() + w)) 
				&& ((playTemp.getY()+playTemp.getHeight()) > getPosy()) ) {
			return true;
		}
		return false;
	}

	@Override
	public void act() {
		action.act();
	}
	public void setAction(Action action){
		this.action = action;
	}

}
