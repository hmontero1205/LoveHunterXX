package platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class PlatformerScreen extends Screen implements KeyListener {

	private Graphic bg;
	private Player player;

	public PlatformerScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		bg = new Graphic(0, 0, 800, 600, "resources/platformerbg.png");
		viewObjects.add(bg);
		player = new Player(10, 370, 100, 150, "resources/player.png");
		player.play();
		viewObjects.add(player);
		// addObjects(viewObjects);	
		}

	private void addObjects(ArrayList<Visible> viewObjects) {
		while (true) {
			try {
				Thread.sleep(2000);
				Obstacle obstacle = new Obstacle(500, 370, 100, 100, 10, "resources/cactus.png");
				viewObjects.add(obstacle);
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

}
