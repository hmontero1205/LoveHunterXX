package gui.components;

import java.awt.Color;
import java.awt.Graphics2D;

public class MovingComponent extends Component implements Runnable {
	private long moveTime; //time when the image last moved
	private double vx; //the horizontal velocity
	private double vy; //the vertical velocity
	private double posx; //the actual x-coordinate of the object
	private double posy; //the actual y-coordinate of the object
	public long getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(long moveTime) {
		this.moveTime = moveTime;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	private boolean running;

	public static final int REFRESH_RATE = 20;

	public MovingComponent(int x, int y, int w, int h) {
		super(x, y, w, h);
		vx=0;
		vy=0;
	}
	
	public boolean isAnimated(){
		return true;
	}
	
	public void setX(int x){
		super.setX(x);
		posx=x;
	}
	
	public void setY(int y){
		super.setY(y);
		posy=y;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	@Override
	public void run() {
		posx=getX();
		posy=getY();
		running = true;
		moveTime = System.currentTimeMillis();
		while(running){
			try {
				Thread.sleep(REFRESH_RATE);
				checkBehaviors();
				update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void checkBehaviors() {
		if(getY()>300){
			setY(300);
			setVy(-vy);
		}
		
	}

	@Override
	public void update(Graphics2D g) {
		long currentTime = System.currentTimeMillis();
		int diff = (int)(currentTime - moveTime);
		if(diff >= REFRESH_RATE){
			moveTime = currentTime;
			posx+=vx*(double)diff/REFRESH_RATE;
			posy+=vy*(double)diff/REFRESH_RATE;
			super.setX((int)posx);
			super.setY((int)posy);
		}
		drawImage(g);
	}

	public void drawImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillOval(0,0,getWidth(),getHeight());
		
	}
	
	public void play(){
		if(!running){
			Thread go = new Thread(this);
			go.start();
		}
	}

}
