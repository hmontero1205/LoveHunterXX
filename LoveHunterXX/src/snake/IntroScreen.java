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
	
	private Button buttonToGuide;
	private Button buttonToGame;
	private Graphic splashArt;
	private Graphic howToPlay;
	private int clickCount =0;
	public IntroScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		buttonToGuide = new Button(340,100,120,50, "Instructions", Color.LIGHT_GRAY, new Action() {
			@Override
			public void act() {
				// TODO Auto-generated method stub
				remove(splashArt);
				remove(buttonToGuide);
				clickCount++;
				update();
			}
		});
		
		buttonToGame = new Button(350,400,100,50, "Start!", Color.LIGHT_GRAY, new Action() {
			@Override
			public void act() {
				// TODO Auto-generated method stub
				System.out.println("Go");
				SnakeGame.sGame.setScreen(SnakeGame.sScreen);
				SnakeGame.sScreen.snake.play();
			}
		});
		
		howToPlay = new Graphic(0,0,800,495,"resources/howToPlay.png");
		splashArt = new Graphic(0,0,800,495,"resources/ShoppingSpree.png");
		
		viewObjects.add(howToPlay);
		viewObjects.add(buttonToGame);
		viewObjects.add(splashArt);
		viewObjects.add(buttonToGuide);
	}
}
