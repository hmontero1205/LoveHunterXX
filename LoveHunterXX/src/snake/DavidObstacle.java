package snake;

import java.util.List;

import gui.components.Visible;
import main.LoveHunterXX;

public class DavidObstacle extends DavidPresent {

	public DavidObstacle(int x, int y, int width, int height) {
		super(x, y, width, height, "resources/obstacle.png");
		setName("Block");
	}

	@Override
	public void generateNew(List<DavidGeneratableInterface> presents) {
		// TODO Auto-generated method stub
		if(LoveHunterXX.ss == null) return ;
		int r = getRandBetween(0, 9);
		if(r % 2 == 0){
			int[] newCoords = getNewXY();
			presents.add(getNewObject(newCoords[0],newCoords[1]));
			LoveHunterXX.ss.addObject((Visible) presents.get(presents.size() - 1)); // adds the new generatable to the scene.
		}
		//else System.out.println("NOT EVEN");
	}

	@Override
	public DavidGeneratableInterface getNewObject(int x, int y) {
		// TODO Auto-generated method stub
		return new DavidObstacle(x, y, 30, 30);
	}


}
