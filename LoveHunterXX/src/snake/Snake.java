package snake;

import java.util.ArrayList;

public class Snake {

	private ArrayList<Interactable> presentList;
	
	private final static int UP = 0;
	private final static int RIGHT = 0;
	private final static int DOWN = 0;
	private final static int LEFT = 0;
	
	private int posX;
	private int posY;
	private int direction;
	
	public Snake() {
		this.posX = 0;
		this.posY = 0;
		this.direction = DOWN;
	}

	
}
