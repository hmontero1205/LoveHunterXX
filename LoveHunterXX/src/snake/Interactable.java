package snake;

import gui.components.Graphic;

public abstract class Interactable extends Graphic{
	// not sure what to put in this class so we can decide at a later time.
	private boolean isDanger;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public Interactable(int x, int y, int width, int height, String path) {
		super(x, y, width, height, path);
		this.xPos = x;
		this.yPos = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public void setX(int x){
		this.xPos = x;
	}
	
	public void setY(int y){
		this.yPos = y;
	}
}