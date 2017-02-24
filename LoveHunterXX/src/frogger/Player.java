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
	public static final int moveDistance = 30;
	private int dir;
	private ArrayList<PowerUp> inventory;
	private String[] pModels = { "resources/frogger/player/playerleft.png", "resources/frogger/player/playerright.png",
			"resources/frogger/player/playerup.png", "resources/frogger/player/playerdown.png" };
	private boolean onPlatform;
	private MovingComponent currentPlatform;

	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		dir = UP;
		inventory = new ArrayList<PowerUp>();
		// TODO inventory stuff
		update();

	}

	@Override
	public void update(Graphics2D g) {
		if(getVx() != 0) {
			long currentTime = System.currentTimeMillis();
			int diff = (int) (currentTime - getMoveTime());
			if (diff >= REFRESH_RATE) {
				setMoveTime(currentTime);
				setPosx(getPosx() + getVx() * (double) diff / REFRESH_RATE);
				setPosy(getPosy() + getVy() * (double) diff / REFRESH_RATE);
				super.setX((int) getPosx());
				super.setY((int) getPosy());
			}
		}
		if (pModels != null) {
			g = clear();
			ImageIcon icon = new ImageIcon(pModels[dir]);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(icon.getImage(), 0, 0, getWidth() - 3, getHeight() - 3, 0, 0, icon.getIconWidth(),
					icon.getIconHeight(), null);
		}
	}

	@Override
	public void move(int k) {
		if (k == FroggerScreen.LEFTARROWKEY && !outOfBounds(FroggerScreen.LEFTARROWKEY)) {
			setX(getX() - moveDistance);
			dir = LEFT;
		} else if (k == FroggerScreen.RIGHTARROWKEY && !outOfBounds(FroggerScreen.RIGHTARROWKEY)) {
			setX(getX() + moveDistance);
			dir = RIGHT;
		} else if (k == FroggerScreen.UPARROWKEY && !outOfBounds(FroggerScreen.UPARROWKEY)) {
			setY(getY() - FroggerScreen.ROW_HEIGHT);
			dir = UP;
		} else if (k == FroggerScreen.DOWNARROWKEY && !outOfBounds(FroggerScreen.DOWNARROWKEY)) {
			setY(getY() + FroggerScreen.ROW_HEIGHT);
			dir = DOWN;
		}
		setVx(0);
		setRunning(false);
		this.onPlatform = false;
		update();
	}

	public boolean outOfBounds(int k) {
		if(k == FroggerScreen.LEFTARROWKEY) {
			if(getX() - moveDistance + getWidth() < 25) {
				return true;
			}
		}

		if (k == FroggerScreen.RIGHTARROWKEY) {
			if (getX() + moveDistance > 775) {
				return true;
			}
		}

		if (k == FroggerScreen.UPARROWKEY) {
			if (getY() - FroggerScreen.ROW_HEIGHT < 0) {
				return true;
			}
		}

		if (k == FroggerScreen.DOWNARROWKEY) {
			if (getY() + FroggerScreen.ROW_HEIGHT + getHeight() > 600) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void ridePlatform(MovingComponent p) {
		if(p instanceof Turtle){
			if(((Turtle) p).isTouchable()){
				this.onPlatform = true;
				this.setVx(p.getVx());
				play();
			}
			else{
				this.onPlatform = false;
				this.setVx(p.getVx());
				play();
			}
		}
		else{
			this.onPlatform = true;
			this.setVx(p.getVx());
			play();	
		}
		currentPlatform = p;
		
	}
	
	public void run() {
		setPosx(getX());
		setPosy(getY());
		setRunning(true);
		setMoveTime(System.currentTimeMillis());
		while (isRunning()) {
			try {
				Thread.sleep(REFRESH_RATE);
				update();
				
					
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if((getX()>800 || getX() < 0) && !FroggerGame.fs.gameOver){
				setRunning(false);
				FroggerGame.fs.gameOver("You were swept away by the current!");
			}
			
			if(currentPlatform instanceof Turtle && !((Turtle) currentPlatform).isTouchable() && !FroggerGame.fs.gameOver){
				FroggerGame.fs.gameOver("The turtle betrayed you.");
			}
		}

	}

	public void play(){
		if(!isRunning()){
			Thread go = new Thread(this);
			go.start();
		}
	}
	@Override
	public void die() {
		// change image to a bloody mess
	}
	
	public boolean isOnPlatform(){
		return onPlatform;
	}


}
