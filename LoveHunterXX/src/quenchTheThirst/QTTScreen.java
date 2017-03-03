package quenchTheThirst;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import gui.Screen;
import gui.components.Graphic;
import gui.components.TextLabel;
import gui.components.Visible;

public class QTTScreen extends Screen implements KeyListener {

	private Graphic map;
	private TextLabel explosive;
	private TextLabel alluring;
	private TextLabel health;
	private TextLabel message;
	private Player user;
	private CopyOnWriteArrayList<Entity> entities;
	private Thread ticker;

	public QTTScreen(int width, int height) {
		super(width, height);
	}

	public Player getPlayer() {
		return user;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * Make Map Player Movement update player getx gety Zombie Movement update
	 * zombie getx gety (zombie are in an array list) Bottle Movement update
	 * bottle getx gety Display ammo & health
	 */
	@Override
	public void initObjects(List<Visible> viewObjects) {
		entities = new CopyOnWriteArrayList<Entity>();
		user = new Player(400, 300);
		viewObjects.add(user);

		map = new Graphic(0, 0, 800, 600, "resources/qtt/mapparts/map.png");
		Graphic border1 = new Graphic(0, 0, 800, 75, "resources/qtt/mapparts/border.png");
		Graphic border2 = new Graphic(0, 525, 800, 75, "resources/qtt/mapparts/border.png");

		// returns the int amount for explosive
		int explosiveAmmo = user.getArsenal().get("explosive");
		int alluringAmmo = user.getArsenal().get("alluring");

		health = new TextLabel(600, 20, 130, 50, "HEALTH:" + user.getHealth());
		explosive = new TextLabel(500, 530, 140, 50,
				(user.equipped("explosive") ? "*" : "") + "explosive: " + explosiveAmmo);
		alluring = new TextLabel(680, 530, 140, 50,
				(user.equipped("alluring") ? "*" : "") + "alluring: " + alluringAmmo);
		
		message = new TextLabel(20, 530, 300, 50, "");

		viewObjects.add(map);
		viewObjects.add(border1);
		viewObjects.add(border2);
		viewObjects.add(health);
		viewObjects.add(explosive);
		viewObjects.add(alluring);
		viewObjects.add(message);

		Obstacle longwall = new Obstacle(75, 75, .75, "resources/qtt/mapparts/longwall.png");
		spawnEntity(longwall);

		Obstacle shortwall = new Obstacle(75, 75, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall);

		Obstacle shortwall2 = new Obstacle(75, 325, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall2);

		Obstacle shortwall3 = new Obstacle(655, 75, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall3);

		Obstacle shortwall4 = new Obstacle(655, 325, .75, "resources/qtt/mapparts/shortwall.png");
		spawnEntity(shortwall4);

		Obstacle longwall2 = new Obstacle(73, 495, .76, "resources/qtt/mapparts/longwall.png");
		spawnEntity(longwall2);

		Obstacle bench = new Obstacle(140, 140, .15, "resources/qtt/mapparts/bench.png");
		spawnEntity(bench);

		Obstacle bushes = new Obstacle(200, 250, .3, "resources/qtt/mapparts/bushes.png");
		spawnEntity(bushes);

		Obstacle sandbox = new Obstacle(500, 250, .6, "resources/qtt/mapparts/sandbox.png");
		spawnEntity(sandbox);
		
		spawnEntity(new Enemy(20, 200));
		spawnEntity(new Enemy(20, 100));
		spawnEntity(new Enemy(740, 500));


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

				Iterator<Entity> entIterator = entities.iterator();
				while (entIterator.hasNext()) {
					Entity ent = entIterator.next();
					if (ent instanceof Enemy) {
						count++;
						((Enemy) ent).tick();

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
		if (ticker != null) {
			ticker.interrupt();
		}
	
		message.setText(won ? "You won!" : "You lost!");
		
		/** CHANGE SCREENS HERE **/
	}

	public boolean canPlace(int x, int y, String dir, Entity ent) {
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

		for (Entity e : ShooterGame.shooterScreen.getEntities()) {
			if (e instanceof Projectile || e == ent || e.getClass() == ent.getClass()) {
				continue;
			}

			if (e.collideWith(x, y, ent.getWidth(), ent.getHeight())) {
				return false;
			}
		}

		return true;
	}

	public void spawnEntity(Entity e) {
		entities.add(e);

		this.addObject(e);
	}

	public void kill(Entity e) {
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
