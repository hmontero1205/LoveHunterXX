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
		int[] newCoords = getNewXY();
		presents.add(getNewObject(newCoords[0],newCoords[1]));
		SnakeGame.sScreen.addObject((Visible) presents.get(presents.size() - 1)); // adds the new generatable to the scene.
		
	}

	@Override
	public GeneratableInterface getNewObject(int x, int y) {
		return new LoveGift(x, y, 30, 30);
	}

}
