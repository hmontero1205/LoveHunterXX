package snake;

import gui.GUIApplication;

public class MichaelSnakeGame extends GUIApplication {
	
	public static MichaelSnakeGame sGame; // the main class to run game.
	public static MichaelIntroScreen iScreen; // screen for introduction.
	public static MichaelSnakeScreen sScreen; // screen for game.
	
	public static boolean gameStart;

	public MichaelSnakeGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		gameStart = false;
		sScreen = new MichaelSnakeScreen(getWidth(),getHeight());
		iScreen = new MichaelIntroScreen(getWidth(),getHeight());
		setScreen(iScreen);
	}
	
	public static void main(String[] stuff){
		sGame = new MichaelSnakeGame(800,500);
		Thread game = new Thread(sGame);
		game.start();
	}
}
