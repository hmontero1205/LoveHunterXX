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
	private BufferedImage image;
	private String imgLoc;
	private ImageIcon icon;
	private boolean jump;
	public Player(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imgLoc = imageLocation;
		icon = new ImageIcon(imgLoc);
		image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
		setX(x);
		setY(y);
		play();
	}
	public void update(Graphics2D g){
		g.drawImage(image, 0, 0, null);
	}
	public void createImage(Graphics2D g){
		icon = new ImageIcon(getImgLoc());
		setImage(new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB));
		g = image.createGraphics();
		g.drawImage(icon.getImage(), 10, 10, null);
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
		createImage(image.createGraphics());
		if(!isRunning()){
			Thread start = new Thread(this);
			start.start();
		}
	}
}
