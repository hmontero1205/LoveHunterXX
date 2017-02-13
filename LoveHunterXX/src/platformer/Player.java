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
	private BufferedImage image;
	private String imgLoc;
	public Player(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.imgLoc = imageLocation;
		update();
	}
	public void update(Graphics2D g){
		try{
			ImageIcon icon = new ImageIcon(imgLoc);
			int newWidth = (int)(icon.getIconWidth());
			int newHeight = (int)(icon.getIconHeight());
			System.out.println("newWidth Before = " + newWidth);
			if(newWidth <= 0 && newHeight <= 0){
				System.out.println("newWidth After = " + newWidth);
				image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
				g = image.createGraphics();
				g.drawImage(icon.getImage(),0,0,null);
			}
			else{
				
				image = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
				g = image.createGraphics();
				//xCoord of destination,yCoord destination,width of dest,height,xCoord of target, yCoord of target, w + h of target, null
				g.drawImage(icon.getImage(),0,0,newWidth+1,newHeight+1,0,0,icon.getIconWidth(),icon.getIconHeight(),null);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
