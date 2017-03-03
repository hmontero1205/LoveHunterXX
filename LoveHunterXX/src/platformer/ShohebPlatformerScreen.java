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
import main.LoveHunterXX;

public class ShohebPlatformerScreen extends Screen implements KeyListener, Runnable {

	private Graphic bg;
	public DanielPlayer danielPlayer;
	public List<ShohebCollidable> obstacles;
	private TextLabel scoreLabel;
	private int score;
	private DanielObstacle obs;
	public ArrayList<Graphic> hearts;
	public ShohebUmbrella shohebUmbrella;

	public ShohebPlatformerScreen(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		
		bg = new Graphic(0, 0, 800, 600, "resources/platformerbg.png");
		viewObjects.add(bg);

		scoreLabel = new TextLabel(20, 40, 80, 40, "0");
		viewObjects.add(scoreLabel);

		danielPlayer = new DanielPlayer(10, 370, 100, 150, "resources/player.png");
		danielPlayer.play();
		viewObjects.add(danielPlayer);
		
		shohebUmbrella = new ShohebUmbrella(0, 0, 130, 150, "resources/umbrellaclosed.png");
		viewObjects.add(shohebUmbrella);
	}
	private void appearNewPowerUp(){
		
		double chance = (obstacles.size() > 0) ? Math.log((double) score) : 10000;
		double rand = ((Math.random()) * 1000);
		if (rand < chance) {
			ShohebPower shohebPower = null;
			switch (0) {
			case 0:
				shohebPower = new ShohebPower(850, 420, 50, 50, -5, 0, "resources/heart.png", 0);
				break;
			}
			obstacles.add(shohebPower);
			addObject(shohebPower);
		}
		
	}
	private void appearNewObstacle() {
		double chance = (obstacles.size() > 0) ? Math.log((double) score) : 10000;
		double rand = ((Math.random()) * 1000)-(score/100);
		if (rand < chance) {
			switch ((int) (Math.random() * 3)) {
			case 0:
				obs = new DanielObstacle(850, 420, 100, 100, -5, 0, "resources/cactus.png");
				obs.setAction(new Action() {
					public void act() {
						if (!danielPlayer.invuln) {
							LoveHunterXX.ps.danielPlayer.setHp(LoveHunterXX.ps.danielPlayer.getHp() - 1);
							LoveHunterXX.ps.danielPlayer.setDamaged(true);
						}
					}
				});
				break;
			case 1:
				obs = new DanielObstacle(850, 470, 50, 50, -7, 0, "resources/crab.png");
				obs.setAction(new Action() {
					public void act() {
						if (!danielPlayer.invuln) {
							LoveHunterXX.ps.danielPlayer.setHp(LoveHunterXX.ps.danielPlayer.getHp() - 1);
							LoveHunterXX.ps.danielPlayer.setDamaged(true);
							int currentScore = LoveHunterXX.ps.getScore();
							LoveHunterXX.ps.danielPlayer.setInitialV(1);
							while (LoveHunterXX.ps.getScore() < (currentScore + 1)) {
								try {
									Thread.sleep(20);
									update();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
							LoveHunterXX.ps.danielPlayer.setInitialV(9);
						}
					}
				});
				break;
			case 2:
				obs = new DanielBird(850, 230, 50, 50, -3, 0, "resources/bird1.png");
				obs.setAction(new Action() {
					public void act() {
						if (!danielPlayer.invuln) {
							LoveHunterXX.ps.danielPlayer.setHp(LoveHunterXX.ps.danielPlayer.getHp() - 1);
							LoveHunterXX.ps.danielPlayer.setDamaged(true);
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
			if (!danielPlayer.getJump()) {
				danielPlayer.setJump(true);
			}
		}
		if(e.getKeyCode() == 38) {
			shohebUmbrella.setActive(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38){
			shohebUmbrella.setActive(false);
			LoveHunterXX.ps.danielPlayer.setInitialV(9);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		obstacles = Collections.synchronizedList(new ArrayList<ShohebCollidable>());
		hearts = new ArrayList<Graphic>();
		while (danielPlayer.getHp() > 0) {
			updateHp();
			updateScore();
			appearNewObstacle();
			appearNewPowerUp();
		}
		gameOver();
	}

	private void gameOver() {
		updateHp();
		danielPlayer.setRunning(false);
		shohebUmbrella.setRunning(false);
		TextLabel goverLabel = new TextLabel(20, 40, 80, 40, "Game Over");
		addObject(goverLabel);
	}

	private void updateHp() {
		int x = 20;

		while (hearts.size() > 0) {
			this.remove(hearts.remove(hearts.size() - 1));
		}

		for (int i = 0; i < danielPlayer.getHp(); i++) {
			Graphic heart = new Graphic(x, 40, 20, 20, "resources/heart.png");
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
