package snake;

import java.util.List;

import gui.components.Visible;

public class Present extends Interactable implements PresentInterface, Generatable{
	
	
	
	public Present(int x, int y, int width, int height, String path) {
		super(x, y, width, height, path);
		// TODO Auto-generated constructor stub
	}
	
	private int reward;
	private String name;
	
	
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
	public void generateNew(List<Visible> view, int presentLen) {
		// TODO Auto-generated method stub
		
		
		view.add((Visible) getNewObject(getNewX(view),getNewY(view))); // adds the new generatable to the scene.
		
	}
	
	public Generatable getNewObject(int x, int y){
		return new Present(x ,y, 30,30, "resources/present.png");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isEmptyX(int x, List<Visible> view) {
		// Usage of lambda function would've been better.
		for(int i = 0; i < view.size(); ++i){
			if(view.get(i).getX() == x) return false;
		}
		return true;
	}
	@Override
	public boolean isEmptyY(int y, List<Visible> view) {
		// TODO Auto-generated method stub
		for(int i = 0; i < view.size(); ++i){
			if(view.get(i).getY() == y) return false;
		}
		return true;
	}
	@Override
	public int getNewX(List<Visible> view) {
		// TODO Auto-generated method stub
		int x;
		do{
			x = getRandBetween(30, 440);
		}while(!isEmptyX(x, view));
		System.out.println("x: " + x);
		return x;
	}
	@Override
	public int getNewY(List<Visible> view) {
		// TODO Auto-generated method stub
		int y;
		do{
			y = getRandBetween(60, 460);
		}while(!isEmptyX(y, view));
		System.out.print("y: " + y);
		return y;
	}
}
