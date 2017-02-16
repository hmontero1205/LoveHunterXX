package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.MovingComponent;

public class Snake extends MovingComponent{

	private ArrayList<Interactable> presentList;
	
	public final static int LEFT = 0;
	public final static int UP = 1;
	public final static int RIGHT = 2;
	public final static int DOWN = 3;
	public final static int DISTANCE = 20;
	private int posX;
	private int posY;
	private int direction;
	
	public Snake(int x, int y, int w, int h) {
		super(x, y, w, h);
		// these two should go on snake head class.
		this.posX = 0;
		this.posY = 0;
		
		
		this.direction = DOWN;
		presentList = new ArrayList<Interactable>();
		// last parameter is file path of image.
		presentList.add(new SnakeHead(30,60,50,50, "resources/cart.png")); // adding head.
		presentList.add(new Present(0,0,30,30,"resources/present.png")); //testing follow feature
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
	
	public void moveCoors(int d){
		if(presentList == null) return;
		System.out.println(presentList.size());
		// this moves the body parts.
		for(int i = presentList.size()-1; i>0; i--){
			presentList.get(i).setX(presentList.get(i-1).getX());
			presentList.get(i).setY(presentList.get(i-1).getY());
		}
		
		// this moves the head.
		switch(d){
		case LEFT:
			presentList.get(0).setX(presentList.get(0).getX() - DISTANCE);			
			break;
		case UP:
			presentList.get(0).setY(presentList.get(0).getY() - DISTANCE);
			break;
		case RIGHT:
			presentList.get(0).setX(presentList.get(0).getX() + DISTANCE);
			break;
		case DOWN:
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
	
	public int getDirection(){
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
	
	
}
