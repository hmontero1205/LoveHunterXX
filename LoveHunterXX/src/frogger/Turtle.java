package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.components.AnimatedComponent;

public class Turtle extends AnimatedComponent implements CollisionInterface {

	private static int goingDownTime = 400;
	private boolean touchable;
	private String[] swimAnimation = {"resources/frogger/turtle0.png", "resources/frogger/turtle1.png"};
	private String[] diveAnimation = {"resources/frogger/down0.png", "resources/frogger/down1.png", "resources/frogger/down2.png", "resources/frogger/down3.png"};
	private int swimTime;
	private int swimSeq = 0;
	private boolean diving;
	private boolean descending;
	private boolean loop;
	private boolean superCreated;
	
	/**
	 * @param x
	 *            initial x location of the turtle
	 * @param y
	 *            initial y location of the turtle
	 * @param w
	 *            width of turtle
	 * @param h
	 *            height of of turtle
	 * @param vx
	 *            x velocity of turtle
	 * @param swimTime
	 *            time before next frame
	 */

	public Turtle(int x, int y, int w, int h, int vx, int swimTime) {
		super(x, y, w, h);
		this.swimTime = swimTime;
		setX(x);
		setY(y);
		setVx(vx);
		setDisplayTime(System.currentTimeMillis());
		for (int i = 0; i < 4; i++) {
			BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			addFrame(bi, null);
		}
		superCreated = true;
	}

	public void update(Graphics2D g) {
		long currentTime = System.currentTimeMillis();
		int diff = (int) (currentTime - getMoveTime());
		if (diff >= REFRESH_RATE) {
			setMoveTime(currentTime);
			setPosx(getPosx() + getVx() * (double) diff / REFRESH_RATE);
			setPosy(getPosy() + getVy() * (double) diff / REFRESH_RATE);
			super.setX((int) getPosx());
			super.setY((int) getPosy());
		}
		drawImage(g);
		if (getCurrentFrame() >= 2) {
			touchable = false;
		} else {
			touchable = true;
		}
	}

	public void drawImage(Graphics2D g) {
		if(superCreated) {
			long currentTime = System.currentTimeMillis();
			if(currentTime - getDisplayTime() > swimTime) {
				diving = true;
				if (getFrame() != null && getFrame().size() > 0 && getFrame().size() == getTimes().size()) {
					g = clear();
					ImageIcon icon = new ImageIcon(diveAnimation[getCurrentFrame()]);
					AffineTransform at = new AffineTransform();
					at.scale(1.3, 1);
					if (getVx() > 0) at.rotate(Math.toRadians(180), icon.getIconWidth()/2, icon.getIconHeight()/2);
					g.drawImage(icon.getImage(), at, null);
					if(getCurrentFrame() == 0) {
						descending = true;
						if(loop) {
							setDisplayTime(currentTime);
							loop = false;
						}
					} else if (getCurrentFrame() > 1){
						descending = false;
					}
					if(!descending) {
						setCurrentFrame(getCurrentFrame() - 1);
						loop = true;
					} else {
						setCurrentFrame(getCurrentFrame() + 1);
					}
					if (getCurrentFrame() == 0 && !isRepeat()) {
						setRunning(false);
						return;
					}
				}
			} else {
				diving = false;
				g = clear();
				swimSeq = (swimSeq + 1) % 2;
				ImageIcon icon = new ImageIcon(swimAnimation[swimSeq]);
				AffineTransform at = new AffineTransform();
				at.scale(1.3, 1);
				if (getVx() > 0) at.rotate(Math.toRadians(180), icon.getIconWidth()/2, icon.getIconHeight()/2);
				g.drawImage(icon.getImage(), at, null);
			}
		}
	}

	public void run() {
		setPosx(getX());
		setPosy(getY());
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
		while (isRunning()) {
			try {
				if(diving) {
					Thread.sleep(goingDownTime);
				} else {
					Thread.sleep(REFRESH_RATE);
				}
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void play() {
		if (!isRunning()) {
			Thread go = new Thread(this);
			go.start();
		}
	}

	@Override
	public boolean isTouchingPlayer(PlayerInterface p) {
		boolean touching = false;
		if (p.getX() <= this.getX() + this.getWidth() && p.getX() >= this.getX()
				|| p.getX() + p.getWidth() <= this.getX() + this.getWidth() && p.getX() + p.getWidth() >= this.getX()) {
			if (p.getY() <= this.getY() + this.getHeight() && p.getY() >= this.getY()
					|| p.getY() + p.getHeight() <= this.getY() + this.getHeight()
							&& p.getY() + p.getHeight() >= this.getY()) {
				touching = true;
			}
		}
		return touching;
	}

	@Override
	public boolean isApproachingPlayer(PlayerInterface p) {
		boolean approaching = false;
		if (getVx() > 0 && p.getX() - this.getX() + this.getWidth() < 200
				&& p.getX() - this.getX() + this.getWidth() > 0) {
			approaching = true;
		} else if (this.getX() - p.getX() + p.getWidth() < 200 && this.getX() - p.getX() + p.getWidth() > 0) {
			approaching = true;
		}
		return approaching;
	}

	public boolean isTouchable() {
		return touchable;
	}

	public void setTouchable(boolean touchable) {
		this.touchable = touchable;
	}
}
