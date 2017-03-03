package platformer;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gui.components.Component;
import gui.components.MovingComponent;

public class Umbrella extends MovingComponent {
	private int x;
	private int y;
	private int w;
	private int h;
	private Image closed;
	private Image open;
	private boolean active;
	private boolean load;
	private boolean superCreated;

	public Umbrella(int x, int y, int w, int h, String imageLocation) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		loadImages();
		this.play();
	}

	private void loadImages() {
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

	@Override
	public void update(Graphics2D g) {
		if (superCreated) {
			Player p = PlatformerGame.cs.player;
			if (load) {
				if (active) {
					g.drawImage(open, 0, 0, getWidth(), getHeight(), 0, 0, open.getWidth(null), open.getHeight(null),
							null);
				} else {
					g.drawImage(closed, 0, 0, getWidth(), getHeight(), 0, 0, closed.getWidth(null),
							closed.getHeight(null), null);
				}
				super.setX((int) p.getX());
				super.setY((int) p.getY());
			}
		}
	}

	public void setActive(boolean b) {
		this.active = true;
	}

}
