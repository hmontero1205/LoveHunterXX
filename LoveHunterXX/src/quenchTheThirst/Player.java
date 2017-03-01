package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Player extends LivingEntity {

	private String direction;

	private HashMap<String, Integer> arsenal;
	private String equipped;

	public Player(int x, int y) {
		super(x, y, .5, "resources/playerright.PNG");

		arsenal = new HashMap<String, Integer>();
		arsenal.put("explosive", 5);
		arsenal.put("alluring", 5);

		equipped = "explosive";

		direction = "east";
	}

	public HashMap<String, Integer> getArsenal() {
		return arsenal;
	}

	public void handle(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_W) {
			move(direction = "north");
			this.loadImages("resources/playerup.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_A) {
			move(direction = "west");
			this.loadImages("resources/playerleft.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			move(direction = "south");
			this.loadImages("resources/playerdown.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_D) {
			move(direction = "east");
			this.loadImages("resources/playerright.PNG", .5);
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
		
		Projectile projectile = null;
		if (proj.equals("explosive")) {
			projectile = new ExplosiveBottle(wbx, wby, direction);
		} else if (proj.equals("alluring")) {
			projectile = new AlluringBottle(wbx, wby, direction);
		}
		
		projectile.setX(projectile.getX() - projectile.getWidth() / 2);
		projectile.setY(projectile.getY() - projectile.getHeight() / 2);

		projectile.start();
		ShooterGame.shooterScreen.spawnEntity(projectile);
	}

	@Override
	public void die() {
		
	}

}