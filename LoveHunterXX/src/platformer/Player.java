package platformer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.AnimatedComponent;
import gui.components.MovingComponent;

public class Player extends MovingComponent{
	private int x;
	private int y;
	private int w;
	private int h;
	public Player(int x, int y, int w, int h){
		super(x,y,w,h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public void update(Graphics2D g){
		try{
			ImageIcon icon = new ImageIcon("resources/player.PNG");
			image = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			//xCoord of destination,yCoord destination,width of dest,height,xCoord of target, yCoord of target, w + h of target, null
			g.drawImage(icon.getImage(),0,0,w,h,0,0,icon.getIconWidth(),icon.getIconHeight(),null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
