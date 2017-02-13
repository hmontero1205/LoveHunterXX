package platformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.AnimatedComponent;
import gui.components.MovingComponent;

public class Player extends MovingComponent{
	private int x;
	private int y;
	private int w;
	private int h;
	private Image image;
	private boolean jump;
	private String imgLoc;
	private boolean load;
	private long startJump;
	private final long jumpLength;
	public Player(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		jumpLength = 2000;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imgLoc = imageLocation;
		load = false;
		jump = false;
		setX(x);
		setY(y);
		loadImage();
	}
	private void loadImage() {
		try{
			image = new ImageIcon("resources/player.png").getImage();
			load = true;
			update();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void update(Graphics2D g){
		if(load){
			g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
			if(jump){
				long currentTime = System.currentTimeMillis();
				int diff = (int)(currentTime - getMoveTime());
				if(diff >= REFRESH_RATE+80){
					setMoveTime(currentTime);
					setPosy(getPosy() + getVy());
					super.setY((int)getPosy());
				}
			}
		}
	}
	public String getImgLoc(){
		return imgLoc;
	}
	public void run() {
		setRunning(true);
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
	public void checkBehaviors(){
		if(jump){
			long current = System.currentTimeMillis();
			int difference = (int)(current - startJump);
			if(difference <= 1000){
				if(difference <= 500){
					super.setVy(-10);
				}
				else{
					super.setVy(-5);
				}
			}
			else{
				if(difference >= 2000){
					setJump(false);
				}
				else{
					if(difference >= 1000 && difference <= 1500){
						super.setVy(5);
					}
					else{
						super.setVy(10);
					}
				}
			}
			
			
		}
	}
	public void play(){
		if(!isRunning()){
			Thread start = new Thread(this);
			start.start();
		}
	}
	public void setJump(boolean b) {
		jump = b;
		startJump = System.currentTimeMillis();
	}
}
