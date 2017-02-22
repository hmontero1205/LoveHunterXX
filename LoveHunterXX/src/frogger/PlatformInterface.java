package frogger;

public interface PlatformInterface {
	boolean isTouchingPlayer(PlayerInterface p);
	
	boolean isApproachingPlayer(PlayerInterface p);
}
