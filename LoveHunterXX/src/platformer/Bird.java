package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Action;

public class Bird extends Obstacle {
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
	
	public Bird(int x, int y, int w, int h, int vx, int vy, String imageLocation) {
		super(x, y, w, h, vx, vy, imageLocation);
		// TODO Auto-generated constructor stub
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
}
