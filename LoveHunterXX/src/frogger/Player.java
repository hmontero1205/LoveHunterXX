package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gui.components.MovingComponent;

public class Player extends MovingComponent implements PlayerInterface {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	private int dir;
	private ArrayList<PowerUp> inventory;
	private String[] pModels = {"resources/frogger/player/playerleft.png", "resources/frogger/player/playerright.png", "resources/frogger/player/playerup.png", "resources/frogger/player/playerdown.png"};
	
	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		dir = UP;
		inventory = new ArrayList<PowerUp>();
//		TODO inventory stuff
		update();
		
	}
	
	@Override
	public void update(Graphics2D g) {
		if(pModels != null) {
			g = clear();
			ImageIcon icon = new ImageIcon(pModels[dir]);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(icon.getImage(), 0, 0, getWidth()-3, getHeight()-3, 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
			g.setColor(Color.RED);
			g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		}
	}
	
	@Override
	public void move(int k) {
		if(k == FroggerScreen.LEFTARROWKEY) {
			if(!outOfBounds(FroggerScreen.LEFTARROWKEY)) {
				setX(getX() - FroggerScreen.ROW_HEIGHT);
				dir = LEFT;
			}
		} else if(k == FroggerScreen.RIGHTARROWKEY) {
			if(!outOfBounds(FroggerScreen.RIGHTARROWKEY)) {
				setX(getX() + FroggerScreen.ROW_HEIGHT);
				dir = RIGHT;
			}
		} else if(k == FroggerScreen.UPARROWKEY) {
			if(!outOfBounds(FroggerScreen.UPARROWKEY)) {
				setY(getY() - FroggerScreen.ROW_HEIGHT);
				dir = UP;
			}
		} else if(k == FroggerScreen.DOWNARROWKEY) {
			if(!outOfBounds(FroggerScreen.DOWNARROWKEY)) {
				setY(getY() + FroggerScreen.ROW_HEIGHT);
				dir = DOWN;
			}
		}
		update();
	}

	private boolean outOfBounds(int k) {
		if(k == FroggerScreen.LEFTARROWKEY) {
			if(getX() - FroggerScreen.ROW_HEIGHT + getWidth() < 25) {
				return true;
			}
		}
		
		if(k == FroggerScreen.RIGHTARROWKEY) {
			if(getX() + FroggerScreen.ROW_HEIGHT > 775) {
				return true;
			}
		}
		
		if(k == FroggerScreen.UPARROWKEY) {
			if(getY() - FroggerScreen.ROW_HEIGHT < 0) {
				return true;
			}
		}
		
		if(k == FroggerScreen.DOWNARROWKEY) {
			if(getY() + FroggerScreen.ROW_HEIGHT + getHeight() > 600) {
				return true;
			}
		}
		return false;
	}

}
