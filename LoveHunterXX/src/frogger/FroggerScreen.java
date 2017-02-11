package frogger;

import java.util.ArrayList;

import gui.Screen;
import gui.components.Visible;

public class FroggerScreen extends Screen {

	public static int sWidth;
	public static int sHeight;
	public static final int WINDOWBARHEIGHT = 32;
	public static final int ROWHEIGHT = 25;
	public static final int ROAD = 1;
	public static final int WATER = 2;
	public static final int SAFEZONE = 3;
	public static Platform pf;
	public static Obstacle ob;
	
	public FroggerScreen(int width, int height) {
		super(width, height);
		sWidth = width;
		sHeight = height;
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		pf = new Platform(0, WINDOWBARHEIGHT, 50, 25, 1, null);
		viewObjects.add(pf);
		pf.play();
		ob = new Obstacle(0, WINDOWBARHEIGHT + 30, 50, 25, 5, "resources/frogger/truck.png");
		viewObjects.add(ob);
		ob.play();
	}

}
