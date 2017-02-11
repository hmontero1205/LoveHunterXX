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
		initObjects(getViewObjects());
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
//		testing
		if(sWidth != 0) {
			pf = new Platform(0, WINDOWBARHEIGHT, 50, 25, 1, null);
			viewObjects.add(pf);
			pf.play();
			
			ob = new Obstacle(0, WINDOWBARHEIGHT + ROWHEIGHT + 5, 50, 25, 3, "resources/frogger/truck.png");
			viewObjects.add(ob);
			ob.play();
			
			pf = new Platform(getWidth(), WINDOWBARHEIGHT + ROWHEIGHT*2 + 5, 50, 25, -1, null);
			viewObjects.add(pf);
			pf.play();
			
			ob = new Obstacle(getWidth(), WINDOWBARHEIGHT + ROWHEIGHT*3 + 5, 50, 25, -3, "resources/frogger/truck.png");
			viewObjects.add(ob);
			ob.play();
		}
	}

}
