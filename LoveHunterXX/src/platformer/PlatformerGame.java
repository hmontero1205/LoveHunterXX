package platformer;

import javax.swing.ImageIcon;

import gui.GUIApplication;

public class PlatformerGame extends GUIApplication {
	public static PlatformerGame game;
	public static PlatformerScreen cs;

	public PlatformerGame(int width, int height) {
		super(width, height);

	}

	@Override
	public void initScreen() {
		cs = new PlatformerScreen(800, 600);
		setScreen(cs);
	}

	public static void main(String[] args) {
		game = new PlatformerGame(800, 600);
		game.setTitle("LoveHunterXX");
		game.setIconImage(new ImageIcon("resources/LoveHunterXXIcon.png").getImage());
		Thread app = new Thread(game);
		app.start();

	}

}
