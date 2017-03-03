package platformer;

import javax.swing.ImageIcon;

import gui.GUIApplication;

public class ShohebPlatformerGame extends GUIApplication {
	public static ShohebPlatformerGame game;
	public static ShohebPlatformerScreen cs;

	public ShohebPlatformerGame(int width, int height) {
		super(width, height);

	}

	@Override
	public void initScreen() {
		cs = new ShohebPlatformerScreen(800, 600);
		setScreen(cs);
	}

	public static void main(String[] args) {
		game = new ShohebPlatformerGame(800, 600);
		game.setTitle("LoveHunterXX");
		game.setIconImage(new ImageIcon("resources/LoveHunterXXIcon.png").getImage());
		Thread app = new Thread(game);
		app.start();

	}

}
