package quenchTheThirst;

import java.util.ArrayList;

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class QttScreen extends Screen {
	
	private Graphic map;

	public QttScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		map = new Graphic(0,0,"resources/tempmap");
		
		viewObjects.add(map);
	}

}
