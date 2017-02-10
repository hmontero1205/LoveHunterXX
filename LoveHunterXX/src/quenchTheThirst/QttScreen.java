package quenchTheThirst;

import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Graphic;
import gui.components.Visible;

public class QttScreen extends ClickableScreen implements KeyListener{
	
	private Graphic map;
	private Player user;

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
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		map = new Graphic(0,0,"resources/temp_map.png");
		viewObjects.add(map);
		
		user = new Player();
		viewObjects.add(user);
	}

}
