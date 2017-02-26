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
	
	public final static int LEFTARROWKEY = 37;
	public final static int UPARROWKEY = 38;
	public final static int RIGHTARROWKEY = 39;
	public final static int DOWNARROWKEY = 40;
	
	/*public final static int LEFT = 0;
	public final static int UP = 1;
	public final static int RIGHT = 2;
	public final static int DOWN = 3;*/

	public Snake snake;
	
	public SnakeScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		Snake.Direction direction = snake.getDirection();
		
		switch(keyCode){
		case LEFTARROWKEY:
			if(direction != Snake.Direction.right && direction != Snake.Direction.left){
				snake.moveCoors(Snake.Direction.left);
			}
			break;
		case UPARROWKEY:
			if(direction != Snake.Direction.down && direction != Snake.Direction.up){
				snake.moveCoors(Snake.Direction.up);
			}
			break;
		case RIGHTARROWKEY:
			if(direction != Snake.Direction.left && direction != Snake.Direction.right){
				snake.moveCoors(Snake.Direction.right);
			}
			break;
		case DOWNARROWKEY:
			if(direction != Snake.Direction.up && direction != Snake.Direction.down){
				snake.moveCoors(Snake.Direction.down);
			}
			break;//
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
		for(Interactable i: snake.getItems()){ // add each snake body part.
			view.add(i);
		}
		
	}
	


}
