/**
 * @author Hans
 *
 */
package frogger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import gui.Screen;
import gui.components.Graphic;
import gui.components.MovingComponent;
import gui.components.Visible;

public class FroggerScreen extends Screen implements KeyListener, Runnable {

	public final static int LEFTARROWKEY = 37;
	public final static int UPARROWKEY = 38;
	public final static int RIGHTARROWKEY = 39;
	public final static int DOWNARROWKEY = 40;
	public final static int WINDOWBARHEIGHT = 26;
	public final static int ROW_HEIGHT = 41;
	public final static int ROW_WIDTH = 795;
	public final static int GRASS = 0;
	public final static int ROAD = 1;
	public final static int WATER = 2;
	public ArrayList<Terrain> tList;
	public static PlayerInterface player;
	public static int currentRow;
	public static boolean gameOver = false;

	public FroggerScreen(int w, int h) {
		super(w, h);
		Thread fGame = new Thread(this);
		fGame.start();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
//		tList = new ArrayList<Terrain>();
//		tList.add(new Terrain(3, WINDOWBARHEIGHT, ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT, ROAD, -9,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (2 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, -5,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (3 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (4 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (5 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 5,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (6 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, ROAD, 4,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (7 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (8 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (9 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, -4,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (10 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 7,true));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (11 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (12 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, WATER, 2,true));
//		tList.add(new Terrain(3, WINDOWBARHEIGHT + (13 * ROW_HEIGHT), ROW_WIDTH, ROW_HEIGHT, GRASS, 0,false));
//		viewObjects.addAll(tList);
//
//		player = getPlayer(400, 600 - ROW_HEIGHT + (20 / 2), 20, 20);
//		viewObjects.add(player);
		
		Turtle turtle = new Turtle(0 - 50, WINDOWBARHEIGHT + 25, 50, 25, 1, 1000, 1000, 5000);
		viewObjects.add(turtle);
		turtle.play();
		
		turtle = new Turtle(800, WINDOWBARHEIGHT + 25, 50, 25, -1, 1000, 1000, 5000);
		viewObjects.add(turtle);
		turtle.play();
		}

	public PlayerInterface getPlayer(int x, int y, int w, int h) {
		return new Player(x, y, w, h);
	}

	@Override
	public void run() {
//		for (int i = 0; i < tList.size(); i++) {
//			if (tList.get(i).getTerrain() == ROAD || tList.get(i).getTerrain() == WATER ) {
//				tList.get(i).startThread();
//			}
//		}
		
	}

	public void checkPlayerRow() {
		tList.get(currentRow).setCheckPlayer(false);
		currentRow = player.getY()/ROW_HEIGHT;
		tList.get(currentRow).setCheckPlayer(true);
		checkDrown();
		
		
	}
	
	public void checkDrown(){
		if(tList.get(currentRow).getTerrain() == WATER && !player.isOnPlatform()){
			gameOver("You drowned!!");
			tList.get(currentRow).setPostGame(true);
			
		}
	}

	public KeyListener getKeyListener() {
		return this;
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(!gameOver){
			int keyCode = k.getKeyCode();
			if (keyCode >= 37 && keyCode <= 40) {
				player.move(keyCode);
				checkPlayerRow();
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
		if(!gameOver){
			gameOver = true;
			System.out.println("GAME OVER!\n"+m);
			tList.get(currentRow).setCheckPlayer(false);
		}
		
	}

}
