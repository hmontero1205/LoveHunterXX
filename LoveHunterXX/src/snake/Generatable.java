package snake;


import java.util.List;

import gui.components.Visible;

public interface Generatable {
	public void generateNew(List<Visible> view, int presentLen);
	public void destroy();
	public Generatable getNewObject(int x, int y);
	public boolean isEmptyX(int x, List<Visible> view);
	public boolean isEmptyY(int y, List<Visible> view);
	public int getNewX(List<Visible> view);
	public int getNewY(List<Visible> view);
	public void setCollectable(boolean b);
	public void setCollected(boolean b);
	public boolean isCollectable();
	public boolean isCollected();

}
