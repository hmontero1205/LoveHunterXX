package platformer;

import java.awt.Color;
import java.util.List;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.Visible;
import main.LoveHunterXX;

public class ShohebPlatformerIntro extends ClickableScreen {
	private Graphic img;
	private Button proceedButton;
	private int choice;
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		img = new Graphic(0, 0, 800, 600, "resources/qtt/lwb.png");
		proceedButton = new Button(325, 425, 150, 80, "Continue", Color.pink, new Action() {

			@Override
			public void act() {
				if (choice == 0) {
					img.loadImages("resources/lwbhtp.png", 800, 600);
				} else if (choice == 1) {
					LoveHunterXX.game.setScreen(LoveHunterXX.qtts);
				}

				choice++;
			}

		});

		viewObjects.add(img);
		viewObjects.add(proceedButton);

	}

	}

}
