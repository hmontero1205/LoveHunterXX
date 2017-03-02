package frogger;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.GUIApplication;
import main.LoveHunterXX;

public class FroggerGame extends GUIApplication {

	public static FroggerGame game;
	public static FroggerScreen fs;

	public FroggerGame(int width, int height) {
		super(width, height);
		setResizable(true);
	}

	@Override
	public void initScreen() {
		fs = new FroggerScreen(800, 600);
		setScreen(fs);
	}

	public static void main(String[] args) {
		game = new FroggerGame(800, 600);
		game.setTitle("LoveHunterXX");
		game.setIconImage(new ImageIcon("resources/LoveHunterXXIcon.png").getImage());
		Thread go = new Thread(game);
		go.start();

	}

}
