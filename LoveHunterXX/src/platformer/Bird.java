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
	private boolean load;
	private Action action;
	private boolean excreted;
	
	public Bird(int x, int y, int w, int h, int vx, int vy, String imageLocation) {
		super(x, y, w, h, vx, vy, imageLocation);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
	public void createFeces(){
		Obstacle obs = new Obstacle(x, y, h, h, h, h, imgLoc);
	}
	public void run() {
		setRunning(true);
		while(isRunning()){
			try {
				Thread.sleep(REFRESH_RATE);
				checkBehaviors();
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void checkBehaviors(){
		System.out.println((int) Math.random() * 2);
	}
}
