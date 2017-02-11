package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.MovingComponent;

public class Platform extends MovingComponent {
	
	private boolean touchable;
	private String imgLoc;

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
		g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
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
		if(getX() > FroggerScreen.sWidth) {
			setX(0 - getWidth());
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
