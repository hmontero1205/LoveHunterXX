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

public class Player extends MovingComponent implements PlayerInterface {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int moveDistance = 30;
	private int dir;
	private ArrayList<PowerUp> inventory;
	private int currentPowerUp = -1;
	private String[] pModels = { "resources/frogger/player/playerleft.png", "resources/frogger/player/playerright.png",
			"resources/frogger/player/playerup.png", "resources/frogger/player/playerdown.png" };
	private boolean onPlatform;
	private CollisionInterface currentPlatform;
	private Terrain currentTerrain;
	private boolean deathGraphic;
	private boolean strength;
	private boolean swimming;
	private Thread thread;

	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		dir = UP;
		inventory = new ArrayList<PowerUp>();
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
		switch (k) {
		case KeyEvent.VK_W:
			if (!outOfBounds(KeyEvent.VK_W)) {
				setY(getY() - FroggerScreen.ROW_HEIGHT);
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
				setY(getY() + FroggerScreen.ROW_HEIGHT);
				dir = DOWN;
			}
			break;
		case KeyEvent.VK_D:
			if (!outOfBounds(KeyEvent.VK_D)) {
				setX(getX() + moveDistance);
				dir = RIGHT;
			}
		}
		if(FroggerGame.fs.getP().isTouchingPlayer(this)) FroggerGame.fs.getP().nextLevel();
		setVx(0);
		setRunning(false);
		this.onPlatform = false;
		update();
	}
	
	public void changeItemSelection(int k) {
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
			if (getY() - FroggerScreen.ROW_HEIGHT < FroggerScreen.ROW_HEIGHT) {
				return true;
			}
			break;
		case KeyEvent.VK_A:
			if (getX() - moveDistance + getWidth() < 25) {
				return true;
			}
			break;
		case KeyEvent.VK_S:
			if (getY() + FroggerScreen.ROW_HEIGHT + getHeight() > 600 - FroggerScreen.ROW_HEIGHT) {
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
		for(int i = 0; i < FroggerScreen.tList.size(); i ++) {
			if(FroggerScreen.tList.get(i).getTerrain() == FroggerScreen.INVENTORY) {
				FroggerScreen.tList.get(i).update();
			}
		}
	}
	
	@Override
	public void activatePower() {
		if(inventory.size() > 0)  {
			inventory.get(currentPowerUp).start();
			currentPowerUp --;
		}
	}
	
	@Override
	public void ridePlatform(CollisionInterface p) {
		if (p instanceof Turtle) {
			if (((Turtle) p).isTouchable()) {
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
				e.printStackTrace();
			}

			if ((getX() > 780 || getX() < 10)) {
				setRunning(false);
				currentTerrain.setCheckPlayer(false);
				currentTerrain.setAllowPush(false);
				setVx(0);
				FroggerGame.fs.gameOver("You were swept away by the current!");
				die();
			}

			if (currentPlatform instanceof Turtle && !((Turtle) currentPlatform).isTouchable()
					&& !FroggerScreen.gameOver) {
				FroggerGame.fs.gameOver("The turtle betrayed you.");
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

	public void setTerrain(Terrain t) {
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


	public ArrayList<PowerUp> getInventory() {
		return inventory;
	}

	public void pickUpItem(PowerUp pu) {
		inventory.add(pu);
		mouseScrolled(0);
	}
	
	@Override
	public void removeItem(PowerUp powerUp) {
		inventory.remove(powerUp);
		mouseScrolled(0);
	}

	@Override
	public void setInventory(ArrayList<PowerUp> pu) {
		this.inventory = pu;
	}

}
