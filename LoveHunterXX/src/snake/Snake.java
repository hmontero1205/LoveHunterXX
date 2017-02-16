package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.MovingComponent;

public class Snake extends MovingComponent{

	private ArrayList<Interactable> presentList;
	
	public enum Direction {
	    Left, Up, Right, Down
	}
	
	
	private int posX;
	private int posY;
	private Direction direction;
	
	public Snake(int x, int y, int w, int h) {
		super(x, y, w, h);
		// these two should go on snake head class.
		this.posX = 0;
		this.posY = 0;
		
		
		this.direction = Direction.Down;
		presentList = new ArrayList<Interactable>();
		// last parameter is file path of image.
		presentList.add(new SnakeHead(10,10,10,10, null)); // adding head.
	}

	private void addPresent(Present p){ // adding body parts.
		p.setX(presentList.get(presentList.size()-1).getX());
		p.setY(presentList.get(presentList.size()-1).getY()); // finish from here
		presentList.add(p);
	}
	
	private void removeLastPresent(){
		presentList.remove(presentList.size()-1);
	}
	
	private void moveCoors(Direction d){
		// this moves the body parts.
		for(int i = presentList.size()-1; i>0; i--){
			presentList.get(i).setX(presentList.get(i-1).getX());
			presentList.get(i).setY(presentList.get(i-1).getY());
		}
		
		// this moves the head.
		switch(d){
		case Left:
			presentList.get(0).setX(presentList.get(0).getX() - 10);			
			break;
		case Up:
			presentList.get(0).setY(presentList.get(0).getY() - 10);
			break;
		case Right:
			presentList.get(0).setX(presentList.get(0).getX() + 10);
			break;
		case Down:
			presentList.get(0).setY(presentList.get(0).getY() + 10);
			break;
		}
	}
	
	public double getPosx() {
		return posX;
	}

	public void setPosx(int posX) {
		this.posX = posX;
	}
	
	public double getPosy() {
		return posY;
	}

	public void setPosy(int posy) {
		this.posY = posy;
	}
	
	public Direction getDirection(){
		return this.direction;
	}
	
	@Override
	public void drawImage(Graphics2D g) {
		if (presentList!=null){
			moveCoors(getDirection());
		}
	}
	
	
}
