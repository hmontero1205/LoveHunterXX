package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.components.MovingComponent;

public class Platform extends MovingComponent {
	
	private boolean touchable;
	private Graphics2D g;
	
	public Platform(int x, int y, int w, int h) {
		super(x, y, w, h);
		touchable = true;
		setX(x);
		setY(y);
		setVx(1);
		createImage(getImage().createGraphics());
		play();
	}
	
	public void createImage(Graphics2D g) {
//		TODO later, randomize the size of the logs and etc
		Color c = new Color(153, 102, 51);
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);

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
//		TODO later, not important atm, probably need player x, y, width, height
	}
	
	public void play() {
		if(!isRunning()){
			Thread go = new Thread(this);
			go.start();
		}
	}
}
