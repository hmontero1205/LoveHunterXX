/**
 * 
 */
package main;

import frogger.FroggerScreen;
import gui.GUIApplication;

/**
 * @author Hans
 *
 */
public class LoveHunterXX extends GUIApplication {

	public static LoveHunterXX game;
	public static FroggerScreen fs;
	
	public LoveHunterXX(int width, int height) {
		super(width, height);
	}


	@Override
	public void initScreen() {
		fs = new FroggerScreen(800,600);
		setScreen(fs);
	}


	public static void main(String[] args) {
		game = new LoveHunterXX(800,600);
		Thread go = new Thread(game);
		go.start();

	}

}
