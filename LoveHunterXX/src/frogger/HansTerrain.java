/**
 * @author Hans (Jia Ming worked creating inventory for the INVENTORY Terrain)
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
import main.LoveHunterXX;

public class HansTerrain extends Component implements Runnable {
	private List<HansCollisionInterface> mcList;
	private String[] carSrcArr = { "bluecar.png", "whiteconvert.png", "greencar.png", "purplecar.png" };
	private String[] powerUpGraphics = {"glove.png","snorkel.png","stopwatch.png"};
	private int terrain;
	private String[] terrainGraphics = { "resources/frogger/grass.png", "resources/frogger/road.png",
			"resources/frogger/water.png" };
	private boolean superCreated;
	private int obVelocity;
	public boolean checkPlayer;
	private boolean genCars;
	private boolean allowPush;
	private boolean postGame;
	private int numTurtles;
	private boolean genTurtles;
	private boolean isRunning;
	private HansPowerUp powerUp;
	private Thread tThread;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param terrain Determines function of lane and things spawning in it
	 * @param obVelocity Velocity of MovingComponents in the lane
	 * @param genTurtles If lane is WATER, determines whether or not to spawn logs or turtles
	 */
	public HansTerrain(int x, int y, int w, int h, int terrain, int obVelocity, boolean genTurtles) {
		super(x, y, w, h);
		this.terrain = terrain;
		this.obVelocity = obVelocity;
		mcList = Collections.synchronizedList(new ArrayList<HansCollisionInterface>());
		this.genTurtles = genTurtles;
		superCreated = true;
		mcList.clear();
		if(terrain == HansFroggerScreen.ROAD)
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
			case HansFroggerScreen.INVENTORY:
				g.setColor(new Color(128,21,21));
				g.fillRect(0, 0, getWidth(), getHeight());
				for(int i = 0; i < HansFroggerScreen.player.getInventory().size(); i ++) {
					ImageIcon im = new ImageIcon(HansFroggerScreen.player.getInventory().get(i).getImage());
					g.drawImage(im.getImage(), 10 + (i * 35), 10, 25, 25, null);
				}
				g.setColor(Color.pink);
				if(HansFroggerScreen.player.getInventory().size() > 0) g.drawRoundRect(7 + (HansFroggerScreen.player.getCurrentPowerUp() * 35) , 7, 31, 31,20, 20); // weird numbers so that the selection box can center itself around the items
				break;
			case HansFroggerScreen.MENU:
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
			case HansFroggerScreen.ROAD:
				runRoad();
				break;
			case HansFroggerScreen.WATER:
				runWater();
				break;
			case HansFroggerScreen.GRASS:
				runGrass();
				break;
			default: break;
		}
		
	}

	public void runGrass() {
		while(isRunning){
			if(this.powerUp == null && Math.random()<.1){
				int selection = (int) (Math.random()*3);
				int xCoord = 10+30*(int) (Math.random()*26);
				this.powerUp = new HansPowerUp(xCoord, getY()+10, 25, 25, powerUpGraphics[selection], selection);
				LoveHunterXX.fs.addObject(powerUp);
			}
			if(checkPlayer){
				checkPlayer();
			}
			
			try {
				int sleepTime = (checkPlayer && this.powerUp!=null) ? 40:9000;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
//				System.out.println("Stopped grass");
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
//				System.out.println("Stopped road");
//				break;
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
//				System.out.println("Stopped water");
//				break;
			}
		}
		
	}
	
	public void addMovingComponents() {
		int vel = (LoveHunterXX.fs.getSlowMode()) ? (2 * (Math.abs(obVelocity)/obVelocity)) : obVelocity;
		int startingPos = (obVelocity > 0) ? 0 : 800;
		if(mcList.size() == 0){
			HansCollisionInterface m = determineMovingComponent(startingPos,vel);
			mcList.add(m);
			LoveHunterXX.fs.addObject(m);
			LoveHunterXX.fs.moveToFront(LoveHunterXX.fs.player);
			m.play();
		}
		HansCollisionInterface trailing = mcList.get(mcList.size() - 1);
		//CollisionInterface leading = mcList.get(0);	
		if(((trailing.getX() > 100 && obVelocity > 0 && Math.random() < .1) || (trailing.getX() < 700 && obVelocity < 0 && Math.random() < .1)) && (trailing.getVx() == vel)){
			HansCollisionInterface m = determineMovingComponent(startingPos,vel);
			mcList.add(m);
			LoveHunterXX.fs.addObject(m);
			LoveHunterXX.fs.moveToFront(LoveHunterXX.fs.player);
			m.play();
			
		}
	}
	
	public HansCollisionInterface determineMovingComponent(int s, int v){
		if(terrain == HansFroggerScreen.ROAD)
			return new JiaMingCar(s, getY() + 10, 50, 25, v,"resources/frogger/" + carSrcArr[((int) (Math.random() * carSrcArr.length))]);
		else{
			if(genTurtles){
				return new JiaMingTurtle(s, getY() + 10, 50, 25, v, (int) (1500*Math.random()), (int) (800*Math.random()),(int) (1000*(Math.random())));
			}
			else{
				return new JiaMingLog(s, getY() + 10, 50, 25, v,"resources/frogger/log.png");
			}
		}
		
	}
	
	public void checkMovingComponents() {
		if(mcList.size()>0){
			HansCollisionInterface leading = mcList.get(0);
			if((leading.getX() < -20 && obVelocity < 0) || (leading.getX() > 800 && obVelocity > 0)){
				leading.setRunning(false);
				mcList.remove(leading);
				LoveHunterXX.fs.remove(leading);
			}
		}
	}

	public void checkPlayer() {
		if(terrain == HansFroggerScreen.GRASS){
			if(this.powerUp!=null && powerUp.isTouchingPlayer(LoveHunterXX.fs.player)){
				LoveHunterXX.fs.remove(powerUp);
				LoveHunterXX.fs.player.pickUpItem(powerUp);
				this.powerUp = null;
			}
		}
		else{
			for(int i=0;i<mcList.size();i++){
				HansCollisionInterface p = mcList.get(i);
				if(p.isTouchingPlayer(LoveHunterXX.fs.player)){
					if(p instanceof JiaMingCar){
						if(LoveHunterXX.fs.player.getSuperStrength()){
							p.setRunning(false);
							mcList.remove(p);
							LoveHunterXX.fs.remove(p);
						}
						else{
							genCars = false;
							LoveHunterXX.fs.gameOver("You were run over by a car!!");
							LoveHunterXX.fs.player.die();
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							for(int j=i;j<mcList.size();j++){
								JiaMingCar c2 = (JiaMingCar) mcList.get(j);
								c2.setRunning(false);
							}
						}
						
					}
					else{
						if(!LoveHunterXX.fs.player.isOnPlatform()){
							LoveHunterXX.fs.player.ridePlatform(p);	
						}
					}
					break;
				}	
			}
		}
	}

	public int getTerrain() {
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
	
	public List<HansCollisionInterface> getMcList(){
		return this.mcList;
	}
	
	public HansPowerUp getPowerUp(){
		return this.powerUp;
	}

	
}
