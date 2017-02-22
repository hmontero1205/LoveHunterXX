package frogger;

public interface CollisionInterface {
	boolean isTouchingPlayer(PlayerInterface p);
	
	boolean isApproachingPlayer(PlayerInterface p);
}
