/**
 * 
 */
package main;

import gui.GUIApplication;

/**
 * @author Hans
 *
 */
public class LoveHunterXX extends GUIApplication {

	public static LoveHunterXX game;
	
	public LoveHunterXX(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void initScreen() {
		// TODO Auto-generated method stub

	}


	public static void main(String[] args) {
		game = new LoveHunterXX(800,600);
		Thread go = new Thread(game);
		go.start();

	}

}
