package platformer;

import java.util.ArrayList; 

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class PlatformerScreen extends Screen {

	private Graphic bg;
	private Player player;

	public PlatformerScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		bg = new Graphic(0, 0, 800, 600, "resources/platformerbg.PNG");
		viewObjects.add(bg);
		player = new Player(10, 300, 20, 20);
	}

}
