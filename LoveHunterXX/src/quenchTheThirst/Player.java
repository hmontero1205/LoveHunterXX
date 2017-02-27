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
			this.setY(this.getY() - 5);
			direction = "north";
			this.loadImages("resources/playerup.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_A) {
			this.setX(this.getX() - 5);
			direction = "west";
			this.loadImages("resources/playerleft.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			this.setY(this.getY() + 5);
			direction = "south";
			this.loadImages("resources/playerdown.png", .5);
		} else if (event.getKeyCode() == KeyEvent.VK_D) {
			this.setX(this.getX() + 5);
			direction = "east";
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
		int wbx = this.getX();
		int wby = this.getY();

		if (arsenal.get(proj) == 0) {
			return;
		}
		
		Projectile projectile = null;
		if (proj.equals("explosive")) {
			projectile = new ExplosiveBottle(wbx, wby, direction);
		} else if (proj.equals("alluring")) {
			projectile = new AlluringBottle(wbx, wby, direction);
		}

		projectile.start();
		ShooterGame.shooterScreen.spawnEntity(projectile);;
	}

}