package frogger;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.GUIApplication;
import main.LoveHunterXX;

public class HansFroggerGame extends GUIApplication {

	public static HansFroggerGame game;
	public static HansFroggerScreen fs;

	public HansFroggerGame(int width, int height) {
		super(width, height);
		setResizable(false);
	}

	@Override
	public void initScreen() {
		fs = new HansFroggerScreen(800, 600);
		setScreen(fs);
	}

	public static void main(String[] args) {
		game = new HansFroggerGame(800, 600);
		game.setTitle("LoveHunterXX");
		game.setIconImage(new ImageIcon("resources/LoveHunterXXIcon.png").getImage());
		Thread go = new Thread(game);
		go.start();

	}

}
