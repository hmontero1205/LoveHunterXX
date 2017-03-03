package quenchTheThirst;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.TextLabel;
import gui.components.Visible;

public class BillyQTTScreen extends ClickableScreen implements KeyListener {

	private Button b;
	private Graphic map;
	private TextLabel explosive;
	private TextLabel alluring;
	private TextLabel health;
	private TextLabel message;
	private AriqPlayer user;
	private CopyOnWriteArrayList<KevinEntity> entities;
	private Thread ticker;

	public BillyQTTScreen(int width, int height) {
		super(width, height);
	}

	public AriqPlayer getPlayer() {
		return user;
	}

	public List<KevinEntity> getEntities() {
		return entities;
	}

	/**
	 * Make Map Player Movement update player getx gety Zombie Movement update
	 * zombie getx gety (zombie are in an array list) Bottle Movement update
	 * bottle getx gety Display ammo & health
	 */
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		entities = new CopyOnWriteArrayList<KevinEntity>();
		user = new AriqPlayer(400, 300);
		viewObjects.add(user);

		map = new Graphic(0, 0, 800, 600, "resources/qtt/mapparts/map.png");
		Graphic border1 = new Graphic(0, 0, 800, 75, "resources/qtt/mapparts/border.png");
		Graphic border2 = new Graphic(0, 525, 800, 75, "resources/qtt/mapparts/border.png");
		
		b = new Button(10, 30, 150, 30, "Cheat", Color.pink, new Action() {

			@Override
			public void act() {
				endGame(true);
			}

		});
		viewObjects.add(b);

		int explosiveAmmo = user.getArsenal().get("explosive");
		int alluringAmmo = user.getArsenal().get("alluring");

		health = new TextLabel(600, 20, 130, 50, "HEALTH:" + user.getHealth());
		explosive = new TextLabel(500, 530, 140, 50,
				(user.equipped("explosive") ? "*" : "") + "Explosive: " + explosiveAmmo);
		alluring = new TextLabel(680, 530, 140, 50,
				(user.equipped("alluring") ? "*" : "") + "Alluring: " + alluringAmmo);
		
		message = new TextLabel(20, 530, 300, 50, "");

		viewObjects.add(map);
		viewObjects.add(border1);
		viewObjects.add(border2);
		viewObjects.add(health);
		viewObjects.add(explosive);
		viewObjects.add(alluring);
		viewObjects.add(message);

		KevinObstacle longwall = new KevinObstacle(75, 75, .75, "resources/qtt/mapparts/longwall.png");
		spawnEntity(longwall);

		KevinObstacle shortwall = new KevinObstacle(75, 75, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall);

		KevinObstacle shortwall2 = new KevinObstacle(75, 325, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall2);

		KevinObstacle shortwall3 = new KevinObstacle(655, 75, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall3);

		KevinObstacle shortwall4 = new KevinObstacle(655, 325, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall4);

		KevinObstacle longwall2 = new KevinObstacle(73, 495, .76, "resources/qtt/mapparts/longwall.png");
		spawnEntity(longwall2);

		KevinObstacle bench = new KevinObstacle(140, 140, .15, "resources/qtt/mapparts/bench.png");
		spawnEntity(bench);

		KevinObstacle bushes = new KevinObstacle(200, 250, .3, "resources/qtt/mapparts/bushes.png");
		spawnEntity(bushes);

                
		
		KevinObstacle sandbox = new KevinObstacle(500, 240, .8, "resources/qtt/mapparts/sandbox.png");
		spawnEntity(sandbox);
		
		KevinObstacle slide = new KevinObstacle(300, 400, .3, "resources/qtt/mapparts/slide.png");
		spawnEntity(slide);
		
		KevinObstacle trash = new KevinObstacle(380, 160, .05, "resources/qtt/mapparts/shoheb.png");
		spawnEntity(trash);
		
		KevinObstacle tree = new KevinObstacle(500, 390, .02, "resources/qtt/mapparts/trees.png");
		spawnEntity(tree);
		
		
		
		spawnEntity(new KevinEnemy(20, 200));
		spawnEntity(new KevinEnemy(20, 100));
		spawnEntity(new KevinEnemy(740, 500));


		moveToFront(b);
		moveToFront(user);
	}

	@Override
	public void onDisplay() {
		tick();
	}

	public void tick() {
		ticker = new Thread(() -> {
			while (true) {
				int count = 0;

				Iterator<KevinEntity> entIterator = entities.iterator();
				while (entIterator.hasNext()) {
					KevinEntity ent = entIterator.next();
					if (ent instanceof KevinEnemy) {
						count++;
						((KevinEnemy) ent).tick();

						if (ent.collideWith(user)) {
							user.damage(1);
							System.out.println(user.getHealth());
							health.setText("Health: " + user.getHealth());
						}
					}
				}

				if (count == 0) {
					endGame(true);
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					break;
				}
			}
		});
		ticker.start();
	}

	public void endGame(boolean won) {
		remove(b);
		if (ticker != null) {
			ticker.interrupt();
		}
	
		message.setText(won ? "You won!" : "You lost!");
		
		/** CHANGE SCREENS HERE **/
	}

	public boolean canPlace(int x, int y, String dir, KevinEntity ent) {
		switch (dir) {
		case "north":
			y -= ent.getSpeed();
			break;
		case "east":
			x += ent.getSpeed();
			break;
		case "south":
			y += ent.getSpeed();
			break;
		case "west":
			x -= ent.getSpeed();
			break;
		default:
			return false;
		}

		for (KevinEntity e : AriqShooterGame.shooterScreen.getEntities()) {
			if (e instanceof BillyProjectile || e == ent || e.getClass() == ent.getClass()) {
				continue;
			}

			if (e.collideWith(x, y, ent.getWidth(), ent.getHeight())) {
				return false;
			}
		}

		return true;
	}

	public void spawnEntity(KevinEntity e) {
		entities.add(e);

		this.addObject(e);
	}

	public void kill(KevinEntity e) {
		entities.remove(e);

		this.remove(e);
	}

	@Override
	public KeyListener getKeyListener() {
		return this;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		user.handle(event);

		int explosiveAmmo = user.getArsenal().get("explosive");
		int alluringAmmo = user.getArsenal().get("alluring");
		explosive.setText((user.equipped("explosive") ? "*" : "") + "explosive: " + explosiveAmmo);
		alluring.setText((user.equipped("alluring") ? "*" : "") + "alluring: " + alluringAmmo);
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}
}
