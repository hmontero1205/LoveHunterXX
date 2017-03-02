/**
 * @author Hans (Jia Ming worked on the inventory that is in the update method)
 *
 */
package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import gui.components.Component;

public class Terrain extends Component implements Runnable {
	private List<CollisionInterface> mcList;
	private String[] carSrcArr = { "bluecar.png", "whiteconvert.png", "greencar.png", "purplecar.png" };
	private String[] powerUpGraphics = {"glove.png","snorkel.png","stopwatch.png"};
	private int terrain;
	private String[] terrainGraphics = { "resources/frogger/grass.png", "resources/frogger/road.png",
			"resources/frogger/water.png" };
	private boolean superCreated;
	private int obVelocity;
	public boolean checkPlayer;
	private boolean genCars;
	private final int GRASS = 0;
	private final int ROAD = 1;
	private final int WATER = 2;
	private final int INVENTORY = 3;
	private final int MENU = 4;
	private boolean allowPush;
	private boolean postGame;
	private int numTurtles;
	private boolean genTurtles;
	private boolean isRunning;
	private PowerUp powerUp;
	private Thread tThread;

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
		allowPush = false;
		update();
	}
	
	public Thread getThread(){
		return tThread;
	}
	
	@Override
	public void update(Graphics2D g) {
		if (superCreated) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			switch(terrain) {
			case INVENTORY:
				g.setColor(new Color(128,21,21));
				g.fillRect(0, 0, getWidth(), getHeight());
				for(int i = 0; i < FroggerScreen.player.getInventory().size(); i ++) {
					ImageIcon im = new ImageIcon(FroggerScreen.player.getInventory().get(i).getImage());
					g.drawImage(im.getImage(), 10 + (i * 35), 10, 25, 25, null);
				}
				g.setColor(Color.pink);
				if(FroggerScreen.player.getInventory().size() > 0) g.drawRoundRect(7 + (FroggerScreen.player.getCurrentPowerUp() * 35) , 7, 31, 31,20, 20); // weird numbers so that the selection box can center itself around the items
				break;
			case MENU:
				g.setColor(new Color(128,21,21));
				g.fillRect(0, 0, getWidth(), getHeight());
				break;
			default:
				ImageIcon icon = new ImageIcon(getImgLoc());
				g.drawImage(icon.getImage(), 0, 0, getWidth() - 1, getHeight(), null);
			}
		}
	}

	public String getImgLoc() {
		return terrainGraphics[this.terrain];
	}

	public void startThread() {
		tThread = new Thread(this);
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
				break;
			default: break;
		}
		
	}

	public void runGrass() {
		while(isRunning){
			if(this.powerUp == null && Math.random()<1){
				int selection = (int) (Math.random()*3);
				int xCoord = 10+30*(int) (Math.random()*26);
				this.powerUp = new PowerUp(xCoord, getY()+10, 25, 25, powerUpGraphics[selection], selection);
				FroggerGame.fs.addObject(powerUp);
			}
			if(checkPlayer){
				checkPlayer();
			}
			
			try {
				int sleepTime = (checkPlayer && this.powerUp!=null) ? 40:9000;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println("Stopped grass");
			}
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
				System.out.println("Stopped road");
				break;
			}
		}
	}
	
	public void runWater() {
		while (isRunning) {
			addMovingComponents();
			checkMovingComponents();
			if(allowPush)
				checkPlayer();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				System.out.println("Stopped water");
				break;
			}
		}
		
	}
	
	public void addMovingComponents() {
		int vel = (FroggerGame.fs.getSlowMode()) ? (2 * (Math.abs(obVelocity)/obVelocity)) : obVelocity;
		int startingPos = (obVelocity > 0) ? 0 : 800;
		if(mcList.size() == 0){
			CollisionInterface m = determineMovingComponent(startingPos,vel);
			mcList.add(m);
			FroggerGame.fs.addObject(m);
			FroggerGame.fs.moveToFront(FroggerGame.fs.player);
			m.play();
		}
		CollisionInterface trailing = mcList.get(mcList.size() - 1);
		//CollisionInterface leading = mcList.get(0);	
		if(((trailing.getX() > 100 && obVelocity > 0 && Math.random() < .1) || (trailing.getX() < 700 && obVelocity < 0 && Math.random() < .1)) && (trailing.getVx() == vel)){
			CollisionInterface m = determineMovingComponent(startingPos,vel);
			mcList.add(m);
			FroggerGame.fs.addObject(m);
			FroggerGame.fs.moveToFront(FroggerGame.fs.player);
			m.play();
			
		}
	}
	
	public CollisionInterface determineMovingComponent(int s, int v){
		if(terrain == ROAD)
			return new Car(s, getY() + 10, 50, 25, v,"resources/frogger/" + carSrcArr[((int) (Math.random() * carSrcArr.length))]);
		else{
			if(genTurtles){
				return new Turtle(s, getY() + 10, 50, 25, v, (int) (1500*Math.random()), (int) (800*Math.random()),(int) (1000*(Math.random())));
			}
			else
				return new Log(s, getY() + 10, 50, 25, v,"resources/frogger/log.png");
		}
		
	}
	
	public void checkMovingComponents() {
		if(mcList.size()>0){
			CollisionInterface leading = mcList.get(0);
			if((leading.getX() < -20 && obVelocity < 0) || (leading.getX() > 800 && obVelocity > 0)){
				leading.setRunning(false);
				mcList.remove(leading);
				FroggerGame.fs.remove(leading);
			}
		}
	}

	public void checkPlayer() {
		if(terrain == GRASS){
			if(this.powerUp!=null && powerUp.isTouchingPlayer(FroggerGame.fs.player)){
				FroggerGame.fs.remove(powerUp);
				FroggerGame.fs.player.pickUpItem(powerUp);
				this.powerUp = null;
			}
		}
		else{
			for(int i=0;i<mcList.size();i++){
				CollisionInterface p = mcList.get(i);
				if(p.isTouchingPlayer(FroggerGame.fs.player)){
					if(p instanceof Car){
						if(FroggerGame.fs.player.getSuperStrength()){
							p.setRunning(false);
							mcList.remove(p);
							FroggerGame.fs.remove(p);
						}
						else{
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
						
					}
					else{
						if(!FroggerGame.fs.player.isOnPlatform()){
							FroggerGame.fs.player.ridePlatform(p);
							
						}
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
	public void setAllowPush(boolean b){
		this.allowPush = true;
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
