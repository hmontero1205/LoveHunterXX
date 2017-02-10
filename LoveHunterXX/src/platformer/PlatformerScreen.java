package platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList; 

import gui.Screen;
import gui.components.Graphic;
import gui.components.Visible;

public class PlatformerScreen extends Screen implements KeyListener{

	private Graphic bg;
	private Player player;

	public PlatformerScreen(int width, int height) {
		super(width, height);
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		bg = new Graphic(0, 0, 800, 600, "resources/platformerbg.PNG");
		viewObjects.add(bg);
//		player = new Player(10, 300, 20, 20);
//		viewObjects.add(player);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
