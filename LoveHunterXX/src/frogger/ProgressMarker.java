package frogger;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Component;

public class ProgressMarker extends Component implements Runnable {
	private boolean superCreated;
	private String imgSrc;
	private boolean running;
	
	public ProgressMarker(int x, int y, int w, int h ,String s) {
		super(x, y, w, h);
		superCreated = true;
		imgSrc = s;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if(superCreated){
			ImageIcon icon = new ImageIcon("resources/frogger/"+imgSrc);
			g.drawImage(icon.getImage(),0,0, getWidth(), getHeight(), null);
		}
	}
	
	public boolean isTouchingPlayer(PlayerInterface p) {
		boolean touching = false;
		if (p.getX() <= this.getX() + this.getWidth() && p.getX() >= this.getX()
				|| p.getX() + p.getWidth() <= this.getX() + this.getWidth() && p.getX() + p.getWidth() >= this.getX()) {
			if (p.getY() <= this.getY() + this.getHeight() && p.getY() >= this.getY()
					|| p.getY() + p.getHeight() <= this.getY() + this.getHeight()
							&& p.getY() + p.getHeight() >= this.getY()) {
				touching = true;
			}
		}
		return touching;
	}

	@Override
	public void run() {
		while(isRunning()){	
			if(isTouchingPlayer(FroggerGame.fs.player)){
				System.out.println("hey");
				FroggerGame.fs.level++;
				FroggerGame.fs.startGame();
				setRunning(false);
			}
		}
		
	}

	public boolean isRunning() {
		return this.running;
	}
	
	public void setRunning(boolean b) {
		this.running = b;
	}

	public void start() {
		Thread p = new Thread(this);
		setRunning(true);
		p.start();
		
	}

}
