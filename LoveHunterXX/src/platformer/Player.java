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
	private boolean load;
	private long startJump;
	
	private final double initialV;
	private double grav;
	private int hp;
	public Player(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.load = false;
		this.jump = false;
		this.hp = 3;
		
//		this.initialV = 5.5;
//		this.grav = .5;
		
		this.initialV = 6.3;
		this.grav = 1;
		
		
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
				if(diff >= REFRESH_RATE){
					setMoveTime(currentTime);
					setPosy(getPosy() + getVy());
					super.setY((int)getPosy());
				}
			}
		}
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
			if(getY() > 372){
				setJump(false);
				setY(370);
			}
			else{
				
					super.setVy(-newV);
				
			}
		}
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
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
	public void act() {
		// TODO Auto-generated method stub
		
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	
}
