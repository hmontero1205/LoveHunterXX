package quenchTheThirst;

import java.awt.Color;
import java.util.List;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Clickable;
import gui.components.Visible;

public class ShooterIntroScreen extends ClickableScreen {

	private Button b;

	public ShooterIntroScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		b = new Button(400, 300, 100, 100, "", Color.white, new Action() {

			@Override
			public void act() {
				ShooterGame.shootGame.setScreen(ShooterGame.shooterScreen);
				System.out.print("asd");
			}
		});

		viewObjects.add(b);

	}

}
