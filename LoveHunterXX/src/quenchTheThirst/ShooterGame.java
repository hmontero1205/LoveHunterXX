package quenchTheThirst;

import gui.GUIApplication;

public class ShooterGame extends GUIApplication {

	public static ShooterGame shootGame; // the main class to run game.
	public static QTTScreen shooterScreen;

	public ShooterGame(int width, int height) {
		super(width, height);
	}

	public void initScreen() {
		shooterScreen = new QTTScreen(800,600);
		setScreen(shooterScreen);
	}

	public static void main(String[] string) {
		shootGame = new ShooterGame(800, 600);
		Thread game = new Thread(shootGame);
		game.start();
	}

}
