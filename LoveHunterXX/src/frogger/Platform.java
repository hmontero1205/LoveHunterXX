package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.MovingComponent;

public class Platform extends MovingComponent {
	
	private boolean touchable;
	private String imgLoc;

	/**
	 * @param  x  initial x location of this component
	 * @param  y  initial y location of this component
	 * @param  w  width of this component
	 * @param  h  height of this component
	 * @param  vx  x velocity of this component
	 * @param  imgLoc  location of the image for this component
	 */
	
	public Platform(int x, int y, int w, int h, int vx, String imgLoc) {
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
	
//	drawing with no image
	public void createImage(Graphics2D g) {
		Color c = new Color(153, 102, 51);
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);

	}
	
//	drawing using an image
	public void createImage() {
		ImageIcon icon = new ImageIcon(getImgLoc());
		setImage(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB));
		Graphics2D g = getImage().createGraphics();
		AffineTransform  at = new AffineTransform ();
		double xScale = getWidth() / icon.getIconWidth();
		double yScale = getHeight() / icon.getIconHeight();
		at.scale(xScale, yScale);
		
		if(getVx() > 0) {
//			rotate cars if they are starting from the right side of the screen
			at.translate(icon.getIconWidth() / 2, icon.getIconHeight() / 2);
			at.rotate(Math.PI);
			at.translate(-icon.getIconWidth() / 2, -icon.getIconHeight() / 2);
		}
		
		g.drawImage(icon.getImage(), at, null);
	}
	
	
	public void run() {
		setPosx(getX());
		setPosy(getY());
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
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
	public void update(Graphics2D g) {
		long currentTime = System.currentTimeMillis();
		int diff = (int)(currentTime - getMoveTime());
		if(diff >= REFRESH_RATE){
			setMoveTime(currentTime);
			setPosx(getPosx() + getVx()*(double)diff/REFRESH_RATE);
			setPosy(getPosy() + getVy()*(double)diff/REFRESH_RATE);
			super.setX((int)getPosx());
			super.setY((int)getPosy());
		}
	}
	
	public void checkBehaviors() {
		if(getVx() > 0) {
			if(getX() > 800) {
				setX(0 - getWidth());
			}
		} else if(getVx() < 0) {
			if(getX() + getWidth() < 0) {
				setX(800);
			}
		}
	}
	
	public void isTouching(Player p) {
//		TODO later, not important atm, will probably need player x, y, width, height
	}
	
	public void play() {
		createImage(getImage().createGraphics());
		if(!isRunning()){
			Thread go = new Thread(this);
			go.start();
		}
	}
}
