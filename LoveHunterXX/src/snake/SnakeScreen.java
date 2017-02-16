package snake;
/*
 *  
 * */

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class SnakeScreen extends Screen implements KeyListener {
	//private ArrayList<> snakeParts;

	public SnakeScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	
		//

	}
	
	public KeyListener getKeyListener(){
		return this;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initObjects(List<Visible> view) {
		// TODO Auto-generated method stub
		Graphic background = new Graphic(0, 0, 800, 600, "resources/snakebackground.png");
		Snake snake = new Snake(1, 1, 100, 100);
		
		
		
		view.add(background);
		view.add(snake);
		
	}
	


}
