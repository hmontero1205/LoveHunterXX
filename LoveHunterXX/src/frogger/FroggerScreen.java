/**
 * @author Hans and Jia Ming
 *
 */
package frogger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.MovingComponent;
import gui.components.TextLabel;
import gui.components.Visible;

public class FroggerScreen extends Screen implements KeyListener, MouseListener, Runnable, MouseWheelListener {

	public final static int WINDOWBARHEIGHT = 26;
	public final static int ROW_HEIGHT = 41;
	public final static int ROW_WIDTH = 795;
	public final static int GRASS = 0;
	public final static int ROAD = 1;
	public final static int WATER = 2;
	public final static int INVENTORY = 3;
	public final static int MENU = 4;
	public static ArrayList<Terrain> tList;
	public static PlayerInterface player;
	public static Terrain currentRow;
	public static boolean gameOver;
	private Button resetButton;
	private boolean superCreated;
	private boolean playerLocked;
	private boolean slowMode;
	public int level;
	private TextLabel infoBox;
	public ProgressMarker p;

	public FroggerScreen(int w, int h) {
		super(w, h);
		superCreated = true;
		this.level = 1;
		startGame();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		if (superCreated) {
//			endThreads(viewObjects);
//			viewObjects.clear();
			
			player = getPlayer(400, 600 - ROW_HEIGHT - 30, 20, 20);
			viewObjects.add(player);
			
			tList = new ArrayList<Terrain>();
			tList.add(new Terrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, INVENTORY, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (2 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (5 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 3, true));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER,-4, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, -5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (8 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 4, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -4, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, true));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (11 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, MENU, 0, false));
			viewObjects.addAll(tList);
//			p = new ProgressMarker(740,ROW_HEIGHT+35,25,25,"continue.png");
			p = new ProgressMarker(740,ROW_HEIGHT+480,100,50,"bluecar.png");
			viewObjects.add(p);
			infoBox = new TextLabel(10, 561, 500, 30, "Howdy");
			infoBox.setC(Color.pink);
			viewObjects.add(infoBox);
			resetButton = new Button(695, 561, 100, 35, "Restart", Color.pink, new Action() {

				@Override
				public void act() {
					startGame();
				};

			});
		}
	}

	public void endThreads(List<Visible> viewObjects) {
		if (tList != null) {
			for (int i = 0; i < tList.size(); i++) {
				// System.out.println("Terrain length: "+tList.size());
				Terrain t = tList.get(i);
				t.setRunning(false);
				

				t.getThread().interrupt();
				
				System.out.println("terrain stopped");
				
				List<CollisionInterface> tObList = t.getMcList();
				// System.out.println("Car length: "+tObList.size());
				if (tObList.size() > 0) {
					for (int j = 0; j < tObList.size(); j++) {
						CollisionInterface c = tObList.get(j);
						c.setRunning(false);
						System.out.println(c.isRunning());
						
							c.getThread().interrupt();
						System.out.println("collision stopped");
					}
				}
				
			}
		}
		if (player != null) {
			player.setRunning(false);
		}
		
		System.out.println(Thread.activeCount());

	}

	public PlayerInterface getPlayer(int x, int y, int w, int h) {
		return new Player(x, y, w, h);
	}

	@Override
	public void run() {
		for (int i = 0; i < tList.size(); i++) {
			tList.get(i).startThread();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerLocked = false;

	}

	public void checkPlayerRow() {
		currentRow.setCheckPlayer(false);
		currentRow = tList.get(player.getY() / ROW_HEIGHT);
		player.setTerrain(currentRow);
		currentRow.setCheckPlayer(true);
		checkDrown();

	}

	public void checkDrown() {
		if(player.getSwimming()){
			currentRow.setAllowPush(true);
		}
		else{
			if (currentRow.getTerrain() == WATER && !player.isOnPlatform()) {
				gameOver("You drowned!!");
				currentRow.setCheckPlayer(false);
				currentRow.setAllowPush(true);
			}
			else{
				currentRow.setAllowPush(false);
			}
		}
		
	}

	public KeyListener getKeyListener() {
		return this;
	}

	public MouseListener getMouseListener() {
		return this;
	}

	public MouseWheelListener getMouseWheelListener() {
		return this;
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (!gameOver && !playerLocked) {
			int kc = k.getKeyCode();
			switch(kc) {
			case KeyEvent.VK_SPACE:
				player.activatePower();
				break;
				
			case KeyEvent.VK_W:
			case KeyEvent.VK_A:
			case KeyEvent.VK_S:
			case KeyEvent.VK_D:
				player.move(kc);
				checkPlayerRow();
				break;
			case KeyEvent.VK_LEFT:
				player.mouseScrolled(-1);
				break;
			case KeyEvent.VK_RIGHT:
				player.mouseScrolled(1);
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void gameOver(String m) {
		if (!gameOver) {
			gameOver = true;
			infoBox.setText("Game over! "+m);
			currentRow.setCheckPlayer(false);
			addObject(resetButton);
		}

	}

	public void startGame() {
		ArrayList<PowerUp> inv = new ArrayList<PowerUp>();
		if(player != null) inv = player.getInventory();
		initObjects(getViewObjects());
		player.setInventory(inv);
		infoBox.setText("Level "+level);
		Thread fGame = new Thread(this);
		fGame.start();
		currentRow = tList.get(tList.size() - 1);
		gameOver = false;
		playerLocked = true;
		remove(resetButton);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (resetButton.isHovered(e.getX(), e.getY())) {
			resetButton.act();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		player.mouseScrolled(e.getWheelRotation());
	}

	public void setSlowMode(boolean c) {
		this.slowMode = c;
	}

	public boolean getSlowMode() {
		return slowMode;
	}

}
