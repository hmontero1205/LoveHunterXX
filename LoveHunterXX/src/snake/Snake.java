package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gui.components.MovingComponent;
import gui.components.Visible;

public class Snake extends MovingComponent{
	public static final int DISTANCE = 20;
	public SnakeHead cart;
	private int refresh_r = 150;
	private int posX;
	private int posY;
	private Direction direction;
	public ArrayList<Interactable> presentList;
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
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
		presentList.add(new Present(0,0,30,30,"resources/present.png", true, true));
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
		
		// check for any collisions.
		checkGenCollision();
		checkLose();
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
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
		while(isRunning()){
			try {
				Thread.sleep(refresh_r); // rate changed.
				//checkCollision here
				update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkLose(){
		//not sure why it isn't checking for collision with self. I think it's something with looping through an arraylist...
		for(int i = 1; i<presentList.size(); i++){
			if (cart.isCollided(presentList.get(i))){
				refresh_r = 999999;
				System.out.println("Game Over. You ran into yourself.");
				return true;                   
			}
		}
		
		if(cart.getX()<30 || cart.getX()>415 || cart.getY() < 10 || cart.getY() > 440){
			refresh_r = 999999;
			System.out.println("Game Over. You ran into a wall.");
			return true;
		}
		
		return false;
	}
	
	
	public void checkGenCollision(){
		for(int i = 0; i < SnakeScreen.gens.size(); ++i){
			System.out.println("cart: " + cart.getX() + ", " + cart.getY());
			Present p = (Present) SnakeScreen.gens.get(i);
			System.out.println("presnet: " + p.getX() + ", " + p.getY());
			if(cart.isCollided((Interactable) SnakeScreen.gens.get(i))){
				System.out.println("Present has been collided.");
				SnakeScreen.gens.get(SnakeScreen.gens.size() - 1).generateNew(SnakeScreen.gens);
				addPresent((Present) SnakeScreen.gens.remove(i));
				//SnakeScreen.gens.add(e)
			}
			
		}
		
	}
	
}
