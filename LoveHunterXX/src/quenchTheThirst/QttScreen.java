package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class QttScreen extends Screen implements KeyListener{
	
	private Graphic map;

	public QttScreen(int width, int height) {
		// TODO Auto-generated constructor stub
		super(width, height);
	}
	
	/**
	 * Make Map
	 * Player Movement update  player getx gety 
	 * Zombie Movement update  zombie getx gety (zombie are in an array list)
	 * Bottle Movement update  bottle getx gety
	 */
	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
