package snake;

import gui.GUIApplication;

public class SnakeGame extends GUIApplication {
	
	public static SnakeGame sGame;
	public static SnakeScreen sScreen;

	public SnakeGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		sScreen = new SnakeScreen(getWidth(),getHeight());
		setScreen(sScreen);
	}
	
	public static void main(String[] stuff){
		sGame = new SnakeGame(800,600);
		Thread game = new Thread(sGame);
		game.start();
	}
}

