package quenchTheThirst;

import java.awt.Color;
import java.util.List;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Clickable;
import gui.components.Graphic;
import gui.components.Visible;
import main.LoveHunterXX;

public class BillyShooterIntroScreen extends ClickableScreen {

	private Button b;
	private Graphic background;
	private int sequence;

	public BillyShooterIntroScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		background = new Graphic(0, 0, 800, 600, "resources/qtt/introback.png");
		b = new Button(325, 425, 150, 80, "Continue", Color.pink, new Action() {

			@Override
			public void act() {
				if (sequence == 0) {
					background.loadImages("resources/qtt/howtoplay.png", 800, 600);
				} else if (sequence == 1) {
					LoveHunterXX.game.setScreen(LoveHunterXX.qtts);
				}

				sequence++;
			}

		});

		viewObjects.add(background);
		viewObjects.add(b);

	}

}
