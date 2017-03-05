package platformer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.components.Action;
import main.LoveHunterXX;

public class DanielBird extends DanielObstacle {
	private int x;
	private int y;
	private int w;
	private int h;
	private int currentFrame;
	
	private long startTime;
	private long frameStart;
	private long checkRate;
	private ArrayList<Image> birdFrames;
	
	private Image image;
	private String imgLoc;
	
	private boolean load;
	private boolean collided;
	private boolean excreted;
	
	private Action action;
	
	
	public DanielBird(int x, int y, int w, int h, int vx, int vy, String imageLocation) {
		super(x, y, w, h, vx, vy, imageLocation);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.currentFrame = 0;
		this.imgLoc = imageLocation;
		load = false;
		
		birdFrames = new ArrayList<Image>();
		birdFrames.add(image = new ImageIcon(imgLoc).getImage());
		birdFrames.add(image = new ImageIcon("resources/bird2.png").getImage());
		birdFrames.add(image = new ImageIcon("resources/bird3.png").getImage());
		
		setX(x);
		setY(y);
		setVx(vx);
		setVy(vy);
		
		loadImage();
		startTime = System.currentTimeMillis();
		frameStart = System.currentTimeMillis();
		checkRate = 700;
		this.play();
	}
	private void loadImage() {
		try {
			image = new ImageIcon(imgLoc).getImage();
			load = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void update(Graphics2D g) {
		if (load) {
			if (isCollided() && !collided && !ShohebPlatformerGame.cs.danielPlayer.invuln) {
				collided = true;
				act();
			}
			
			if (getX() < w*-1) {
				ShohebPlatformerGame.cs.obstacles.remove(this);
				ShohebPlatformerGame.cs.remove(this);
				setRunning(false);
			} else {
				g.drawImage(birdFrames.get(currentFrame), 0, 0, getWidth(), getHeight(), 0, 0, birdFrames.get(currentFrame).getWidth(null), birdFrames.get(currentFrame).getHeight(null),
						null);
				setPosx(getPosx() + getVx());
				setPosy(getPosy() + getVy());
				super.setX((int) getPosx());
				super.setY((int) getPosy());
			}
		}
	}
//	public void update(Graphics2D g) {
//		if (load) {
//			if (isCollided() && !collided && !PlatformerGame.cs.player.invuln) {
//				collided = true;
//				act();
//			}
//			
//			if (getX() < w*-1) {
//				PlatformerGame.cs.obstacles.remove(this);
//				PlatformerGame.cs.remove(this);
//				setRunning(false);
//			} else {
//				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(null), image.getHeight(null),
//						null);
//				setPosx(getPosx() + getVx());
//				setPosy(getPosy() + getVy());
//				super.setX((int) getPosx());
//				super.setY((int) getPosy());
//			}
//		}
//	}
	public void createFeces(){
		DanielExcrement obs = new DanielExcrement((int)getPosx(), y+getHeight(), 50, 50, (int)getVx(), -.5, "resources/poop.png");
		obs.setAction(new Action(){
			public void act(){
				if(!ShohebPlatformerGame.cs.danielPlayer.invuln){
					ShohebPlatformerGame.cs.danielPlayer.setHp(ShohebPlatformerGame.cs.danielPlayer.getHp()-1);
					ShohebPlatformerGame.cs.danielPlayer.setDamaged(true);
				}
			}
		});
		ShohebPlatformerGame.cs.obstacles.add(obs);
		ShohebPlatformerGame.cs.addObject(obs);
	}
	public boolean isCollided(){
		DanielPlayer playTemp = ShohebPlatformerGame.cs.danielPlayer;
		if(playTemp.getX() < getPosx() + w && 
				playTemp.getX() + playTemp.getWidth() > getPosx() &&
				playTemp.getY() < getPosy() + h && playTemp.getY() + playTemp.getHeight() > getPosy()){
			return true;
		}
		return false;
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
//		System.out.println((int) Math.floor(Math.random() * 2));
		double chance = Math.random();
		
		if(chance < .005){
			startTime = System.currentTimeMillis();
			excreted = true;
			createFeces();
		}
		if(System.currentTimeMillis() - frameStart > checkRate){
			currentFrame = (currentFrame+1)%3;
		}
	}
}
