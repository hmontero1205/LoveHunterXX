package snake;


import java.util.List;

import gui.components.Visible;

public interface GeneratableInterface {
	public void generateNew(List<GeneratableInterface> presents);
	public GeneratableInterface getNewObject(int x, int y);
	public int[] getNewXY();

}
