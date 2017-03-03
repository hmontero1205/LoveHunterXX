package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Component;
import gui.components.MovingComponent;

public class Power extends Obstacle implements Runnable, PowerUp{
	private int effect;
	private String imgSrc;
	private Image image;
	
	private boolean load;
	private boolean picked;
	
	private int w;
	private int h;
	
	private final int HEART = 0;
	private final int INVULN = 1;
	private final int WATER = 2;
	

	public Power(int x, int y, int w, int h,int vx,int vy,String s, int e) {
		super(x,y,w,h, vx, vy, s);
		this.effect = e;
		this.imgSrc = s;
		this.w = w;
		this.h = h;
		
		setVx(vx);
		setVy(vy);
		//Thread pThread = new Thread(this);
		loadImage();
		this.play();
	}
	private void loadImage() {
		try {
			image = new ImageIcon(imgSrc).getImage();
			load = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void update(Graphics2D g) {
		if(load){
			if(!picked && isCollided()){
				performEffect();
				PlatformerGame.cs.obstacles.remove(this);
				PlatformerGame.cs.remove(this);
				setRunning(false);
			}
			if(getX() < w*-1){
				PlatformerGame.cs.obstacles.remove(this);
				PlatformerGame.cs.remove(this);
				setRunning(false);
			}
			else{
				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null),
						null);
				setPosx(getPosx() + getVx());
				setPosy(getPosy() + getVy());
				super.setX((int) getPosx());
				super.setY((int) getPosy());
			}
		}
		
	}
	public void performEffect() {
		picked = true;
		switch(this.effect){
			case HEART:
				PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() + 1);
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
				break;
		}
	}

	@Override
	public void run() {
		setRunning(true);
		while (isRunning()) {
			try {
				Thread.sleep(REFRESH_RATE);
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(){
		Thread pThread = new Thread(this);
		pThread.start();
	}

	@Override
	public boolean isCollided() {
		Player playTemp = PlatformerGame.cs.player;
		if((playTemp.getX() + playTemp.getWidth()) > getPosx()
				&& (playTemp.getX() + playTemp.getWidth()) < (getPosx() + w)
				&& (playTemp.getY() + playTemp.getHeight()) > getPosy()){
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