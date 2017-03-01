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
	private Graphic introBack;

	public ShooterIntroScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		introBack = new Grapic(0,0,800,600,"resource/introBack.PNG");
		b = new Button(350, 250, 100, 100, "Click Here To Continue", Color.white, new Action() {

			@Override
			public void act() {
				ShooterGame.shootGame.setScreen(ShooterGame.shooterScreen);//explain this
			}
		});
		
		viewObjects.add(introBack);
		viewObjects.add(b);

	}

}
