package platformer;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Component;
import gui.components.MovingComponent;

public class Power extends MovingComponent implements Runnable, PowerUp{
	private int effect;
	private String imgSrc;
	private boolean superCreated;
	private final int HEART = 0;
	private final int INVULN = 1;
	private final int WATER = 2;
	

	public Power(int x, int y, int w, int h, String s, int e) {
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
	public void performEffect() {
		switch(this.effect){
			case HEART:
				PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() + 3);
				break;
			case INVULN:
				PlatformerGame.cs.player.setInvuln(true);
				break;
			case WATER:
				
//				FroggerGame.fs.setSlowMode(true);
//				try {
//					Thread.sleep(8000);	
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				FroggerGame.fs.setSlowMode(false);
//				break;
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

	@Override
	public boolean isCollided() {
		Player playTemp = PlatformerGame.cs.player;
		if(playTemp.getX() < getPosx() + w && 
				playTemp.getX() + playTemp.getWidth() > getPosx() &&
				playTemp.getY() < getPosy() + h && playTemp.getY() + playTemp.getHeight() > getPosy()){
			return true;
		}
		return false;
	}

	@Override
	public void setAction(Action action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPowerID() {
		// TODO Auto-generated method stub
		
	}
}