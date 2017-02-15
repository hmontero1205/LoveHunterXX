package platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Screen;
import gui.components.Action;
import gui.components.Graphic;
import gui.components.Visible;

public class PlatformerScreen extends Screen implements KeyListener, Runnable {

	private Graphic bg;
	public Player player;
	public ArrayList<Obstacle> obstacles;

	public PlatformerScreen(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
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
		Obstacle obs = new Obstacle(850, 420, 100, 100, -5, "resources/cactus.png");
		obs.setAction(new Action(){
			public void act(){
				player.setHp(player.getHp()-1);
				System.out.println(player.getHp());
			}
		});
		obstacles.add(obs);
		addObject(obs);
	}

	public KeyListener getKeyListener() {
		return this;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 32) {
			if (!player.getJump()) {
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
		obstacles = new ArrayList<Obstacle>();
		while (true) {
			try {
				Thread.sleep(2000);
				appearNewObstacle();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
