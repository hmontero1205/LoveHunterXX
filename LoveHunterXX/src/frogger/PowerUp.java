package frogger;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Component;

public class PowerUp extends Component implements Runnable{
	private int effect;
	private String imgSrc;
	private boolean superCreated;
	private final int STRENGTH = 0;
	private final int SWIM = 1;
	private final int SLOW = 2;

	public PowerUp(int x, int y, int w, int h, String s, int e) {
		super(x,y,w,h);
		superCreated = true;
		this.effect = e;
		this.imgSrc = s;
		//Thread pThread = new Thread(this);
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if(superCreated){
			System.out.println(imgSrc);
			ImageIcon icon = new ImageIcon("resources/frogger/"+imgSrc);
			g.drawImage(icon.getImage(),0,0, getWidth(), getHeight(), null);
		}
		
	}

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

	public void performEffect() {
		FroggerScreen.player.removeItem(this);
		switch(this.effect){
			case STRENGTH:
				FroggerScreen.player.setSuperStrength(true);	
				System.out.println("le strength");
				try {
					Thread.sleep(3000);
					FroggerScreen.player.setSuperStrength(false);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case SWIM:
				FroggerScreen.player.setSwimming(true);	
				System.out.println("le swim");
				try {
					Thread.sleep(5000);
					FroggerScreen.player.setSwimming(false);	
					FroggerGame.fs.checkPlayerRow();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case SLOW:
				FroggerGame.fs.setSlowMode(true);
				System.out.println("le slow");
				try {
					Thread.sleep(8000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				FroggerGame.fs.setSlowMode(false);
				break;
		}
	}

	@Override
	public void run() {
		performEffect();
	}
	
	public void start(){
		Thread pThread = new Thread(this);
		pThread.start();
	}
	
}
