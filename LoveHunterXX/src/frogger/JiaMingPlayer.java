/**
 * @author Jia Ming
 *
 */
package frogger;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.components.MovingComponent;

public class JiaMingPlayer extends MovingComponent implements HansPlayerInterface {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int moveDistance = 30;
	private JiaMingProgressMarkerInterface pMarker;
	private int dir;
	private ArrayList<HansPowerUp> inventory;
	private int currentPowerUp = -1;
	private String[] pModels = { "resources/frogger/player/playerleft.png", "resources/frogger/player/playerright.png",
			"resources/frogger/player/playerup.png", "resources/frogger/player/playerdown.png" };
	private boolean onPlatform;
	private HansCollisionInterface currentPlatform;
	private HansTerrain currentTerrain;
	private boolean deathGraphic;
	private boolean strength;
	private boolean swimming;
	private Thread thread;

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public JiaMingPlayer(int x, int y, int w, int h) {
		super(x, y, w, h);
		dir = UP;
		inventory = new ArrayList<HansPowerUp>();
		update();

	}

	@Override
	public void update(Graphics2D g) {
		if (getVx() != 0) {
			long currentTime = System.currentTimeMillis();
			int diff = (int) (currentTime - getMoveTime());
			if (diff >= REFRESH_RATE) {
				setMoveTime(currentTime);
				setPosx(getPosx() + getVx() * (double) diff / REFRESH_RATE);
				setPosy(getPosy() + getVy() * (double) diff / REFRESH_RATE);
				super.setX((int) getPosx());
				super.setY((int) getPosy());
			}
		}
		if (pModels != null) {
			g = clear();
			ImageIcon icon = (deathGraphic) ? new ImageIcon("resources/frogger/player/dead.png")
					: new ImageIcon(pModels[dir]);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(icon.getImage(), 0, 0, getWidth() - 3, getHeight() - 3, 0, 0, icon.getIconWidth(),
					icon.getIconHeight(), null);
		}
	}

	@Override
	public void move(int k) {
		// there are a lot of logic codes but I picked this one because this one has a lot of impact on the game
		// these are the controls for the player for when they press WASD, the controls for the powerups are in another method
		// and i think using switch and cases can make your code look extremely clean and good
		switch (k) {
		case KeyEvent.VK_W:
			if (!outOfBounds(KeyEvent.VK_W)) {
				setY(getY() - HansFroggerScreen.ROW_HEIGHT);
				dir = UP;
			}
			break;
		case KeyEvent.VK_A:
			if (!outOfBounds(KeyEvent.VK_A)) {
				setX(getX() - moveDistance);
				dir = LEFT;
			}
			break;
		case KeyEvent.VK_S:
			if (!outOfBounds(KeyEvent.VK_S)) {
				setY(getY() + HansFroggerScreen.ROW_HEIGHT);
				dir = DOWN;
			}
			break;
		case KeyEvent.VK_D:
			if (!outOfBounds(KeyEvent.VK_D)) {
				setX(getX() + moveDistance);
				dir = RIGHT;
			}
		}
		if(pMarker == null) pMarker = HansFroggerGame.fs.getProgressMarker();
		setVx(0); // if they get off the log, then their speed should return to 0
		setRunning(false); // and the thread should be stopped
		this.onPlatform = false;
		update();
		if(pMarker.isTouchingPlayer(this)) pMarker.nextLevel(); // the arrow key to the next level
	}
	
	public void changeItemSelection(int k){
		switch(k) {
		case KeyEvent.VK_LEFT:
			mouseScrolled(-1);
			break;
		case KeyEvent.VK_RIGHT:
			mouseScrolled(1);
		}
	}
	
	public boolean outOfBounds(int k) {
		switch (k) {
		case KeyEvent.VK_W:
			if (getY() - HansFroggerScreen.ROW_HEIGHT < HansFroggerScreen.ROW_HEIGHT) {
				return true;
			}
			break;
		case KeyEvent.VK_A:
			if (getX() - moveDistance + getWidth() < 25) {
				return true;
			}
			break;
		case KeyEvent.VK_S:
			if (getY() + HansFroggerScreen.ROW_HEIGHT + getHeight() > 600 - HansFroggerScreen.ROW_HEIGHT) {
				return true;
			}
			break;
		case KeyEvent.VK_D:
			if (getX() + moveDistance > 775) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseScrolled(int w) {
		if(currentPowerUp == -1 && inventory.size() > 0) { 
			currentPowerUp = 0;
		} else if(currentPowerUp != -1){
			if (w < 0) {
				if (currentPowerUp - 1 < 0 ) {
					currentPowerUp = inventory.size() - 1;
				} else {
					currentPowerUp -= 1;
				}
			} else if(w > 0){
				if (currentPowerUp + 1 > inventory.size() - 1) {
					currentPowerUp = 0;
				} else {
					currentPowerUp += 1;
				}
			}
			
		}
		updateInventory();
	}
	
	@Override
	public void activatePower() {
		if(inventory.size() > 0)  {
			inventory.get(currentPowerUp).start();
			inventory.remove(currentPowerUp);
			currentPowerUp --;
			if(currentPowerUp < 0) currentPowerUp = 0;
			updateInventory();
		}
	}
	
	private void updateInventory() {
		for(int i = 0; i < HansFroggerScreen.tList.size(); i ++) {
			if(HansFroggerScreen.tList.get(i).getTerrain() == HansFroggerScreen.INVENTORY) {
				HansFroggerScreen.tList.get(i).update();
			}
		}
	}
	
	@Override
	public void ridePlatform(HansCollisionInterface p) {
		if (p instanceof JiaMingTurtle) {
			if (((JiaMingTurtle) p).isTouchable()) {
				this.onPlatform = true;
				this.setVx(p.getVx());
				play();
			} else {
				this.onPlatform = false;
				this.setVx(p.getVx());
				play();
			}
		} else {
			this.onPlatform = true;
			this.setVx(p.getVx());
			play();
		}
		currentPlatform = p;

	}

	public void run() {
		setPosx(getX());
		setPosy(getY());
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
		while (isRunning()) {
			try {
				Thread.sleep(REFRESH_RATE);
				update();

			} catch (InterruptedException e) {
				
			}

			if ((getX() > 780 || getX() < 10)) {
				setRunning(false);
				currentTerrain.setCheckPlayer(false);
				currentTerrain.setAllowPush(false);
				setVx(0);
				HansFroggerGame.fs.gameOver("You were swept away by the current!");
				die();
			}

			if (currentPlatform instanceof JiaMingTurtle && !((JiaMingTurtle) currentPlatform).isTouchable()
					&& !HansFroggerScreen.gameOver) {
				HansFroggerGame.fs.gameOver("The turtle betrayed you.");
				die();
			}
		}

	}
	
	public Thread getThread() {
		return thread;
	}

	public void play() {
		if (!isRunning()) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void die() {
		this.deathGraphic = true;
		update();
	}

	public boolean isOnPlatform() {
		return onPlatform;
	}

	public void setTerrain(HansTerrain t) {
		this.currentTerrain = t;
	}

	public int getCurrentPowerUp() {
		return currentPowerUp;
	}

	@Override
	public void setSuperStrength(boolean b) {
		this.strength = b;
	}
	@Override
	public boolean getSuperStrength(){
		return this.strength;
	}

	@Override
	public void setSwimming(boolean b) {
		this.swimming = b;	
	}
	
	@Override
	public boolean getSwimming(){
		return this.swimming;
	}


	public ArrayList<HansPowerUp> getInventory() {
		return inventory;
	}

	public void pickUpItem(HansPowerUp pu) {
		inventory.add(pu);
		mouseScrolled(0);
	}

	@Override
	public void setInventory(ArrayList<HansPowerUp> pu) {
		// setInventory when you advance to the next level, so you can keep your items
		this.inventory = pu;
		currentPowerUp = 0;
		updateInventory();
	}

}
