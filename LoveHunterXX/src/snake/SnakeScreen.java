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
import gui.components.TextArea;
import gui.components.Visible;

public class SnakeScreen extends Screen implements KeyListener {
	
	public final static int LEFTARROWKEY = 37;
	public final static int UPARROWKEY = 38;
	public final static int RIGHTARROWKEY = 39;
	public final static int DOWNARROWKEY = 40;


	public Snake snake;
	public static List<GeneratableInterface> gens; // generated objects.
	Graphic background;
	Graphic sbBack;
	TextArea line;
	TextArea scoreCount;
	Graphic cBack;
	TextArea cLine;
	
	public SnakeScreen(int width, int height) {
		super(width, height);
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
	
	public void updateScore(){
		scoreCount.clear();
		scoreCount.setText(snake.presentList.size() - 1  + "");
	}

	@Override
	public void initObjects(List<Visible> view) {
		// TODO Auto-generated method stub
		gens = new ArrayList<GeneratableInterface>();
		snake = new Snake(0, 0, 100, 100);
		background = new Graphic(10, 40, 450,450, "resources/snakebackground.jpg");
		sbBack = new Graphic(465,40,320,150,"resources/scorebackground.png");
		line = new TextArea(525,50,300,200, "You have collected:");
		scoreCount = new TextArea(600,75,250,200,"");
		scoreCount.setSize(46);
		scoreCount.setText("" + (snake.presentList.size()-1));
		
		cBack = new Graphic(465,195,320,295,"resources/scorebackground.png");
		cLine = new TextArea(500,205,320,295,"Win/Lose Text Here Please");
		
		view.add(background);
		view.add(sbBack);
		view.add(line);
		view.add(scoreCount);
		view.add(cBack);
		view.add(cLine);
		for(Interactable i: snake.getItems()){ // add each snake body part.
			view.add(i);
		}
		
		LoveGift p = new LoveGift(50 , 60, 30, 30);
		Obstacle o = new Obstacle(70, 120, 30, 30);
		gens.add(p);
		gens.add(o);
		view.add(p);
		view.add(o);
	}
	


}
