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
	private String[] pModels = {"", "", "resources/frogger/player.png", ""};
	
	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		update();
		
	}
	
	@Override
	public void update(Graphics2D g) {
		ImageIcon icon = new ImageIcon(pModels[UP]);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(icon.getImage(), getX(), getY(), null);
	}
	
	@Override
	public void move(KeyEvent k) {
		
	}

}
