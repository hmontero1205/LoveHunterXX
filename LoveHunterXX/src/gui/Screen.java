package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.components.Visible;

public abstract class Screen {
	private BufferedImage image;
	private ArrayList<Visible> viewObjects;
	public Screen(int width,int height){
		viewObjects = new ArrayList<Visible>();
		initObjects(viewObjects);
		initImage(width,height);
	}
	public void initImage(int width, int height) {
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		update();
		
	}
	public void update() {
		BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		Graphics2D g = buffer.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(204,204,255));
		g.fillRect(0,0,image.getWidth(), image.getHeight());
		g.setColor(Color.black);
		for(int i=0;i<viewObjects.size();i++){
			g.drawImage(viewObjects.get(i).getImage(), viewObjects.get(i).getX(), viewObjects.get(i).getY(), null);
		}
		g2.drawImage(buffer, 0, 0, null);
	}
	public abstract void initObjects(ArrayList<Visible> viewObjects);
	public int getWidth(){
		return image.getWidth();
	}
	public int getHeight(){
		return image.getHeight();
	}
	public BufferedImage getImage(){
		return image;
	}
	
	public MouseMotionListener getMouseMotionListener(){
		return null;
	}
	
	public MouseListener getMouseListener(){
		return null;
	}
	
	public KeyListener getKeyListener(){
		return null;	
	}
	
	public void addObject(Visible v){
		viewObjects.add(v);
	}
	public void remove(Visible v){
		viewObjects.remove(v);
	}
	public void moveToBack(Visible v){
		if(viewObjects.contains(v)){
			viewObjects.remove(v);
			viewObjects.add(0,v);
		}
	}
	public void moveToFront(Visible v){
		if(viewObjects.contains(v)){
			viewObjects.remove(v);
			viewObjects.add(v);
		}
	}
	public MouseWheelListener getMouseWheelListener() {
		return null;
	}
	
}
