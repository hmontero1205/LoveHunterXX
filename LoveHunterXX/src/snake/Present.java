package snake;

import java.util.List;

import gui.components.Visible;

public abstract class Present extends Interactable implements PresentInterface, GeneratableInterface{
	
	private int reward;
	private String name;
	
	public Present(int x, int y, int width, int height, String path) {
		super(x, y, width, height, path);
		// TODO Auto-generated constructor stub
	}
	
	public Present(int x, int y, int width, int height, String path, boolean collectable, boolean collected) {
		super(x, y, width, height, path);
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
	
	@Override // Abstract methods: each present has its own way of generating new objects.
	abstract public void generateNew(List<GeneratableInterface> presents);
	abstract public GeneratableInterface getNewObject(int x, int y);
	
	public boolean isEmptySpace(int x, int y){
		for(int i = 0; i < SnakeGame.sScreen.getViewObjects().size(); i++){
			if(SnakeGame.sScreen.getViewObjects().get(i).getY() == y && 
					SnakeGame.sScreen.getViewObjects().get(i).getX() == x){
				return false;
			}
		}
		return true;
		
	}
	public int[] getNewXY(){
		int[] coords = new int[2];
		int x,y;
		do{
			x = 30 + (20 * getRandBetween(0, 19));
			y = 60 + (20 * getRandBetween(0, 19));
		}while(!isEmptySpace(x, y));
		coords[0] = x;
		coords[1] = y;
		return coords;
	}
	
}
