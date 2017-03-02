package snake;

import java.util.List;

import gui.components.Visible;

public class LoveGift extends Present {

	public LoveGift(int x, int y, int width, int height) {
		super(x, y, width, height, "resources/present.png");
		setName("LoveGift");
	}

	@Override
	public void generateNew(List<GeneratableInterface> presents) {
		if(SnakeGame.sScreen == null) return ;
		// get the coordinates for the new present:
		int[] newCoords = getNewXY();
		// add it to the screen and generatables list.
		presents.add(getNewObject(newCoords[0],newCoords[1]));
		SnakeGame.sScreen.addObject((Visible) presents.get(presents.size() - 1)); // adds the new generatable to the scene.
		
		// 50/50 chance of creating new Obstacle:
		Obstacle o = new Obstacle(0, 0, 1, 1); // create new obstacle.
		o.generateNew(presents); // 50/50 chance of adding it to the screen.
	}

	@Override
	public GeneratableInterface getNewObject(int x, int y) {
		return new LoveGift(x, y, 30, 30);
	}

}
