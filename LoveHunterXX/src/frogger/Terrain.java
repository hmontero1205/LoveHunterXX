package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Component;
import gui.components.Visible;

public class Terrain extends Component implements Runnable {

	private List<Obstacle> obs;
	private String[] carSrcArr = { "bluecar.png", "whiteconvert.png", "greencar.png", "purplecar.png" };
	private ArrayList<Platform> pf;
	private ArrayList<AnimatedPlatform> apf;
	private int terrain;
	private Color[] terrainColors = { Color.green, Color.darkGray, Color.blue };
	private String[] terrainGraphics = { "resources/frogger/grass.jpg", "resources/frogger/road.png",
			"resources/frogger/water.png" };
	private boolean superCreated;
	private int carVelocity;

	public Terrain(int x, int y, int w, int h, int terrain, int carVelocity) {
		super(x, y, w, h);
		this.terrain = terrain;
		obs = Collections.synchronizedList(new ArrayList<Obstacle>());
		this.carVelocity = carVelocity;
		superCreated = true;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if (superCreated) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			ImageIcon icon = new ImageIcon(getImgLoc());
			g.drawImage(icon.getImage(), 0, 0, getWidth() - 1, getHeight(), null);

			// g.setColor(terrainColors[terrain]);
			// g.fillRect(0, 0, getWidth() - 1, getHeight());
			// g.setColor(Color.black);
			// g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}

	public String getImgLoc() {
		return terrainGraphics[this.terrain];
	}

	public void startThread() {
		Thread tThread = new Thread(this);
		tThread.start();
	}

	@Override
	public void run() {
		// generateObstacles();
		while (true) {
			addCars();
			checkCars();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void checkCars() {
		if (carVelocity < 0 && obs.size() > 0) {
			Obstacle leadingCar = obs.get(0);
			if (leadingCar.getX() < 0) {
				obs.remove(leadingCar);
				FroggerGame.fs.remove(leadingCar);
			}
		} else {
			if (carVelocity > 0 && obs.size() > 0) {
				Obstacle leadingCar = obs.get(0);
				if (leadingCar.getX() > 780) {
					obs.remove(leadingCar);
					FroggerGame.fs.remove(leadingCar);
				}
			}
		}

	}

	public void addCars() {
		if (obs.size() == 0) {
			int startingPos = (carVelocity > 0) ? 0 : 800;
			String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
			Obstacle c1 = new Obstacle(startingPos, getY() + 10, 50, 25, this.carVelocity,
					"resources/frogger/" + carSrc);
			obs.add(c1);
			FroggerGame.fs.addObject(c1);
			c1.play();
		}
		Obstacle backCar = obs.get(obs.size() - 1);
		Obstacle frontCar = obs.get(0);
		if (carVelocity > 0) {
			if (/* frontCar.getX()<700 && */ backCar.getX() > 100 && Math.random() < .1) {
				int startingPos = 0;
				String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
				Obstacle c1 = new Obstacle(startingPos, getY() + 10, 50, 25, this.carVelocity,
						"resources/frogger/" + carSrc);
				obs.add(c1);
				FroggerGame.fs.addObject(c1);
				c1.play();
			}
		} else {
			if (/* frontCar.getX()>100 && */ backCar.getX() < 700 && Math.random() < .1) {
				int startingPos = 800;
				String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
				Obstacle c1 = new Obstacle(startingPos, getY() + 10, 50, 25, this.carVelocity,
						"resources/frogger/" + carSrc);
				obs.add(c1);
				FroggerGame.fs.addObject(c1);
				c1.play();
			}
		}

	}

	private void checkPlayer() {
		// TODO Auto-generated method stub

	}

	public int getTerrain() {
		// TODO Auto-generated method stub
		return terrain;
	}

}
