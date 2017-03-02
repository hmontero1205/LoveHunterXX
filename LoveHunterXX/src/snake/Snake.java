package snake;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gui.components.MovingComponent;

public class Snake extends MovingComponent{
	public static final int DISTANCE = 20;
	private boolean gameRunning = true;
	public SnakeHead cart;
	private int refresh_r = 250;
	private int posX;
	private int posY;
	private Direction direction;
	public ArrayList<Interactable> presentList;
	public enum Direction{
		left, up, right, down
	}

	public Snake(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.posX = 0;
		this.posY = 0;
		cart = new SnakeHead(30,60,30,30, "resources/cartRight.png");

		this.direction = Snake.Direction.down;
		presentList = new ArrayList<Interactable>();
		presentList.add(cart);
		presentList.add(new LoveGift(0,0,30,30));
	}

	public void addPresent(Present p){ // adding body parts.
		p.setX(presentList.get(presentList.size()-1).getX());
		p.setY(presentList.get(presentList.size()-1).getY()); // finish from here
		System.out.println(p.getX() + ", " + p.getY());
		presentList.add(presentList.size()-1,p);
	}

	public void removeLastPresent(){
		Interactable i = presentList.remove(presentList.size()-1);
		SnakeGame.sScreen.remove(i);
	}

	public void moveCoors(Direction d){
		if(presentList == null || gameRunning == false) return;
		// change direction to new direction.
		this.direction = d;
		// this moves the body parts.
		for(int i = presentList.size()-1; i>0; i--){
			presentList.get(i).setX(presentList.get(i-1).getX());
			presentList.get(i).setY(presentList.get(i-1).getY());
		}
		// this moves the head.
		switch(this.direction){
		case left:
			cart.setX(cart.getX() - DISTANCE); 
			cart.setSprite("resources/cartLeft.png");
			checkGenCollision();
			break;
		case up:
			cart.setY(cart.getY() - DISTANCE);
			checkGenCollision();
			break;
		case right:
			cart.setX(cart.getX() + DISTANCE);
			cart.setSprite("resources/cartRight.png");
			checkGenCollision();
			break;
		case down:
			cart.setY(cart.getY() + DISTANCE);
			checkGenCollision();
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
	
	public void setRate(int r){
		refresh_r = r;
	}
	public int getRate(){
		return refresh_r;
	}

	@Override
	public void drawImage(Graphics2D g) {
		if (presentList!=null){
			// move coordinates.
			moveCoors(getDirection());
			// check for any collisions.
			checkAll();
		}
	}

	public ArrayList<Interactable> getItems(){
		return presentList;
	}

	public void setDirection(Direction d){
		this.direction = d;
	}
	
	public void updateRates(){
		int s = presentList.size() - 1;
		
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
		for(int i = 1; i<presentList.size(); i++){
			if (cart.isCollided(presentList.get(i))){
				setRate(999999);
				gameRunning = false;
				System.out.println("Game Over. You ran into yourself. You have earned " + presentList.size()/6 + " LovePoints.");
				SnakeGame.sScreen.updateText("Game Over.                          You ran into yourself. You have earned " + presentList.size()/6 + " LovePoints.");
				return true;                   
			}
		}

		if(cart.getX()<30 || cart.getX()>415 || cart.getY() < 50 || cart.getY() > 440){
			setRate(999999);
			gameRunning = false;
			System.out.println("Game Over. You ran into a wall. You have earned " + presentList.size()/10 + " LovePoints.");
			SnakeGame.sScreen.updateText("                Game Over.                   You ran into a wall. You have              earned " + presentList.size()/10 + " LovePoints.");
			return true;
		}
		return false;
	}
	
	public boolean checkWin(){
		if(presentList.size()>30){
			setRate(999999);
			gameRunning = false;
			System.out.println("You win! You have collected enough presents to please your girlfriend! LovePoints +3!");
			SnakeGame.sScreen.updateText("You win! You have collected enough presents to please your girlfriend! LovePoints +3!");
			return true;
		}
		return false;
	}


	public void checkGenCollision(){
		for(int i = 0; i < SnakeScreen.gens.size(); ++i){
			Present p = (Present) SnakeScreen.gens.get(i);
			if(cart.isCollided((Interactable) SnakeScreen.gens.get(i))){
				// keep track of the collision.
				Present collided = (Present) SnakeScreen.gens.get(i);
				
				// check what kind of collision.
				if(collided.getName() == "LoveGift"){
					// collision was with LoveGift type.
					LoveGift item = (LoveGift) collided;
					System.out.println("Collided with LOVE");
					//SnakeScreen.gens.get(SnakeScreen.gens.size() - 1).generateNew(SnakeScreen.gens);
					item.generateNew(SnakeScreen.gens);
					addPresent((Present) SnakeScreen.gens.remove(i));
				}
				else if(collided.getName() == "Block"){
					// collision was with Obstacle type.
					Obstacle item = (Obstacle) collided;
					// remove old item from gens list.
					SnakeScreen.gens.remove(item);
					// removes old obstacle from screen.
					SnakeGame.sScreen.remove(item); 
					// generate new obstacle.
					item.generateNew(SnakeScreen.gens);
					
					if(presentList.size() > 2){
						removeLastPresent();
						System.out.println("Collided with block");
					}
				}
				SnakeGame.sScreen.updateScore();
			}
		}
	}
	
	public void checkAll(){
		checkGenCollision();
		checkLose();
		checkWin();
	}
}
