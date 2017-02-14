package snake;

public abstract class Interactable {
	// not sure what to put in this class so we can decide at a later time.
	private boolean isDanger;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public Interactable() {
	}

	public int getX() {
		// TODO Auto-generated method stub
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
}
