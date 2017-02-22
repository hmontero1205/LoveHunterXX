package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import gui.components.MovingComponent;

public class Car extends MovingComponent implements CollisionInterface {
	private boolean touchable;
	private String imgLoc;

	/**
	 * @param x
	 *            initial x location of this component
	 * @param y
	 *            initial y location of this component
	 * @param w
	 *            width of this component
	 * @param h
	 *            height of this component
	 * @param vx
	 *            x velocity of this component
	 * @param imgLoc
	 *            location of the image for this component
	 */

	public Car(int x, int y, int w, int h, int vx, String imgLoc) {
		super(x, y, w, h);
		touchable = true;
		this.imgLoc = imgLoc;
		setX(x);
		setY(y);
		setVx(vx);
	}

	public String getImgLoc() {
		return imgLoc;
	}

	public void setImgLoc(String imgLoc) {
		this.imgLoc = imgLoc;
	}

	public boolean getTouchable() {
		return touchable;
	}

	public void setTouchable(boolean b) {
		touchable = b;
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

		if (getImgLoc() != null) {
			ImageIcon icon = new ImageIcon(getImgLoc());
			AffineTransform at = new AffineTransform();
			double xScale = getWidth() / icon.getIconWidth();
			double yScale = getHeight() / icon.getIconHeight();
			at.scale(xScale, yScale);
			if (getVx() > 0) {
				// rotate cars if they are starting from the right side of the
				// screen
				at.translate(icon.getIconWidth() / 2, icon.getIconHeight() / 2);
				at.rotate(Math.PI);
				at.translate(-icon.getIconWidth() / 2, -icon.getIconHeight() / 2);
			}
			g.drawImage(icon.getImage(), at, null);
		}
	}

	public void checkBehaviors() {
		if (getVx() > 0) {
			if (getX() > 800) {
				setX(0 - getWidth());
			}
		} else if (getVx() < 0) {
			if (getX() + getWidth() < 0) {
				setX(800);
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
		if(p.getX() <= this.getX() + this.getWidth() && p.getX() >= this.getX() || p.getX() + p.getWidth() <= this.getX() + this.getWidth() && p.getX() + p.getWidth() >= this.getX()) {
			if(p.getY() <= this.getY() + this.getHeight() && p.getY() >= this.getY() || p.getY() + p.getHeight() <= this.getY() + this.getHeight() && p.getY() + p.getHeight() >= this.getY()) {
				touching = true;
			}
		}
		return touching;
	}

	@Override
	public boolean isApproachingPlayer(PlayerInterface p) {
		// TODO Auto-generated method stub
		return false;
	}
}
