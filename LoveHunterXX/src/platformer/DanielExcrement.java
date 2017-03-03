package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DanielExcrement extends DanielObstacle {
	private double grav;
	private long startDrop;
	private String imgSrc;
	private Image image;
	private boolean load;
	private boolean collided;
	private boolean um;
	private int x;
	private int y;
	private int w;
	private int h;
	public DanielExcrement(int x, int y, int w, int h, int vx, double vy, String imageLocation) {
		super(x, y, w, h, vx, vy, imageLocation);
		
		startDrop = System.currentTimeMillis();
		grav = vy;
		
		imgSrc = imageLocation;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		setX(x);
		setY(y);
		
		loadImage();
		this.play();
	}
	private void loadImage() {
		try{
			image = new ImageIcon(imgSrc).getImage();
			load = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean isCollided(){
		DanielPlayer playTemp = ShohebPlatformerGame.cs.danielPlayer;
		ShohebUmbrella umb = ShohebPlatformerGame.cs.shohebUmbrella;
		if(umb.getX() < getPosx() + w && 
				umb.getX() + umb.getWidth() > getPosx() &&
				umb.getY() < getPosy() + h && umb.getY() + umb.getHeight() > getPosy()){
			um = true;
			return true;
		}
		if(playTemp.getX() < getPosx() + w && 
				playTemp.getX() + playTemp.getWidth() > getPosx() &&
				playTemp.getY() < getPosy() + h && playTemp.getY() + playTemp.getHeight() > getPosy()){
			return true;
		}
		
		return false;
	}
	public void update(Graphics2D g){
		if(load){
			if(getY() > 850 || um){
				ShohebPlatformerGame.cs.obstacles.remove(this);
				ShohebPlatformerGame.cs.remove(this);
				setRunning(false);
			}
			if (isCollided() && !um && !collided) {
				collided = true;
				act();
			}
			g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
			setPosy(getPosy() + getVy());
			setPosx(getPosx() + getVx());
			setY((int)getPosy());
			setX((int)getPosx());
		}
	}
	public void run() {
		setRunning(true);
		while (isRunning()) {
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
		long current = System.currentTimeMillis();
		double difference = (double)current - startDrop;
		double newVy = grav*(double)(difference/100);
		super.setVy(-newVy);
		
	}

}
