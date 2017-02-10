package quenchTheThirst;

import gui.GUIApplication;
import snake.GuideScreen;

public class ShooterGame extends GUIApplication{

	public static ShooterGame shootGame; // the main class to run game.
	public static QttScreen qttScreen; // screen for game.

	public ShooterGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		qttScreen = new QttScreen(getWidth(),getHeight());
		setScreen(qttScreen);
	}
	
	public static void main(String[] stuff){
		shootGame = new ShooterGame(800,600);
		Thread game = new Thread(shootGame);
		game.start();
	}

}
