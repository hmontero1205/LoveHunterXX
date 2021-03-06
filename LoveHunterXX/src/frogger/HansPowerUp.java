/**
 * @author Hans
 *
 */
package frogger;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Component;
import main.LoveHunterXX;

public class HansPowerUp extends Component implements Runnable{
	private int effect;
	private String imgSrc;
	private boolean superCreated;
	private final int STRENGTH = 0;
	private final int SWIM = 1;
	private final int SLOW = 2;
	private Thread pThread;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param s Image source
	 * @param e Determines effect of PowerUp
	 */
	public HansPowerUp(int x, int y, int w, int h, String s, int e) {
		super(x,y,w,h);
		superCreated = true;
		this.effect = e;
		this.imgSrc = s;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if(superCreated){
			ImageIcon icon = new ImageIcon("resources/frogger/"+imgSrc);
			g.drawImage(icon.getImage(),0,0, getWidth(), getHeight(), null);
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

	public void performEffect() {
		switch(this.effect){
			case STRENGTH:
				HansFroggerScreen.player.setSuperStrength(true);	
				try {
					Thread.sleep(3000);
					HansFroggerScreen.player.setSuperStrength(false);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case SWIM:
				HansFroggerScreen.player.setSwimming(true);	
				try {
					Thread.sleep(5000);
					HansFroggerScreen.player.setSwimming(false);	
					LoveHunterXX.fs.checkPlayerRow();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case SLOW:
				LoveHunterXX.fs.setSlowMode(true);
				try {
					Thread.sleep(8000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LoveHunterXX.fs.setSlowMode(false);
				break;
		}
	}

	@Override
	public void run() {
		performEffect();
	}
	
	public void start(){
		pThread = new Thread(this);
		pThread.start();
	}
	
	public Thread getThread(){
		return this.pThread;
	}
	
}
