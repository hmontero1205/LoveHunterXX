package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.components.MovingComponent;

public class Player extends MovingComponent implements PlayerInterface {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	private String[] pModels = {"resources/frogger/player/playerleft.png", "resources/frogger/player/playerright.png", "resources/frogger/player/playerup.png", "resources/frogger/player/playerdown.png"};
	
	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		update();
		
	}
	
	@Override
	public void update(Graphics2D g) {
		if(pModels != null) {
			ImageIcon icon = new ImageIcon(pModels[UP]);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
			g.setColor(Color.RED);
			g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		}
	}
	
	@Override
	public void move(KeyEvent k) {
		
	}

}
