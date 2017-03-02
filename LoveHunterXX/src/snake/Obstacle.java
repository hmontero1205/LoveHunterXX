package snake;

import java.util.List;

import gui.components.Visible;

public class Obstacle extends Present {

	public Obstacle(int x, int y, int width, int height) {
		super(x, y, width, height, "resources/Block.png");
		setName("Block");
	}

	@Override
	public void generateNew(List<GeneratableInterface> presents) {
		// TODO Auto-generated method stub
		if(SnakeGame.sScreen == null) return ;
		int r = getRandBetween(0, 9);
		if(r % 2 == 0){
			int[] newCoords = getNewXY();
			presents.add(getNewObject(newCoords[0],newCoords[1]));
			SnakeGame.sScreen.addObject((Visible) presents.get(presents.size() - 1)); // adds the new generatable to the scene.
		}
		else System.out.println("NOT EVEN");
		
		
	}

	@Override
	public GeneratableInterface getNewObject(int x, int y) {
		// TODO Auto-generated method stub
		return new Obstacle(x, 0, y, 30);
	}
	

}
