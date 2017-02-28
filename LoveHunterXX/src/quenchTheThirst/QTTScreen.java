package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Graphic;
import gui.components.TextLabel;
import gui.components.Visible;

public class QTTScreen extends Screen implements KeyListener {

	private Graphic map;
	private TextLabel explosive;
	private TextLabel alluring;
	private TextLabel health;
	private Player user;
	private ArrayList<Entity> entities;

	public QTTScreen(int width, int height) {
		super(width, height);
	}

	/**
	 * Make Map Player Movement update player getx gety Zombie Movement update
	 * zombie getx gety (zombie are in an array list) Bottle Movement update
	 * bottle getx gety Display ammo & health
	 */
	@Override
	public void initObjects(List<Visible> viewObjects) {
		entities = new ArrayList<Entity>();
		user = new Player(400, 300);

		// returns the int amount for explosive
		int explosiveAmmo = user.getArsenal().get("explosive");
		int alluringAmmo = user.getArsenal().get("alluring");

		health = new TextLabel(600, 20, 130, 50, "HEALTH:" + user.getHealth());
		explosive = new TextLabel(500, 530, 140, 50, (user.equipped("explosive") ? "*" : "") + "explosive: " + explosiveAmmo);
		alluring = new TextLabel(680, 530, 140, 50, (user.equipped("alluring") ? "*" : "") + "alluring: " + alluringAmmo);

		map = new Graphic(0, 0, 800, 600, "resources/mapparts/map.png");
		viewObjects.add(map);
		viewObjects.add(health);
		viewObjects.add(explosive);
		viewObjects.add(alluring);
		
		Obstacle longwall = new Obstacle(75,75,.75, "resources/mapparts/longwall.png");
		viewObjects.add(longwall);
		
		Obstacle shortwall = new Obstacle(75,75,.75, "resources/mapparts/shortwall.png");
		viewObjects.add(shortwall);
	
		Obstacle shortwall2 = new Obstacle(75,325,.75, "resources/mapparts/shortwall.png");
		viewObjects.add(shortwall2);
		
		
		Obstacle shortwall3 = new Obstacle(655,75,.75, "resources/mapparts/shortwall.png");
		viewObjects.add(shortwall3);
		
		Obstacle shortwall4 = new Obstacle(655,325,.75, "resources/mapparts/shortwall.png");
		viewObjects.add(shortwall4);
		
		Obstacle longwall2 = new Obstacle(73,495,.76, "resources/mapparts/longwall.png");
		viewObjects.add(longwall2);
		
		Obstacle bench = new Obstacle(140,140,.15, "resources/mapparts/bench.png");
		viewObjects.add(bench);
		
		Obstacle bushes = new Obstacle(200,250,.3, "resources/mapparts/bushes.png");
		viewObjects.add(bushes);
		viewObjects.add(user);
		moveToFront(user);
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
