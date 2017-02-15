package snake;

import java.util.ArrayList;

public class Snake {

	private ArrayList<Interactable> presentList;
	
	public enum Direction {
	    Left, Up, Right, Down
	}
	
	
	private int posX;
	private int posY;
	private Direction direction;
	
	public Snake() {
		this.posX = 0;
		this.posY = 0;
		this.direction = Direction.Down;
		presentList = new ArrayList<Interactable>();
		presentList.add(new SnakeHead(10,10,10,10));
	}

	private void addPresent(Present p){
		p.setX(presentList.get(presentList.size()-1).getX());
		p.setY(presentList.get(presentList.size()-1).getY()); // finish from here
		presentList.add(p);
	}
	
	private void removeLastPresent(){
		presentList.remove(presentList.size()-1);
	}
	
	private void moveCoors(Direction d){
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
		
		// this moves the body parts.
		for(int i = presentList.size()-1; i>0; i--){
			presentList.get(i).setX(presentList.get(i-1).getX());
			presentList.get(i).setY(presentList.get(i-1).getY());
		}
		
	}
}
