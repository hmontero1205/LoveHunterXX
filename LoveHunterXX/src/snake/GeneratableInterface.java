package snake;


import java.util.List;

import gui.components.Visible;

public interface GeneratableInterface {
	public void generateNew(List<Visible> view, List<GeneratableInterface> presents, int presentLen);
	public void destroy();
	public GeneratableInterface getNewObject(int x, int y);
	public boolean isEmptyX(int x, List<Visible> view);
	public boolean isEmptyY(int y, List<Visible> view);
	public int getNewX(List<Visible> view);
	public int getNewY(List<Visible> view);
	public void setCollectable(boolean b);
	public void setCollected(boolean b);
	public boolean isCollectable();
	public boolean isCollected();

}
