/**
 * @author Hans
 *
 */
package frogger;

import java.awt.Color;
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
import gui.components.Visible;

public class FroggerScreen extends Screen implements KeyListener, MouseListener, Runnable, MouseWheelListener {

	public final static int WINDOWBARHEIGHT = 26;
	public final static int ROW_HEIGHT = 41;
	public final static int ROW_WIDTH = 795;
	public final static int GRASS = 0;
	public final static int ROAD = 1;
	public final static int WATER = 2;
	public ArrayList<Terrain> tList;
	// public Terrain[][] levels = {
	// {new Terrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS,
	// 0,false),new Terrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH,
	// ROW_HEIGHT, WATER, 6,true),new Terrain(3, WINDOWBARHEIGHT + (2 *
	// ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false),new Terrain(3,
	// WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT,
	// ROAD,-5,false),new Terrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT),
	// ROW_WIDTH, ROW_HEIGHT, ROAD, 4,false),new Terrain(3, WINDOWBARHEIGHT + (5
	// * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false),new Terrain(3,
	// WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD,
	// 4,false),new Terrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH,
	// ROW_HEIGHT, ROAD, -5,false),new Terrain(3, WINDOWBARHEIGHT + (8 *
	// ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false),new Terrain(3,
	// WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER,
	// -4,false),new Terrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH,
	// ROW_HEIGHT, GRASS, 0,true),new Terrain(3, WINDOWBARHEIGHT + (11 *
	// ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5,false),new Terrain(3,
	// WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD,
	// 5,false),new Terrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH,
	// ROW_HEIGHT, GRASS, 0,false)}
	// };
	public PlayerInterface player;
	public static Terrain currentRow;
	public static boolean gameOver;
	private Button b;
	private boolean superCreated;
	private boolean playerLocked;

	public FroggerScreen(int w, int h) {
		super(w, h);
		superCreated = true;
		startGame();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		if (superCreated) {
			endThreads(viewObjects);
			viewObjects.clear();
			tList = new ArrayList<Terrain>();
			// for(Terrain t:levels[0]){
			// tList.add(t);
			// t.resetTerrain();
			// }
			tList.add(new Terrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT, WATER, 3, true));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (2 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 4, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (5 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 4, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (8 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -4, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, true));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (11 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 5, false));
			tList.add(new Terrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0, false));
			viewObjects.addAll(tList);

			player = getPlayer(400, 600 - ROW_HEIGHT + (20 / 2), 20, 20);
			viewObjects.add(player);

			b = new Button(695, 561, 100, 35, "Restart", Color.green, new Action() {

				@Override
				public void act() {
					startGame();
				};

			});
			viewObjects.add(b);
			//
			// Turtle turtle = new Turtle(0 - 50, WINDOWBARHEIGHT + 25, 50, 25,
			// 1, 1000, 1000, 1000);
			// viewObjects.add(turtle);
			// turtle.play();
			//
			// turtle = new Turtle(800, WINDOWBARHEIGHT + 25, 50, 25, -1, 1000,
			// 1000, 500);
			// viewObjects.add(turtle);
			// turtle.play();
		}
	}

	public void endThreads(List<Visible> viewObjects) {
		if (tList != null) {
			for (int i = 0; i < tList.size(); i++) {
				// System.out.println("Terrain length: "+tList.size());
				Terrain t = tList.get(i);
				t.setRunning(false);
				List<CollisionInterface> tObList = t.getMcList();
				// System.out.println("Car length: "+tObList.size());
				if (tObList.size() > 0) {
					for (int j = 0; j < tObList.size(); j++) {
						CollisionInterface c = tObList.get(j);
						c.setRunning(false);
					}
				}
			}
		}
		if (player != null) {
			player.setRunning(false);
		}

	}

	public PlayerInterface getPlayer(int x, int y, int w, int h) {
		return new Player(x, y, w, h);
	}

	@Override
	public void run() {
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).getTerrain() == ROAD || tList.get(i).getTerrain() == WATER) {
				tList.get(i).startThread();
			}
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
		if (currentRow.getTerrain() == WATER && !player.isOnPlatform()) {
			gameOver("You drowned!!");
			// player.die();
			currentRow.setCheckPlayer(false);
			currentRow.setPostGame(true);

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
			if (kc == KeyEvent.VK_W || kc == KeyEvent.VK_A || kc == KeyEvent.VK_S || kc == KeyEvent.VK_D) {
				player.move(kc);
				checkPlayerRow();
			} else if(kc == KeyEvent.VK_SPACE) player.activatePower();
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
			System.out.println("GAME OVER!\n" + m);
			currentRow.setCheckPlayer(false);
		}

	}

	public void startGame() {
		System.out.println("Game started");
		initObjects(getViewObjects());
		Thread fGame = new Thread(this);
		fGame.start();
		currentRow = tList.get(tList.size() - 1);
		gameOver = false;
		playerLocked = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (b.isHovered(e.getX(), e.getY())) {
			b.act();
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

}
