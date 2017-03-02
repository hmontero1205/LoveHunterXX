package snake;


import java.util.List;

import gui.components.Visible;

public interface GeneratableInterface {
	public void generateNew(List<GeneratableInterface> presents);
	public GeneratableInterface getNewObject(int x, int y);
	public boolean isEmptyX(int x);
	public boolean isEmptyY(int y);
	public int getNewX();
	public int getNewY();

}
