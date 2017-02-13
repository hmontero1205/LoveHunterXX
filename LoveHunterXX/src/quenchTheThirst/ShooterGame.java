package quenchTheThirst;

import gui.GUIApplication;

public class ShooterGame extends GUIApplication {

	public static ShooterGame shootGame; // the main class to run game.

	public ShooterGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		setScreen(new QTTScreen(getWidth(), getHeight()));
	}

	public static void main(String[] stuff) {
		shootGame = new ShooterGame(800, 600);
		Thread game = new Thread(shootGame);
		game.start();
	}

}
