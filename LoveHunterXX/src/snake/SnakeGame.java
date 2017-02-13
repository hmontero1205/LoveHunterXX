package snake;

import gui.GUIApplication;

public class SnakeGame extends GUIApplication {
	
	public static SnakeGame sGame; // the main class to run game.
	public static SnakeScreen sScreen; // screen for game.
	public static GuideScreen gScreen; // screen for instructions.

	public SnakeGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		sScreen = new SnakeScreen(getWidth(),getHeight());
		gScreen = new GuideScreen(getWidth(),getHeight());
		setScreen(gScreen);
	}
	
	public static void main(String[] stuff){
		sGame = new SnakeGame(800,500);
		Thread game = new Thread(sGame);
		game.start();
	}
}

