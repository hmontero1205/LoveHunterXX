package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Component;
import gui.components.MovingComponent;
import gui.components.Visible;

public class Terrain extends Component implements Runnable {

	private List<Car> cars;
	private String[] carSrcArr = { "bluecar.png", "whiteconvert.png", "greencar.png", "purplecar.png" };
	private ArrayList<Log> pf;
	//private ArrayList<AnimatedPlatform> apf;
	private int terrain;
	private String[] terrainGraphics = { "resources/frogger/grass.png", "resources/frogger/road.png",
			"resources/frogger/water.png" };
	private boolean superCreated;
	private int carVelocity;
	private boolean checkPlayer;
	private boolean safeRoad;

	public Terrain(int x, int y, int w, int h, int terrain, int carVelocity) {
		super(x, y, w, h);
		this.terrain = terrain;
		cars = Collections.synchronizedList(new ArrayList<Car>());
		this.carVelocity = carVelocity;
		superCreated = true;
		safeRoad = true;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if (superCreated) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			ImageIcon icon = new ImageIcon(getImgLoc());
			g.drawImage(icon.getImage(), 0, 0, getWidth() - 1, getHeight(), null);
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
		while (safeRoad) {
			addCars();
			checkCars();
			if(checkPlayer)
				checkPlayer();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void checkCars() {
		if (carVelocity < 0 && cars.size() > 0) {
			Car leadingCar = cars.get(0);
			if (leadingCar.getX() < 0) {
				cars.remove(leadingCar);
				FroggerGame.fs.remove(leadingCar);
			}
		} else {
			if (carVelocity > 0 && cars.size() > 0) {
				Car leadingCar = cars.get(0);
				if (leadingCar.getX() > 780) {
					cars.remove(leadingCar);
					FroggerGame.fs.remove(leadingCar);
				}
			}
		}

	}

	public void addCars() {
		if (cars.size() == 0) {
			int startingPos = (carVelocity > 0) ? 0 : 800;
			String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
			Car c1 = new Car(startingPos, getY() + 10, 50, 25, this.carVelocity,
					"resources/frogger/" + carSrc);
			cars.add(c1);
			FroggerGame.fs.addObject(c1);
			c1.play();
		}
		MovingComponent backCar = cars.get(cars.size() - 1);
		MovingComponent frontCar = cars.get(0);
		if (carVelocity > 0) {
			if (/* frontCar.getX()<700 && */ backCar.getX() > 100 && Math.random() < .1) {
				int startingPos = 0;
				String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
				Car c1 = new Car(startingPos, getY() + 10, 50, 25, this.carVelocity,
						"resources/frogger/" + carSrc);
				cars.add(c1);
				FroggerGame.fs.addObject(c1);
				c1.play();
			}
		} else {
			if (/* frontCar.getX()>100 && */ backCar.getX() < 700 && Math.random() < .1) {
				int startingPos = 800;
				String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
				Car c1 = new Car(startingPos, getY() + 10, 50, 25, this.carVelocity,
						"resources/frogger/" + carSrc);
				cars.add(c1);
				FroggerGame.fs.addObject(c1);
				c1.play();
			}
		}

	}

	public void checkPlayer() {
		for(int i=0;i<cars.size();i++){
			if(cars.get(i).isTouchingPlayer(FroggerGame.fs.player)){
				for(int j=i;j<cars.size();j++){
					cars.get(j).setRunning(false);
				}
				System.out.println("GAME OVER!");
				safeRoad = false;
				FroggerGame.fs.gameOver();
				break;
			}
		}

	}

	public int getTerrain() {
		// TODO Auto-generated method stub
		return terrain;
	}

	public void setCheckPlayer(boolean b){
		checkPlayer = b;
	}

}
