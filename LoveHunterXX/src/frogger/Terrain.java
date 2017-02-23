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
	private List<Log> logs;
	//private List<AnimatedPlatform> apf;
	private int terrain;
	private String[] terrainGraphics = { "resources/frogger/grass.png", "resources/frogger/road.png",
			"resources/frogger/water.png" };
	private boolean superCreated;
	private int obVelocity;
	private boolean checkPlayer;
	private boolean safeRoad;
	private final int GRASS = 0;
	private final int ROAD = 1;
	private final int WATER = 2;
	private boolean postGame;

	public Terrain(int x, int y, int w, int h, int terrain, int obVelocity) {
		super(x, y, w, h);
		this.terrain = terrain;
		this.obVelocity = obVelocity;
		switch(this.terrain){
			case ROAD:
				cars = Collections.synchronizedList(new ArrayList<Car>());
				safeRoad = true;
				break;
			case WATER:
				logs = Collections.synchronizedList(new ArrayList<Log>());
				break;
		}	
		postGame = false;
		superCreated = true;
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
		switch(terrain){
			case ROAD:
				runRoad();
				break;
			case WATER:
				runWater();
				break;
		}
		

	}
	public void runWater() {
		while (true) {
			addLogs();
			checkLogs();
			if(postGame)
				checkPlayer();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void checkLogs() {
		if (obVelocity < 0 && logs.size() > 0) {
			Log leadingLog = logs.get(0);
			if (leadingLog.getX() < 0) {
				logs.remove(leadingLog);
				FroggerGame.fs.remove(leadingLog);
			}
		} else {
			if (obVelocity > 0 && logs.size() > 0) {
				Log leadingLog = logs.get(0);
				if (leadingLog.getX() > 780) {
					logs.remove(leadingLog);
					FroggerGame.fs.remove(leadingLog);
				}
			}
		}
		
	}

	private void addLogs() {
		if(logs.size() == 0) {
			int startingPos = (obVelocity > 0) ? 0 : 800;
			Log l1 = new Log(startingPos, getY() + 10, 50, 25, this.obVelocity, "resources/frogger/log.png");
			logs.add(l1);
			FroggerGame.fs.addObject(l1);
			FroggerGame.fs.moveToFront(FroggerGame.fs.player);
			l1.play();
		}
		Log backLog = logs.get(logs.size() - 1);
		Log frontLog = logs.get(0);
		if (obVelocity > 0) {
			if (/* frontCar.getX()<700 && */ backLog.getX() > 100 && Math.random() < .1) {
				int startingPos = 0;
				Log l1 = new Log(startingPos, getY() + 10, 50, 25, this.obVelocity,
						"resources/frogger/log.png");
				logs.add(l1);
				FroggerGame.fs.addObject(l1);
				FroggerGame.fs.moveToFront(FroggerGame.fs.player);
				l1.play();
			}
		} 
		else {
			if (/* frontCar.getX()>100 && */ backLog.getX() < 700 && Math.random() < .1) {
				int startingPos = 800;
				Log l1 = new Log(startingPos, getY() + 10, 50, 25, this.obVelocity,"resources/frogger/log.png");
				logs.add(l1);
				FroggerGame.fs.addObject(l1);
				FroggerGame.fs.moveToFront(FroggerGame.fs.player);
				l1.play();
			}
		}
	}

	public void runRoad(){
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
		if (obVelocity < 0 && cars.size() > 0) {
			Car leadingCar = cars.get(0);
			if (leadingCar.getX() < 0) {
				cars.remove(leadingCar);
				FroggerGame.fs.remove(leadingCar);
			}
		} else {
			if (obVelocity > 0 && cars.size() > 0) {
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
			int startingPos = (obVelocity > 0) ? 0 : 800;
			String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
			Car c1 = new Car(startingPos, getY() + 10, 50, 25, this.obVelocity,
					"resources/frogger/" + carSrc);
			cars.add(c1);
			FroggerGame.fs.addObject(c1);
			FroggerGame.fs.moveToFront(FroggerGame.fs.player);
			c1.play();
		}
		Car backCar = cars.get(cars.size() - 1);
		Car frontCar = cars.get(0);
		if (obVelocity > 0) {
			if (/* frontCar.getX()<700 && */ backCar.getX() > 100 && Math.random() < .1) {
				int startingPos = 0;
				String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
				Car c1 = new Car(startingPos, getY() + 10, 50, 25, this.obVelocity,
						"resources/frogger/" + carSrc);
				cars.add(c1);
				FroggerGame.fs.addObject(c1);
				FroggerGame.fs.moveToFront(FroggerGame.fs.player);
				c1.play();
			}
		} else {
			if (/* frontCar.getX()>100 && */ backCar.getX() < 700 && Math.random() < .1) {
				int startingPos = 800;
				String carSrc = carSrcArr[((int) (Math.random() * carSrcArr.length))];
				Car c1 = new Car(startingPos, getY() + 10, 50, 25, this.obVelocity,
						"resources/frogger/" + carSrc);
				cars.add(c1);
				FroggerGame.fs.addObject(c1);
				FroggerGame.fs.moveToFront(FroggerGame.fs.player);
				c1.play();
			}
		}

	}

	public void checkPlayer() {
		if(terrain==ROAD){
			for(int i=0;i<cars.size();i++){
				if(cars.get(i).isTouchingPlayer(FroggerGame.fs.player)){
					for(int j=i;j<cars.size();j++){
						cars.get(j).setRunning(false);
					}
					safeRoad = false;
					FroggerGame.fs.gameOver();
					break;
				}
			}
		}
		if(terrain==WATER){
			for(int i=0;i<logs.size();i++){
				if(logs.get(i).isTouchingPlayer(FroggerGame.fs.player)){
					FroggerGame.fs.player.ridePlatform(logs.get(i));
					// hans, make it game over when the player is out of the map x<0 and x>800
					// also make the player die when they step off the log
					break;
				}
			}
			
		}

	}

	public int getTerrain() {
		// TODO Auto-generated method stub
		return terrain;
	}

	public void setCheckPlayer(boolean b){
		checkPlayer = b;
		if(b)
			checkPlayer();
	}
	public void setPostGame(boolean b){
		this.postGame = true;
	}

}
