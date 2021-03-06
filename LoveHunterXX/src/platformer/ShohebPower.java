package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Component;
import gui.components.MovingComponent;
import main.LoveHunterXX;

public class ShohebPower extends DanielObstacle implements Runnable, DanielPowerUp{
	private int effect;
	private String imgSrc;
	private Image image;
	
	private boolean load;
	private boolean picked;
	
	private int w;
	private int h;
	
	private final int HEART = 0;
	

	public ShohebPower(int x, int y, int w, int h,int vx,int vy,String s, int e) {
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
				LoveHunterXX.ps.obstacles.remove(this);
				LoveHunterXX.ps.remove(this);
				setRunning(false);
			}
			if(getX() < w*-1){
				LoveHunterXX.ps.obstacles.remove(this);
				LoveHunterXX.ps.remove(this);
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
				LoveHunterXX.ps.danielPlayer.setHp(LoveHunterXX.ps.danielPlayer.getHp() + 1);
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
		DanielPlayer playTemp = LoveHunterXX.ps.danielPlayer;
		if((playTemp.getX() + playTemp.getWidth()) > getPosx()
				&& (playTemp.getX() + playTemp.getWidth()) < (getPosx() + w)
				&& (playTemp.getY() + playTemp.getHeight()) > getPosy()){
			return true;
		}
		return false;
	}
	@Override
	public void setPowerID() {
		// TODO Auto-generated method stub
		
	}


}