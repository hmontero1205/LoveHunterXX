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
import main.LoveHunterXX;

public class HansFroggerScreen extends Screen implements KeyListener, MouseListener, Runnable, MouseWheelListener {

	public final static int WINDOWBARHEIGHT = 26;
	public final static int ROW_HEIGHT = 41;
	public final static int ROW_WIDTH = 795;
	public final static int GRASS = 0;
	public final static int ROAD = 1;
	public final static int WATER = 2;
	public final static int INVENTORY = 3;
	public final static int MENU = 4;
	public static ArrayList<HansTerrain> tList;
	public static HansPlayerInterface player;
	public static HansTerrain currentRow;
	public static boolean gameOver;
	private Button resetButton;
	private boolean superCreated;
	private boolean playerLocked;
	private boolean slowMode;
	public int level;
	private TextLabel infoBox;
	private JiaMingProgressMarkerInterface pMarker;
	private Thread thread;
	private Button endB;
	private int pointsToAdd;

	public HansFroggerScreen(int w, int h) {
		super(w, h);
		superCreated = true;
		this.level = 1;
		this.pointsToAdd = -1;
		startGame();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		if (superCreated) {
			endThreads(viewObjects);
			viewObjects.clear();
			pMarker = getProgressMarker(740, ROW_HEIGHT + 480, 25, 25, "continue.png");
			viewObjects.add(pMarker);
			player = getPlayer(400, 600 - ROW_HEIGHT - 30, 20, 20);
			viewObjects.add(player);
			tList = new ArrayList<HansTerrain>();
			
			switch(level){
				case 1:
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, INVENTORY, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (2 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (5 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 3, true));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -4, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (8 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 4, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -4, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, true));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (11 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, MENU, 0, false));
					pMarker = new HansProgressMarker(740, ROW_HEIGHT + 35, 25, 25, "continue.png");
					break;
				case 2:
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, INVENTORY, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (2 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (5 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD,7, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -5, true));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (8 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -4, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (11 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, MENU, 0, false));
					pMarker = new HansProgressMarker(740, ROW_HEIGHT + 35, 25, 25, "continue.png");
					break;
				case 3:
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, INVENTORY, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (2 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -5, true));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 5, true));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (5 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER ,5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (8 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -4, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (11 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
					tList.add(new HansTerrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, MENU, 0, false));
					pMarker = new HansProgressMarker(745, 12*ROW_HEIGHT + 35, 25, 25, "gf.png");
					break;
			}
			
			viewObjects.addAll(tList);
			viewObjects.add(pMarker);
			infoBox = new TextLabel(10, 561, 500, 30, "Howdy");
			infoBox.setC(Color.pink);
			viewObjects.add(infoBox);
			resetButton = new Button(695, 561, 100, 35, "Restart", Color.pink, new Action() {

				@Override
				public void act() {
					if(gameOver)
						startGame();
				};

			});
		}
	}

	private JiaMingProgressMarkerInterface getProgressMarker(int x, int y, int w, int h, String img) {
		return  new HansProgressMarker(x, y, w, h, img);
	}

	public void endThreads(List<Visible> viewObjects) {
		if (tList != null) {
			//stops Terrain CollisionInterface
			for (int i = 0; i < tList.size(); i++) {
				HansTerrain t = tList.get(i);
				t.setRunning(false);
				if (t.getThread() != null) {
					t.getThread().interrupt();
				}
				List<HansCollisionInterface> tObList = t.getMcList();
				if (tObList.size() > 0) {
					for (int j = 0; j < tObList.size(); j++) {
						HansCollisionInterface c = tObList.get(j);
						c.setRunning(false);
						if (c.getThread() != null) {
							c.getThread().interrupt();
						}
					}
				}
			}

			//waits for the above threads to finish
			for (int i = 0; i < tList.size(); i++) {
				HansTerrain t = tList.get(i);
				//Terrain thread
				if (t.getThread() != null) {
					try {
						t.getThread().join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//CollisionInterface threads
				List<HansCollisionInterface> tObList = t.getMcList();
				if (tObList.size() > 0) {
					for (int j = 0; j < tObList.size(); j++) {
						HansCollisionInterface c = tObList.get(j);
						if (c.getThread() != null) {
							try {
								c.getThread().join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				//PowerUo threads, if exists
				if(t.getPowerUp() != null && t.getPowerUp().getThread()!=null){
					t.getPowerUp().getThread().interrupt();
				}
			}

		}
		
		//stops player thread and waits for it
		if (player != null) {
			player.setRunning(false);

			if (player.getThread() != null) {
				player.getThread().interrupt();

				try {
					player.getThread().join();
				} catch (InterruptedException e) {
					
				}
			}
		}
		//stops screen thread and waits for it
		if (thread != null) {
			try {
				thread.interrupt();
				thread.join();
			} catch (InterruptedException e) {
				
			}
		}
		
		//System.out.println(Thread.activeCount());
	}

	public HansPlayerInterface getPlayer(int x, int y, int w, int h) {
		return new JiaMingPlayer(x, y, w, h);
	}

	@Override
	public void run() {
		for (int i = 0; i < tList.size(); i++) {
			tList.get(i).startThread();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
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
		if (player.getSwimming()) {
			currentRow.setAllowPush(true);
		} else {
			if (currentRow.getTerrain() == WATER && !player.isOnPlatform()) {
				gameOver("You drowned!!");
				currentRow.setCheckPlayer(false);
				currentRow.setAllowPush(true);
			} else {
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
			switch (kc) {
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
					break;
				case KeyEvent.VK_P:
					pMarker.nextLevel();
			}

		}
	}

	public void gameOver(String m) {
		if (!gameOver) {
			gameOver = true;
			infoBox.setText("Game over! " + m);
			currentRow.setCheckPlayer(false);
			addObject(resetButton);
		}

	}
	
	public void gameEnd(){
		infoBox.setText("You finally made it to your gf!!");
		endB = new Button(695, 561, 100, 35, "Onward!", Color.pink, new Action() {

			@Override
			public void act() {
//				LoveHunterXX.ts.lovePoints += pointsToAdd;
//				LoveHunterXX.game.setScreen(LoveHunterXX.ts);
//				LoveHunterXX.ts.playSequence3();
				LoveHunterXX.game.setScreen(LoveHunterXX.qttis);
			};

		});
		addObject(endB);
		gameOver = true;
	}

	public void startGame() {
		pointsToAdd++;
		if(level == 4){
			gameEnd();
			endThreads(getViewObjects());
			//System.out.println(Thread.activeCount());
			
		}
		else{
			ArrayList<HansPowerUp> inv = new ArrayList<HansPowerUp>();
			if (player != null)
				inv = player.getInventory();
			initObjects(getViewObjects());
			player.setInventory(inv);
			infoBox.setText("Level " + level);
			thread = new Thread(this);
			thread.start();
			currentRow = tList.get(tList.size() - 1);
			gameOver = false;
			playerLocked = true;
			remove(resetButton);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (resetButton.isHovered(e.getX(), e.getY())) {
			resetButton.act();
		}
		if (endB!= null && endB.isHovered(e.getX(), e.getY())) {
			endB.act();
		}
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
	
	public JiaMingProgressMarkerInterface getProgressMarker() {
		return pMarker;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		
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
}
