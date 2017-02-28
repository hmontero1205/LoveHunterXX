package platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Screen;
import gui.components.Action;
import gui.components.Graphic;
import gui.components.TextLabel;
import gui.components.Visible;

public class PlatformerScreen extends Screen implements KeyListener, Runnable {

	private Graphic bg;
	public Player player;
	public ArrayList<Obstacle> obstacles;
	private TextLabel scoreLabel;
	private int score;
	private Obstacle obs;
	public int test;

	public PlatformerScreen(int width, int height) {
		super(width, height);
		test = 5;
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		bg = new Graphic(0, 0, 800, 600, "resources/platformerbg.png");
		viewObjects.add(bg);
		
		scoreLabel = new TextLabel(50, 40, 80, 40, "0");
		viewObjects.add(scoreLabel);
		
		player = new Player(10, 370, 100, 150, "resources/player.png");
		player.play();
		viewObjects.add(player);
	}

	private void appearNewObstacle() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 * Problem was kind of fixed by adding an Obstacle field instead of it being local.
		 * Problem was due to the statement making an Obstacle, but the constructor has a play() so that the thread runs
		 * despite not being added to viewObjects yet
		 */
		int chance = (obstacles.size() > 0) ? (int) obstacles.get(obstacles.size() - 1).getPosx() : 0;
		int x1 = (int) Math.floor(Math.random() * 400);
		if (x1 > chance) {
			switch ((int) (Math.random() * 2)) {
			case 0:
				obs = new Obstacle(850, 420, 100, 100, -5, "resources/cactus.png");
				obs.setAction(new Action() {
					public void act() {
						
						PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() - 1);
					}
				});
				break;
			case 1:
				obs = new Obstacle(850, 470, 50, 50, -7, "resources/crab.png");
				obs.setAction(new Action() {
					public void act() {
						PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() - 1);
					}
				});
				break;
			}
			obstacles.add(obs);
			addObject(obs);
			
			
		}
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
		while (player.getHp() >= 0) {
			updateScore();
			appearNewObstacle();
		}
	}

	private void updateScore() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		score += 1;
		scoreLabel.setText("" + score);

	}

}
