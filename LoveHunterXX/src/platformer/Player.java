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
	private boolean jumping;
	private String imgLoc;
	private ImageIcon icon;
	private boolean load;
	public Player(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imgLoc = imageLocation;
		load = false;
		jumping = false;
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
		}
	}
	private void setImage(BufferedImage bufferedImage) {
		
		image = bufferedImage;
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
		
	}
	public void play(){
		if(!isRunning()){
			Thread start = new Thread(this);
			start.start();
		}
	}
	public void setJump(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
