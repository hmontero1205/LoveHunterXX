package frogger;

import gui.GUIApplication;
import main.LoveHunterXX;

public class FroggerGame extends GUIApplication {

	public static FroggerGame game;
	public static FroggerScreen fs;
	
	public FroggerGame(int width, int height) {
		super(width, height);
		setResizable(false);
	}


	@Override
	public void initScreen() {
		fs = new FroggerScreen(800,600);
		setScreen(fs);
	}


	public static void main(String[] args) {
		game = new FroggerGame(800,600);
		Thread go = new Thread(game);
		go.start();

	}

}
