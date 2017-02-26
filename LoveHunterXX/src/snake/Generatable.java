package snake;


import java.util.List;

import gui.components.Visible;

public interface Generatable {
	public void generateNew(List<Visible> view, int presentLen);
	public void destroy();
	public Generatable getNewObject();//

}
