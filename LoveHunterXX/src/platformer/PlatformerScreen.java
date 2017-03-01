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
		/*trying to do a thing where the image of the player changes when they get hit and they are invulnerable for a 
		 * second... can you finish the job on this one? 
		 * also changed the effect of the crab to temporarily root the player... */
		
		//Tried to do the invuln, but images are changing funny. player loses its transparency for some reason. 
		int chance = (obstacles.size() > 0) ? (int) obstacles.get(obstacles.size() - 1).getPosx() : 0;
		int x1 = (int) Math.floor(Math.random() * 400);
		if (x1 > chance) {
			switch ((int) (Math.random() * 2)) {
			case 0:
				obs = new Obstacle(850, 420, 100, 100, -5, "resources/cactus.png");
				obs.setAction(new Action() {
					public void act() {
						PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp() - 1);
						PlatformerGame.cs.player.setDamaged(true);
						
						System.out.println(PlatformerGame.cs.player.getHp());
					}
				});
				break;
			case 1:
				obs = new Obstacle(850, 470, 50, 50, -7, "resources/crab.png");
				obs.setAction(new Action() {
					public void act() {
						// PlatformerGame.cs.player.setHp(PlatformerGame.cs.player.getHp()
						// - 1);
						int currentScore = PlatformerGame.cs.getScore();
						PlatformerGame.cs.player.setInitialV(0);
						while(PlatformerGame.cs.getScore() < (currentScore+5)){
							try {
								Thread.sleep(20);
								update();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						PlatformerGame.cs.player.setInitialV(9);
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
		while (player.getHp() > 0) {
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
	
	public int getScore(){
		return this.score;
	}

}
