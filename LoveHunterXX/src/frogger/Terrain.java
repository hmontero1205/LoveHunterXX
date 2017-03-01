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
import gui.components.Action;

public class Terrain extends Component implements Runnable {
	private List<CollisionInterface> mcList;
	private String[] carSrcArr = { "bluecar.png", "whiteconvert.png", "greencar.png", "purplecar.png" };
	private String[] powerUpGraphics = {"glove.png","snorkel.png","stopwatch.png"};
	private int terrain;
	private String[] terrainGraphics = { "resources/frogger/grass.png", "resources/frogger/road.png",
			"resources/frogger/water.png" };
	private boolean superCreated;
	private int obVelocity;
	private boolean checkPlayer;
	private boolean genCars;
	private final int GRASS = 0;
	private final int ROAD = 1;
	private final int WATER = 2;
	private boolean postGame;
	private int numTurtles;
	private boolean genTurtles;
	private boolean isRunning;
	private PowerUp powerUp;

	public Terrain(int x, int y, int w, int h, int terrain, int obVelocity, boolean genTurtles) {
		super(x, y, w, h);
		this.terrain = terrain;
		this.obVelocity = obVelocity;
		mcList = Collections.synchronizedList(new ArrayList<CollisionInterface>());
		this.genTurtles = genTurtles;
		superCreated = true;
		mcList.clear();
		if(terrain == ROAD)
			genCars = true;
		isRunning = false;
		postGame = false;
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
		isRunning = true;
		switch(terrain){
			case ROAD:
				runRoad();
				break;
			case WATER:
				runWater();
				break;
			case GRASS:
				runGrass();
		}
		
	}
	
	public void runGrass() {
		while(isRunning){
			if(this.powerUp == null && Math.random()<.01){
				System.out.println("hey");
				int selection = (int) (Math.random()*3);
				int xCoord = 10+30*(int) (Math.random()*27);
				this.powerUp = new PowerUp(xCoord, getY()+10, 25, 25, powerUpGraphics[selection], getAction(selection));
				FroggerGame.fs.addObject(powerUp);
			}
			if(checkPlayer){
				checkPlayer();
			}
			try {
				int sleepTime = (checkPlayer) ? 40:1000;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public Action getAction(int j) {
		switch(j){
			case 0:
				return new Action(){
					@Override
					public void act() {
						//FroggerScreen.player.setSuperStrength(true);	
					}
				};
			case 1:
				return new Action(){
					@Override
					public void act() {
						//FroggerScreen.player.setSwimming(true);	
					}
				};
			case 2:
				return new Action(){
					@Override
					public void act() {
						//FroggerScreen.setSlow(true);
					}
				};	
			default: return null;
		}
	}

	public void runRoad(){
		while (isRunning) {
			if(genCars)
				addMovingComponents();
			checkMovingComponents();
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
	
	public void runWater() {
		while (isRunning) {
			addMovingComponents();
			checkMovingComponents();
			if(postGame)
				checkPlayer();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addMovingComponents() {
		
		int startingPos = (obVelocity > 0) ? 0 : 800;	
		//String imgSrc = (terrain==ROAD) ? carSrcArr[((int) (Math.random() * carSrcArr.length))] : "log.png";
		if(mcList.size() == 0){
			CollisionInterface m = determineMovingComponent(startingPos);
			mcList.add(m);
			FroggerGame.fs.addObject(m);
			FroggerGame.fs.moveToFront(FroggerGame.fs.player);
			m.play();
		}
		CollisionInterface trailing = mcList.get(mcList.size() - 1);
		//Car frontCar = cars.get(0);	
		if((trailing.getX() > 100 && obVelocity > 0 && Math.random() < .1) || (trailing.getX() < 700 && obVelocity < 0 && Math.random() < .1)){
			CollisionInterface m = determineMovingComponent(startingPos);
			mcList.add(m);
			FroggerGame.fs.addObject(m);
			FroggerGame.fs.moveToFront(FroggerGame.fs.player);
			m.play();
			
		}
	}
	
	public CollisionInterface determineMovingComponent(int s){
		if(terrain == ROAD)
			return new Car(s, getY() + 10, 50, 25, this.obVelocity,"resources/frogger/" + carSrcArr[((int) (Math.random() * carSrcArr.length))]);
		else{
			if(genTurtles){
				return new Turtle(s, getY() + 10, 50, 25, this.obVelocity, (int) (1500*Math.random()), (int) (800*Math.random()),(int) (1000*(Math.random())));
			}
			else
				return new Log(s, getY() + 10, 50, 25, this.obVelocity,"resources/frogger/log.png");
		}
		
	}
	
	public void checkMovingComponents() {
		if(mcList.size()>0){
			CollisionInterface leading = mcList.get(0);
			if((leading.getX() < -20 && obVelocity < 0) || (leading.getX() > 800 && obVelocity > 0)){
				mcList.remove(leading);
				FroggerGame.fs.remove(leading);
			}
		}
	}

	public void checkPlayer() {
		if(terrain == GRASS){
			if(this.powerUp!=null && powerUp.isTouchingPlayer(FroggerGame.fs.player)){
				//do somethings
			}
		}
		else{
			for(int i=0;i<mcList.size();i++){
				CollisionInterface p = mcList.get(i);
				if(p.isTouchingPlayer(FroggerGame.fs.player)){
					if(p instanceof Car){
						genCars = false;
						FroggerGame.fs.gameOver("You were run over by a car!!");
						FroggerGame.fs.player.die();
						try {
							Thread.sleep(40);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for(int j=i;j<mcList.size();j++){
							Car c2 = (Car) mcList.get(j);
							c2.setRunning(false);
						}
					}
					else{
						if(!FroggerGame.fs.player.isOnPlatform())
							FroggerGame.fs.player.ridePlatform(p);
					}
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
	
	public boolean isRunning() {
		return isRunning;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public List<CollisionInterface> getMcList(){
		return this.mcList;
	}

	
}
