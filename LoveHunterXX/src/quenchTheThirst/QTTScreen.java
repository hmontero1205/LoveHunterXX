package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class QTTScreen extends Screen implements KeyListener {

	private Graphic map;
	private ArrayList<Entity> entities;

	public QTTScreen(int width, int height) {
		super(width, height);
	}

	/**
	 * Make Map Player Movement update player getx gety Zombie Movement update
	 * zombie getx gety (zombie are in an array list) Bottle Movement update
	 * bottle getx gety
	 */
	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		map = new Graphic(0, 0, 800, 600, "rescources/tempmap.png");
		viewObjects.add(map);
		viewObjects.addAll(entities);
	}

	@Override
	public KeyListener getKeyListener() {
		return this;
	}

	@Override
	public void keyPressed(KeyEvent event) {
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

}
