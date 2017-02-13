package quenchTheThirst;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Player extends LivingEntity {
	
	private HashMap<Projectile, Integer> arsenal;

	public Player(int x, int y, double scale, String imageLocation) {
		super(x, y, scale, imageLocation);
	}
	
	public void handle(KeyEvent event) {
		
	}

	public void toss(Projectile proj) {

	}

}
