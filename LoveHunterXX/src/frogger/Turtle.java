package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.components.AnimatedComponent;

public class Turtle extends AnimatedComponent implements CollisionInterface {

	private boolean touchable;
	private String[] swimAnimation = { "resources/frogger/turtle0.png", "resources/frogger/turtle1.png" };
	private String[] diveAnimation = { "resources/frogger/down0.png", "resources/frogger/down1.png",
			"resources/frogger/down2.png", "resources/frogger/down3.png" };
	private int swimTime;
	private int swimSeq = 0;
	private int submergeFrameInterval;
	private int submergeCurrentFrameTime = 0;
	private int timeBeforeAscending;
	private int underWaterTime = 0;
	private long currentTime;
	private boolean isSubmerging;
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
	 * @param timeBeforeAscending
	 *            time that the turtles stay under water     
	 */

	public Turtle(int x, int y, int w, int h, int vx, int swimTime, int timeBeforeAscending, int submergeFrameInterval) {
		super(x, y, w, h);
		this.swimTime = swimTime;
		this.timeBeforeAscending = timeBeforeAscending;
		this.submergeFrameInterval = submergeFrameInterval;
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
		if (getCurrentFrame() > 2) {
			touchable = false;
		} else {
			touchable = true;
		}
	}

	public void drawImage(Graphics2D g) {
		if (superCreated) {
			currentTime = System.currentTimeMillis();
			if (currentTime - getDisplayTime() > swimTime) {
				if (getFrame() != null && getFrame().size() > 0 && getFrame().size() == getTimes().size()) {
					g = clear();
					ImageIcon icon = new ImageIcon(diveAnimation[getCurrentFrame()]);
					AffineTransform at = new AffineTransform();
					at.scale(1.3, 1);
					if (getVx() > 0) {
						at.rotate(Math.toRadians(180), icon.getIconWidth() / 2, icon.getIconHeight() / 2);
					} else {
						at.translate(-2, -8); // little bit of hard coding here cause the turtles won't center for some reason :/
					}
					g.drawImage(icon.getImage(), at, null);
					
					// determines what order the frames should play, plays backward if the turtles are ascend and plays forward if the turtles are submerging
					checkFrame();
					// changes the frame based on the logics above ^
					changeFrame();
					
					if (getCurrentFrame() == 0 && !isRepeat()) {
						setRunning(false);
						return;
					}
					if(!isSubmerging) underWaterTime += REFRESH_RATE;
					submergeCurrentFrameTime += REFRESH_RATE;
				}
			} else {
				g = clear();
				swimSeq = (swimSeq + 1) % 2;
				ImageIcon icon = new ImageIcon(swimAnimation[swimSeq]);
				AffineTransform at = new AffineTransform();
				at.scale(1.3, 1);
				if (getVx() > 0) {
					at.rotate(Math.toRadians(180), icon.getIconWidth() / 2, icon.getIconHeight() / 2);
				} else {
					at.translate(-2, -6);
				}
				g.drawImage(icon.getImage(), at, null);
			}
		}
	}

	private void changeFrame() {
		if (submergeFrameInterval - submergeCurrentFrameTime <= 0) {
			if (!isSubmerging && timeBeforeAscending - underWaterTime <= 0) {
				setCurrentFrame(getCurrentFrame() - 1);
				loop = true;
			} else if(isSubmerging){
				setCurrentFrame(getCurrentFrame() + 1);
			}
			submergeCurrentFrameTime = 0;
		}
	}

	private void checkFrame() {
		if (getCurrentFrame() == 0) {
			isSubmerging = true;
			if (loop) {
				setDisplayTime(currentTime);
				loop = false;
				underWaterTime = 0;
			}
		} else if (getCurrentFrame() > 2) {
			isSubmerging = false;
		}		
	}

	public void run() {
		setPosx(getX());
		setPosy(getY());
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
		while (isRunning()) {
			try {
				Thread.sleep(REFRESH_RATE);
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
