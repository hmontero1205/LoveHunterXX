package quenchTheThirst;

import gui.GUIApplication;

public class AriqShooterGame extends GUIApplication {

	public static AriqShooterGame shootGame;
	public static BillyShooterIntroScreen introScreen;
	public static BillyQTTScreen shooterScreen;

	public AriqShooterGame(int width, int height) {
		super(width, height);
		setResizable(true);
	}

	public void initScreen() {
		shooterScreen = new BillyQTTScreen(800, 600);
		introScreen = new BillyShooterIntroScreen(800,600);
		setScreen(introScreen);
	}

	public static void main(String[] string) {
		shootGame = new AriqShooterGame(800, 600);
		Thread game = new Thread(shootGame);
		game.start();
	}

}
