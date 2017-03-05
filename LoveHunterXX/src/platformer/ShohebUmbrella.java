package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Component;
import gui.components.MovingComponent;
import main.LoveHunterXX;

public class ShohebUmbrella extends MovingComponent {
	private int x;
	private int y;
	private int w;
	private int h;
	private Image closed;
	private Image open;
	private boolean active;
	private boolean load;
	private boolean superCreated;
	

	public ShohebUmbrella(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		loadImage();
		this.play();
	}

	private void loadImage() {
		try {
			closed = new ImageIcon("resources/umbrellaclosed.png").getImage();
			open = new ImageIcon("resources/umbrellaopen.png").getImage();
			load = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		setRunning(true);
		superCreated = true;
		while (isRunning()) {
			try {
				Thread.sleep(REFRESH_RATE);
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
//xd
	@Override
	public void update(Graphics2D g) {
		if (superCreated) {
			DanielPlayer p = ShohebPlatformerGame.cs.danielPlayer;
			if (load) {
				if (active) {
					ShohebPlatformerGame.cs.danielPlayer.setInitialV(0);
					super.setX((int) p.getX());
					super.setY((int) p.getY()-70);
					g.drawImage(open, 0, 0, getWidth(), getHeight(), 0, 0, open.getWidth(null), 
							open.getHeight(null), null);
				} else {
					super.setX((int) p.getX()-5);
					super.setY((int) p.getY()+50);
					g.drawImage(closed, 0, 0, getWidth(), getHeight(), 0, 0, closed.getWidth(null),
							closed.getHeight(null), null);
				}
			}
		}
	}

	public void setActive(boolean b) {
		if(this.active != b)
			clear();
		this.active = b;
	}

}
