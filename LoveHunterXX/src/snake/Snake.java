package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.MovingComponent;

public class Snake extends MovingComponent{
	public final static int DISTANCE = 20;
	public SnakeHead cart;
	private int REFRESH_R = 150;
	private int posX;
	private int posY;
	private Direction direction;
	private ArrayList<Interactable> presentList;
	public enum Direction{
		left, up, right, down
	}

	public Snake(int x, int y, int w, int h) {
		super(x, y, w, h);
		// these two should go on snake head class.
		this.posX = 0;
		this.posY = 0;
		cart = new SnakeHead(30,60,30,30, "resources/cartRight.png");
		
		this.direction = Snake.Direction.down;
		presentList = new ArrayList<Interactable>();
		presentList.add(cart);
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
		presentList.add(new Present(0,0,30,30,"resources/present.png"));
	}

	public void addPresent(Present p){ // adding body parts.
		p.setX(presentList.get(presentList.size()-1).getX());
		p.setY(presentList.get(presentList.size()-1).getY()); // finish from here
		presentList.add(p);
	}
	
	public void removeLastPresent(){
		presentList.remove(presentList.size()-1);
	}
	
	public void moveCoors(Direction d){
		if(presentList == null) return;
		// this moves the body parts.
		// change direction to new direction.
		this.direction = d;
		
		for(int i = presentList.size()-1; i>0; i--){
			presentList.get(i).setX(presentList.get(i-1).getX());
			presentList.get(i).setY(presentList.get(i-1).getY());
		}
		
		// this moves the head.
		switch(this.direction){
		case left:
			presentList.get(0).setX(presentList.get(0).getX() - DISTANCE);
			cart.setSprite("resources/cartLeft.png");
			break;
		case up:
			presentList.get(0).setY(presentList.get(0).getY() - DISTANCE);
			break;
		case right:
			presentList.get(0).setX(presentList.get(0).getX() + DISTANCE);
			cart.setSprite("resources/cartRight.png");
			break;
		case down:
			presentList.get(0).setY(presentList.get(0).getY() + DISTANCE);
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
	
	public ArrayList<Interactable> getItems(){
		return presentList;
	}
	
	public void setDirection(Direction d){
		this.direction = d;
	}
	
	@Override
	public void run() {
//		posx=getX();
//		posy=getY();
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
		while(isRunning()){
			try {
				Thread.sleep(REFRESH_R); // rate changed.
				//checkCollision here
				update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
}