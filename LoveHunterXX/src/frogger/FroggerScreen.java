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

public class FroggerScreen extends Screen implements KeyListener,Runnable{

	public final static int LEFTARROWKEY = 37;
	public final static int UPARROWKEY = 38;
	public final static int RIGHTARROWKEY = 39;
	public final static int DOWNARROWKEY = 40;
	public final static int WINDOWBARHEIGHT = 26;
	public final static int ROW_HEIGHT = 40;
	public final static int SAFEZONE = 0;
	public final static int ROAD = 1;
	public final static int WATER = 2;	
	public ArrayList<Terrain> tList;
	public PlayerInterface player;

	
	public FroggerScreen(int w, int h) {
		super(w, h);
		Thread fGame = new Thread(this);
		fGame.start();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		tList = new ArrayList<Terrain>();
		tList.add(new Terrain(3,WINDOWBARHEIGHT, 794, ROW_HEIGHT, ROAD,9));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+ROW_HEIGHT,794,ROW_HEIGHT,WATER,0));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(2*ROW_HEIGHT),794,ROW_HEIGHT,ROAD,-5));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(3*ROW_HEIGHT),794,ROW_HEIGHT,SAFEZONE,5));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(4*ROW_HEIGHT),794,ROW_HEIGHT,ROAD,2));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(5*ROW_HEIGHT),794,ROW_HEIGHT,ROAD,5));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(6*ROW_HEIGHT),794,ROW_HEIGHT,WATER,5));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(7*ROW_HEIGHT),794,ROW_HEIGHT,SAFEZONE,5));
		tList.add(new Terrain(3,WINDOWBARHEIGHT+(8*ROW_HEIGHT),794,ROW_HEIGHT,ROAD,-4));
		viewObjects.addAll(tList);
		
		player = getPlayer((800 - 40) / 2, 600 - ROW_HEIGHT, 40, ROW_HEIGHT);
		viewObjects.add(player);
	}
	
	public PlayerInterface getPlayer(int x, int y, int w, int h){
		return new Player(x, y, w, h);
	}
	@Override
	public void run() {
		
		for(int i=0;i<tList.size();i++){
			if(tList.get(i).getTerrain() == ROAD){
				tList.get(i).startThread();
			}
		}
		
	}
	public KeyListener getKeyListener(){
		return this;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		int keyCode = k.getKeyCode();
		if(keyCode >= 37 && keyCode <= 40) {
			player.move(keyCode);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
