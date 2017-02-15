package platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class PlatformerScreen extends Screen implements KeyListener, Runnable {

	private Graphic bg;
	private Player player;
	private ArrayList<Obstacle> obstacles;
	private Obstacle obs;

	public PlatformerScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		bg = new Graphic(0, 0, 800, 600, "resources/platformerbg.png");
		viewObjects.add(bg);
		player = new Player(10, 370, 100, 150, "resources/player.png");
		player.play();
		viewObjects.add(player);	
	}

	private void appearNewObstacle() {
		obstacles = new ArrayList<Obstacle>();
		while (true) {
			try {
				Thread.sleep(2000);
				obs = new Obstacle(500, 370, 100, 100, -5, "resources/cactus.png");
				obstacles.add(obs);
				viewObjects.add(obs);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public KeyListener getKeyListener() {
		return this;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 32) {
			if(!player.getJump()){
				player.setJump(true);
			}
		}
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
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
