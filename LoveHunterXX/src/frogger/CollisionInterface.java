package frogger;

public interface CollisionInterface {
	boolean isTouchingPlayer(Player p);
	
	boolean isApproachingPlayer(Player p);
}
