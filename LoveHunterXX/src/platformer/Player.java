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
	private final int initialV;
	private double grav;
	public Player(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		jumpLength = 2000;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imgLoc = imageLocation;
		this.load = false;
		this.jump = false;
		this.initialV = 21;
		this.grav = 2.2;
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
			double newV = initialV - grav*(double)(difference/100);
			if(difference <= jumpLength/2){
				super.setVy(-newV);
			}
			else{
				if(difference >= jumpLength){
					setJump(false);
					setY(370);
				}
				else{
					super.setVy(-newV);
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
	public boolean getJump() {
		return jump;
	}
}
