package snake;

import javax.swing.ImageIcon;

import gui.GUIApplication;

public class MichaelSnakeGame extends GUIApplication {
	
	public static MichaelSnakeGame sGame; // the main class to run game.
	public static MichaelIntroScreen iScreen; // screen for introduction.
	public static MichaelDavidSnakeScreen sScreen; // screen for game.
	
	public static boolean gameStart;

	public MichaelSnakeGame(int width, int height) {
		super(width, height);
		setResizable(false);
	}

	public void initScreen() {
		gameStart = false;
		sScreen = new MichaelDavidSnakeScreen(getWidth(),getHeight());
		iScreen = new MichaelIntroScreen(getWidth(),getHeight());
		setScreen(iScreen);
	}
	
	public static void main(String[] stuff){
		sGame = new MichaelSnakeGame(800,500);
		sGame.setTitle("LoveHunterXX");
		sGame.setIconImage(new ImageIcon("resources/LoveHunterXXIcon.png").getImage());
		Thread game = new Thread(sGame);
		game.start();
	}
}
