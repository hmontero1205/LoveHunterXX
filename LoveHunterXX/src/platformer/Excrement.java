package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Excrement extends Obstacle {
	private double grav;
	private long startDrop;
	private String imgSrc;
	private Image image;
	private boolean load;
	public Excrement(int x, int y, int w, int h, int vx, double vy, String imageLocation) {
		super(x, y, w, h, vx, vy, imageLocation);
		grav = vy;
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
	public void update(Graphics2D g){
		if(load){
			g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
			setPosy(getPosy() + 1.5);
			setY((int)getPosy());
//			setX((int)getPosx());
//			System.out.println(getPosy());
//			System.out.println(getPosx());
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
