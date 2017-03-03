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
	public List<Obstacle> obstacles;
	private TextLabel scoreLabel;
	private int score;
	private Obstacle obs;
	public int test;
	public ArrayList<Graphic> hearts;

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
	private void appearNewPowerUp(){
		Obstacle power = null;
		double chance = (obstacles.size() > 0) ? Math.log((double) score) : 10000;
		double rand = (Math.random()) * 1000;
		if (rand < chance) {
			switch ((int) (Math.random() * 1)) {
			case 0:
				power = new Power(850, 500, 50, 50, -5, 0, "resources/heart.png", 0);
				break;
			}
		}
		obstacles.add(power);
		addObject(power);
	}
	private void appearNewObstacle() {
		double chance = (obstacles.size() > 0) ? Math.log((double) score) : 10000;
		double rand = (Math.random()) * 1000;
		if (rand < chance) {
			switch ((int) (Math.random() * 3)) {
			case 0:
				obs = new Obstacle(850, 420, 100, 100, -5, 0, "resources/cactus.png");
				obs.setAction(new Action() {
					public void act() {
						if (!player.invuln) {
							PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() - 1);
							PlatformerGame.cs.player.setDamaged(true);
						}
					}
				});
				break;
			case 1:
				obs = new Obstacle(850, 470, 50, 50, -7, 0, "resources/crab.png");
				obs.setAction(new Action() {
					public void act() {
						// PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp()
						// - 1);
						if (!player.invuln) {
							PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() - 1);
							PlatformerGame.cs.player.setDamaged(true);
							int currentScore = PlatformerGame.cs.getScore();
							PlatformerGame.cs.player.setInitialV(0);
							while (PlatformerGame.cs.getScore() < (currentScore + 1)) {
								try {
									Thread.sleep(20);
									update();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
							PlatformerGame.cs.player.setInitialV(9);
						}
					}
				});
				break;
			case 2:
				obs = new Bird(850, 230, 50, 50, -3, 0, "resources/bird1.png");
				obs.setAction(new Action() {
					public void act() {
						if (!player.invuln) {
							PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() - 1);
							PlatformerGame.cs.player.setDamaged(true);
						}
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
		obstacles = Collections.synchronizedList(new ArrayList<Obstacle>());
		hearts = new ArrayList<Graphic>();
		while (player.getHp() > 0) {
			updateHp();
			updateScore();
			appearNewObstacle();
			appearNewPowerUp();
		}
		gameOver();
	}

	private void gameOver() {
		updateHp();
	}

	private void updateHp() {
		int x = 20;

		while (hearts.size() > 0) {
			this.remove(hearts.remove(hearts.size() - 1));
		}

		for (int i = 0; i < player.getHp(); i++) {
			Graphic heart = new Graphic(x, 30, 20, 20, "resources/heart.png");
			hearts.add(heart);
			this.addObject(heart);

			x += 30;
		}

		// xd
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

	public int getScore() {
		return this.score;
	}

}
