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
		view.add((Visible) getNewObject()); // adds the new generatable to the scene.
		
	}
	
	public Generatable getNewObject(){
		return new Present(0,0, 30,30, "resources/present.png");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
