package snake;

import java.util.List;

import gui.components.Visible;
import main.LoveHunterXX;

public class DavidLoveGift extends DavidPresent {

	public DavidLoveGift(int x, int y, int width, int height) {
		super(x, y, width, height, "resources/present.png");
		setName("LoveGift");
	}

	@Override
	public void generateNew(List<DavidGeneratableInterface> presents) {
		if(LoveHunterXX.ss == null) return ;
		// get the coordinates for the new present:
		int[] newCoords = getNewXY();
		// add it to the screen and generatables list.
		presents.add(getNewObject(newCoords[0],newCoords[1]));
		LoveHunterXX.ss.addObject((Visible) presents.get(presents.size() - 1)); // adds the new generatable to the scene.
		
		// 50/50 chance of creating new Obstacle:
		DavidObstacle o = new DavidObstacle(0, 0, 1, 1); // create new obstacle.
		o.generateNew(presents); // 50/50 chance of adding it to the screen.
	}

	@Override
	public DavidGeneratableInterface getNewObject(int x, int y) {
		return new DavidLoveGift(x, y, 30, 30);
	}

}
