package frogger;

public interface PowerUpInterface {
	
	public String getPowerUpName();
	
	public void activatePowerUp();
	
	public boolean touchingPlayer(Player p);
}
