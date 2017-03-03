package snake;
/*
 *  
 * */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import gui.Screen;
import gui.components.Graphic;
import gui.components.TextArea;
import gui.components.Visible;

public class MichaelSnakeScreen extends Screen implements KeyListener {

	public final static int LEFTARROWKEY = 37;
	public final static int UPARROWKEY = 38;
	public final static int RIGHTARROWKEY = 39;
	public final static int DOWNARROWKEY = 40;

	public MichaelSnake snake;
	public static List<DavidGeneratableInterface> gens; // generated objects.
	Graphic background;
	Graphic sbBack;
	TextArea line;
	TextArea scoreCount;
	Graphic cBack;
	TextArea cLine;

	public MichaelSnakeScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		MichaelSnake.Direction direction = snake.getDirection();

		switch (keyCode) {
		case LEFTARROWKEY:
			if (direction != MichaelSnake.Direction.right && direction != MichaelSnake.Direction.left) {
				snake.moveCoors(MichaelSnake.Direction.left);
			}
			break;
		case UPARROWKEY:
			if (direction != MichaelSnake.Direction.down && direction != MichaelSnake.Direction.up) {
				snake.moveCoors(MichaelSnake.Direction.up);
			}
			break;
		case RIGHTARROWKEY:
			if (direction != MichaelSnake.Direction.left && direction != MichaelSnake.Direction.right) {
				snake.moveCoors(MichaelSnake.Direction.right);
			}
			break;
		case DOWNARROWKEY:
			if (direction != MichaelSnake.Direction.up && direction != MichaelSnake.Direction.down) {
				snake.moveCoors(MichaelSnake.Direction.down);
			}
			break;
		}
	}

	public KeyListener getKeyListener() {
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

	public void updateScore() {
		scoreCount.clear();
		scoreCount.setText(snake.presentList.size() - 1 + "");
	}

	public void updateText(String s) {
		cLine.clear();
		cLine.setText(s);
	}

	@Override
	public void initObjects(List<Visible> view) {
		// TODO Auto-generated method stub
		gens = new ArrayList<DavidGeneratableInterface>();
		snake = new MichaelSnake(0, 0, 100, 100);
		background = new Graphic(10, 40, 450, 450, "resources/snakebackground.jpg");
		sbBack = new Graphic(465, 40, 320, 150, "resources/scorebackground.png");
		line = new TextArea(525, 50, 300, 200, "You have collected:");
		scoreCount = new TextArea(600, 75, 250, 200, "");
		scoreCount.setSize(46);
		scoreCount.setText("" + (snake.presentList.size() - 1));

		cBack = new Graphic(465, 195, 320, 295, "resources/scorebackground.png");
		cLine = new TextArea(475, 205, 310, 295, "          Shopping Spree!");

		view.add(background);
		view.add(sbBack);
		view.add(line);
		view.add(scoreCount);
		view.add(cBack);
		view.add(cLine);
		for (MichaelInteractable i : snake.getItems()) {
			// add each snake body part.
			view.add(i);
		}

		DavidLoveGift p = new DavidLoveGift(50, 60, 30, 30);
		gens.add(p);
		view.add(p);
	}

}
