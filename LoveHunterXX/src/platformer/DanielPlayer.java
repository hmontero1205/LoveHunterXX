package platformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gui.components.AnimatedComponent;
import gui.components.MovingComponent;
import main.LoveHunterXX;

public class DanielPlayer extends AnimatedComponent{
	private int x;
	private int y;
	private int w;
	private int h;
	private int currentFrame;
	
	private Image image;
	private boolean jump;
	public boolean invuln;
	private boolean load;
	private boolean damaged;
	
	private ArrayList<Image> frames;
	
	private long startInvuln;
	private long startJump;
	private long invulnLength;
	
	private int imgID;
	
	
	private double initialV;
	private double grav;
	private int hp;
	private String imageSrc = "resources/player.png";
	private Graphics2D global;
	
	public DanielPlayer(int x, int y, int w, int h, String imageLocation){
		super(x,y,w,h);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.load = false;
		this.jump = false;
		this.hp = 3;
		this.invulnLength = 350;
		
		this.imgID = 0;
		this.initialV = 9;
		this.grav = 1.5;
		
		frames = new ArrayList<Image>();
		frames.add(new ImageIcon(imageSrc).getImage());
		frames.add(new ImageIcon("resources/playerjump.png").getImage());
		
		setX(x);
		setY(y);
		loadImage();
	}
	private void loadImage() {
		try{
			image = new ImageIcon(imageSrc).getImage();
			load = true;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void setInvuln(boolean b){
		this.invuln = b;
	}
	public void update(Graphics2D g){
		if(load){
//			image = new ImageIcon(imageSrc).getImage(); 
//			if(invuln){
//				if(imgID == 0){
//					image = new ImageIcon("resources/platformerplayerinvul.png").getImage();
//					imgID = 1;
//				}
//				else{
//					if(imgID == 1){
//						image = new ImageIcon(imageSrc).getImage();
//						imgID = 0;
//					}
//				}
//				image = new ImageIcon("resources/platformerplayerinvul.png").getImage();
//			}
			
				
//				clear();
//				imageSrc = "resources/playerhit.png";
//				loadImage();
//				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
				global = g;
			if(jump){
				
				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
				setPosy(getPosy() + getVy());
				super.setY((int)getPosy());
			}
			else{
				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
			}
		}
	}
	public void flicker(Graphics2D g){
		try {
			invuln = true;
			clear();
			ShohebPlatformerGame.cs.shohebUmbrella.clear();
			Thread.sleep(500);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), 0,0,image.getWidth(null), image.getHeight(null), null);
			damaged = !damaged;
			invuln = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		setRunning(true);
		while(isRunning()){
			try {
				Thread.sleep(REFRESH_RATE);
				checkBehaviors();
				if(damaged){
					flicker(global);
				}
				else{
					update();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void checkBehaviors(){
		if(jump){
			long current = System.currentTimeMillis();
			int difference = (int)(current - startJump);
			double newV = initialV - grav*(double)(difference/100);
			if(getY() > 372){
				setJump(false);
				setY(370);
				image = frames.get(0);
			}
			else{
				super.setVy(-newV);
			}
		}
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void play(){
		if(!isRunning()){
			Thread start = new Thread(this);
			start.start();
		}
	}
	public void setJump(boolean b) {
		clear();
		jump = b;
		startJump = System.currentTimeMillis();
		image = frames.get(1);
	}
	public boolean getJump() {
		return jump;
	}
	public void act() {
		// TODO Auto-generated method stub
		
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
		update();
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
		update();
	}
	public void setGravity(double g){
		this.grav = g;
	}
	public void setInitialV(double v){
		this.initialV = v;
	}
	public void setImgSrc(String src){
		this.imageSrc = src;
		loadImage();
	}
	public void setInvulnLength(int i) {
		this.invulnLength = i;
		
	}
	public void setStartInvuln(long i){
		this.startInvuln = i;
	}
	public void setDamaged(boolean b) {
		this.damaged = b;
		
	}
}
