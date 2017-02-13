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

import gui.Screen;
import gui.components.Graphic;
import gui.components.MovingComponent;
import gui.components.Visible;

public class FroggerScreen extends Screen implements KeyListener,Runnable{

	public int sWidth;
	public int sHeight;
	public final int WINDOWBARHEIGHT = 32;
	public final int ROW_HEIGHT = 40;
	public final int SAFEZONE = 0;
	public final int ROAD = 1;
	public final int WATER = 2;	
	public ArrayList<Terrain> tList;
	public PlayerInterface player;

	
	public FroggerScreen(int w, int h) {
		super(w, h);
		Thread fGame = new Thread(this);
		fGame.start();
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		tList = new ArrayList<Terrain>();
		//player = getPlayer();
		Terrain t1 = new Terrain(8, 31, 792, ROW_HEIGHT, ROAD,3);
		Terrain t2 = new Terrain(8,31+ROW_HEIGHT,792,ROW_HEIGHT,WATER,-5);
		Terrain t3 = new Terrain(8,31+(2*ROW_HEIGHT),792,ROW_HEIGHT,ROAD,-2);
		Terrain t4 = new Terrain(8,31+(3*ROW_HEIGHT),792,ROW_HEIGHT,ROAD,4);
		Terrain t5 = new Terrain(8,31+(4*ROW_HEIGHT),792,ROW_HEIGHT,ROAD,4);
		Terrain t6 = new Terrain(8,31+(5*ROW_HEIGHT),792,ROW_HEIGHT,ROAD,5);
		tList.add(t1);
		tList.add(t2);
		tList.add(t3);
		tList.add(t4);
		tList.add(t5);
		tList.add(t6);
		viewObjects.add(t1);
		viewObjects.add(t2);
		viewObjects.add(t3);
		viewObjects.add(t4);
		viewObjects.add(t5);
		viewObjects.add(t6);
		//viewObjects.add(player);
		
	}
	
//	public PlayerInterface getPlayer(){
//		return new PlayerInterface();
//	}
	@Override
	public void run() {
		for(int i=0;i<tList.size();i++){
			if(tList.get(i).getTerrain() == ROAD){
				tList.get(i).startThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public KeyListener getKeyListener(){
		return this;
	}
	@Override
	public void keyPressed(KeyEvent k) {
//		if(k.getKeyCode()==KeyEvent.VK_UP){
//			player.move(k);
//		}
		
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
