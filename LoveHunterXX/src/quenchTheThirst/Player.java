package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Player extends LivingEntity {

	private String direction;

	private HashMap<String, Integer> arsenal;

	public Player(int x, int y) {
		super(x, y, .2, "resources/player.PNG");

		arsenal = new HashMap<String, Integer>();
		arsenal.put("explosive", 5);
		arsenal.put("alluring", 5);
		
		direction = "east";
	}

	public HashMap<String, Integer> getArsenal() {
		return arsenal;
	}

	public void handle(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_W) {
			this.setY(this.getY() - 4);
			direction = "north";
			this.loadImages("resources/playerN.png", .2);
		} else if (event.getKeyCode() == KeyEvent.VK_A) {
			this.setX(this.getX() - 4);
			direction = "west";
			this.loadImages("resources/playerW.png", .2);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			this.setY(this.getY() + 4);
			direction = "south";
			this.loadImages("resources/playerS.png", .2);
		} else if (event.getKeyCode() == KeyEvent.VK_D) {
			this.setX(this.getX() + 4);
			direction = "east";
			this.loadImages("resources/player.PNG", .2);
		} else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
			toss("Water Bottle");
		}
	}

	public void toss(String proj) {
		int wbx = this.getX();
		int wby = this.getY();

		ExplosiveBottle eb = new ExplosiveBottle(wbx, wby, direction);
		eb.start();

		ShooterGame.shooterScreen.addObject(eb);
	}

}