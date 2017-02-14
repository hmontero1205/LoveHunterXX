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

	public int sWidth;
	public int sHeight;
	public final int WINDOWBARHEIGHT = 31;
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
	public void initObjects(List<Visible> viewObjects) {
		tList = new ArrayList<Terrain>();
		//player = getPlayer();
		tList.add(new Terrain(8, 31, 792, ROW_HEIGHT, WATER,5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+ROW_HEIGHT,792,ROW_HEIGHT,WATER,0));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(2*ROW_HEIGHT),792,ROW_HEIGHT,ROAD,-5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(3*ROW_HEIGHT),792,ROW_HEIGHT,SAFEZONE,5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(4*ROW_HEIGHT),792,ROW_HEIGHT,WATER,5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(5*ROW_HEIGHT),792,ROW_HEIGHT,SAFEZONE,5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(6*ROW_HEIGHT),792,ROW_HEIGHT,WATER,5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(7*ROW_HEIGHT),792,ROW_HEIGHT,SAFEZONE,5));
		tList.add(new Terrain(8,WINDOWBARHEIGHT+(8*ROW_HEIGHT),792,ROW_HEIGHT,ROAD,5));
		viewObjects.addAll(tList);
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
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
