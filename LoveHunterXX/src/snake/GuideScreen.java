package snake;

import java.awt.Color;
import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.Visible;

public class GuideScreen extends ClickableScreen {

	public GuideScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// TODO Auto-generated method stub
		
		// Background image.
		Graphic background = new Graphic(0, 0, "resources/ShoppingSpree.png");
		
		// button to be clicked to transition into the next screen.
		Button button = new Button(400, 400, 100, 100, "Continue.", Color.BLACK, new Action() {
			
			@Override
			public void act() {
				// TODO Auto-generated method stub
				System.out.println("Let's continue!");
				
			}
		});
		
		// add items to the view list.
		viewObjects.add(background);
		viewObjects.add(button);
		

	}

}
