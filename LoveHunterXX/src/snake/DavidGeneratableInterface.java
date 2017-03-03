package snake;

import java.util.List;

import gui.components.Visible;

public interface DavidGeneratableInterface {
	public void generateNew(List<DavidGeneratableInterface> presents);

	public DavidGeneratableInterface getNewObject(int x, int y);

	public int[] getNewXY();

}
