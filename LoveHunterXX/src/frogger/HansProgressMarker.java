package frogger;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import gui.components.Component;

public class HansProgressMarker extends Component implements JiaMingProgressMarkerInterface {
	private boolean superCreated;
	private String imgSrc;
	private boolean touching;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param s Image source
	 */
	public HansProgressMarker(int x, int y, int w, int h, String s) {
		super(x, y, w, h);
		superCreated = true;
		touching = false;
		imgSrc = s;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if (superCreated) {
			ImageIcon icon = new ImageIcon("resources/frogger/" + imgSrc);
			g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}

	public boolean isTouchingPlayer(HansPlayerInterface p) {
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

	public boolean isTouching() {
		return touching;
	}

	public void setTouching(boolean touching) {
		this.touching = touching;
	}

	public void nextLevel() {
		HansFroggerGame.fs.level++;
		HansFroggerGame.fs.startGame();
	}
}
