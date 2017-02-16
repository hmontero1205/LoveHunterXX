package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.Visible;

public class IntroScreen extends ClickableScreen implements MouseListener {
	
	private Button button;
	private Graphic background;
	
	public IntroScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		button = new Button(350,100,100,50, "Start!", Color.LIGHT_GRAY, new Action() {
			@Override
			public void act() {
				// TODO Auto-generated method stub
				System.out.println("Go");
				SnakeGame.sGame.setScreen(SnakeGame.gScreen);
				
			}
		});
		
		background = new Graphic(10,0,790,495,"resources/ShoppingSpree.png");
		viewObjects.add(background);
		viewObjects.add(button);
	}
}
