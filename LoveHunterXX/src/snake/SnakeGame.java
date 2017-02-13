package snake;

import gui.GUIApplication;

public class SnakeGame extends GUIApplication {
	
	public static SnakeGame sGame; // the main class to run game.
	public static IntroScreen iScreen; // screen for introduction.
	public static SnakeScreen sScreen; // screen for game.
	public static GuideScreen gScreen; // screen for instructions.

	public SnakeGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		sScreen = new SnakeScreen(getWidth(),getHeight());
		iScreen = new IntroScreen(getWidth(),getHeight());
		//gScreen = new GuideScreen(getWidth(),getHeight()); fix this pls :).
		setScreen(iScreen);
	}
	
	public static void main(String[] stuff){
		sGame = new SnakeGame(800,600);
		Thread game = new Thread(sGame);
		game.start();
	}
}

