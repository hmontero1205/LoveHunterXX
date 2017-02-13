package snake;

import java.awt.Color;
import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.TextArea;
import gui.components.Visible;

public class GuideScreen extends ClickableScreen {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	public GuideScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		// Background image.
		Graphic background = new Graphic(0, 0, 800, 600, "resources/guidebackground.jpg");
		
		// button to be clicked to transition into the next screen.
		Button button = new Button((WIDTH/2) - (100/2), (HEIGHT/2) - (100/2), 100, 60, "Continue", Color.BLACK, new Action() {
			
			@Override
			public void act() { // transition will occur here.
				System.out.println("Let's continue!");
				
			}
		});
		
		// text with all the instructions.
		TextArea guideBox = new TextArea(100, 100, 200, 200, "Instructions:");
		
		// add items to the view list.
		viewObjects.add(background);
		viewObjects.add(button);
		viewObjects.add(guideBox);
		

	}

}
