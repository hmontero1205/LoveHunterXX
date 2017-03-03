package quenchTheThirst;

import gui.GUIApplication;

public class ShooterGame extends GUIApplication {

	public static ShooterGame shootGame;
	public static BillyShooterIntroScreen introScreen;
	public static BillyQTTScreen shooterScreen;

	public ShooterGame(int width, int height) {
		super(width, height);
		setResizable(true);
	}

	public void initScreen() {
		shooterScreen = new BillyQTTScreen(800, 600);
		introScreen = new BillyShooterIntroScreen(800,600);
		setScreen(introScreen);
	}

	public static void main(String[] string) {
		shootGame = new ShooterGame(800, 600);
		Thread game = new Thread(shootGame);
		game.start();
	}

}
