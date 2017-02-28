package snake;

import java.util.List;

import gui.components.Visible;

public class Present extends Interactable implements PresentInterface, GeneratableInterface{
	
	private int reward;
	private String name;
	private boolean collectable;
	private boolean collected;
	
	
	
	public Present(int x, int y, int width, int height, String path) {
		super(x, y, width, height, path);
		// TODO Auto-generated constructor stub
	}
	
	public Present(int x, int y, int width, int height, String path, boolean collectable, boolean collected) {
		super(x, y, width, height, path);
		this.collectable = collectable;
		this.collected = collected;
	}
	
	@Override
	public void setCollectable(boolean b) {
		// TODO Auto-generated method stub
		collectable = b;
	}
	@Override
	public void setCollected(boolean b) {
		// TODO Auto-generated method stub
		collected = b;
	}
	@Override
	public boolean isCollectable() {
		// TODO Auto-generated method stub
		return collectable;
	}
	@Override
	public boolean isCollected() {
		// TODO Auto-generated method stub
		return collected;
	}
	@Override
	public void setReward(int r) {
		// TODO Auto-generated method stub
		reward = r;
		
	}
	@Override
	public int getReward() {
		// TODO Auto-generated method stub
		return reward;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public void setName(String s) {
		// TODO Auto-generated method stub
		name = s;
	}
	@Override
	public void generateNew(List<GeneratableInterface> presents) {
		// TODO Auto-generated method stub
		if(SnakeGame.sScreen.getViewObjects() == null) return ;
		presents.add(getNewObject(getNewX(),getNewY()));
		SnakeGame.sScreen.addObject((Visible) presents.get(presents.size() - 1)); // adds the new generatable to the scene.
		
	}
	
	public GeneratableInterface getNewObject(int x, int y){
		return new Present(x ,y, 30,30, "resources/present.png");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isEmptyX(int x) {
		// Usage of lambdas would've been better.
		if(SnakeGame.sScreen.getViewObjects() == null) return false;
		for(int i = 0; i < SnakeGame.sScreen.getViewObjects().size(); ++i){
			if(SnakeGame.sScreen.getViewObjects().get(i).getX() == x) return false;
		}
		return true;
	}
	@Override
	public boolean isEmptyY(int y) {
		// TODO Auto-generated method stub
		if(SnakeGame.sScreen.getViewObjects() == null) return false;
		for(int i = 0; i < SnakeGame.sScreen.getViewObjects().size(); ++i){
			if(SnakeGame.sScreen.getViewObjects().get(i).getY() == y) return false;
		}
		return true;
	}
	@Override
	public int getNewX() {
		// TODO Auto-generated method stub
		int x;
		do{
			//x = getRandBetween(30, 440);
			x = 30 + (20 * getRandBetween(0, 13));
		}while(!isEmptyX(x));
		System.out.println("x: " + x);
		return x;
	}
	@Override
	public int getNewY() {
		// TODO Auto-generated method stub
		int y;
		do{
			y = 50 + (20 * getRandBetween(0, 13));
		}while(!isEmptyX(y));
		System.out.print("y: " + y);
		return y;
	}
	
}
