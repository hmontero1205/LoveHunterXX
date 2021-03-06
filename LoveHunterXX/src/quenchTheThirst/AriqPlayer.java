package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import main.LoveHunterXX;

public class AriqPlayer extends AriqLivingEntity {

	private String direction;

	private HashMap<String, Integer> arsenal;
	private String equipped;

	public AriqPlayer(int x, int y) {
		super(x, y, .5, "resources/qtt/playerright.PNG", 5);

		arsenal = new HashMap<String, Integer>();
		arsenal.put("explosive", 10);
		arsenal.put("alluring", 10);

		equipped = "explosive";

		direction = "east";
	}

	public HashMap<String, Integer> getArsenal() {
		return arsenal;
	}

	public void handle(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_W) {
			move(direction = "north");
			this.loadImages("resources/qtt/playerup.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_A) {
			move(direction = "west");
			this.loadImages("resources/qtt/playerleft.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			move(direction = "south");
			this.loadImages("resources/qtt/playerdown.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_D) {
			move(direction = "east");
			this.loadImages("resources/qtt/playerright.PNG", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
			toss(equipped);
		} else if (event.getKeyCode() == KeyEvent.VK_E) {
			equipped = equipped("explosive") ? "alluring" : "explosive";
		}
	}

	public boolean equipped(String weapon) {
		return equipped.equals(weapon);
	}

	public void toss(String proj) {
		int wbx = this.getCenterX();
		int wby = this.getCenterY();

		if (arsenal.get(proj) == 0) {
			return;
		}

		arsenal.put(proj, arsenal.get(proj) - 1);

		BillyProjectile projectile = null;
		if (proj.equals("explosive")) {
			projectile = new BillyExplosiveBottle(wbx, wby, direction);
		} else if (proj.equals("alluring")) {
			projectile = new AriqAlluringBottle(wbx, wby, direction);
		}

		projectile.setX(projectile.getX() - projectile.getWidth() / 2);
		projectile.setY(projectile.getY() - projectile.getHeight() / 2);

		projectile.start();
		LoveHunterXX.qtts.spawnEntity(projectile);
	}

	@Override
	public void die() {
		LoveHunterXX.qtts.endGame(false);
	}

}