package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Player extends LivingEntity {

	private HashMap<String, Integer> arsenal;

	public Player(int x, int y) {
		super(x, y, 1, "resources/player.PNG");

		arsenal = new HashMap<String, Integer>();
		arsenal.put("explosive", 5);
		arsenal.put("alluring", 5);
	}

	public HashMap<String, Integer> getArsenal() {
		return arsenal;

	}

	public void handle(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_W) {
			this.setY(this.getY() + 10);
		} else if (event.getKeyCode() == KeyEvent.VK_A) {
			this.setX(this.getX() - 10);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			this.setY(this.getY() - 10);
		} else if (event.getKeyCode() == KeyEvent.VK_D) {
			this.setX(this.getX() + 10);
		}
	}

	public void toss(Projectile proj) {
		
	}

}