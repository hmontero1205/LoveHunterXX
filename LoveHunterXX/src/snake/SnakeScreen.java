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
	public final static int LEFTARROWKEY = 37;
	public final static int UPARROWKEY = 38;
	public final static int RIGHTARROWKEY = 39;
	public final static int DOWNARROWKEY = 40;
	
	public final static int LEFT = 0;
	public final static int UP = 1;
	public final static int RIGHT = 2;
	public final static int DOWN = 3;

	public Snake snake;
	
	public SnakeScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case LEFTARROWKEY:
			snake.moveCoors(LEFT);
			break;
		case UPARROWKEY:
			snake.moveCoors(UP);
			break;
		case RIGHTARROWKEY:
			snake.moveCoors(RIGHT);
			break;
		case DOWNARROWKEY:
			snake.moveCoors(DOWN);
			break;
		}
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

		Graphic background = new Graphic(10, 40, 450,450, "resources/snakebackground.png");
		snake = new Snake(0, 0, 100, 100);

		view.add(background);
		for(Interactable i: snake.getItems()){
			//System.out.println(i);
			view.add(i);
		}
		
	}
	


}
