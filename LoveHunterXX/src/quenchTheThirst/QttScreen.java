package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class QttScreen extends Screen implements KeyListener{
	
	private Graphic map;
	private Player user;

	public QttScreen(int width, int height) {
		super(width, height);
	}
	/**
	 * Screen needs to include the map, the ai(enemy) and the bottle
	 * the enemy and bottle belong to a arraylist that keeps on changing
	 * updates when bottle appear and disappear from screen
	 * and updates when enemy spawn/get killed
	 */
	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		map = new Graphic(0,0,"resources/tempmap");
		user = new Player();
		viewObjects.add(map);
		viewObjects.add(user);
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
