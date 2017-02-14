package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import gui.components.Clickable;
import gui.components.Visible;

public abstract class ClickableScreen extends Screen implements MouseListener {

	private List<Clickable> clickables;
	public ClickableScreen(int width, int height) {
		super(width, height);
		//this is a comment
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0;i<clickables.size();i++){
			if(clickables.get(i).isHovered(e.getX(),e.getY())){
				clickables.get(i).act();
				break;
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		initAllObjects(viewObjects);
		clickables = new ArrayList<Clickable>();
		for(Visible v: viewObjects){
			if(v instanceof Clickable){
				clickables.add((Clickable)v);
			}
		}
	}
	
	public abstract void initAllObjects(List<Visible> viewObjects);
	
	public MouseListener getMouseListener(){
		return this;
	}
	
	 public void addObject(Visible v){
		 super.addObject(v);
		 if(v instanceof Clickable){
			 clickables.add((Clickable)v);
		 }
	 }

	 
	 public void remove(Visible v){
		 super.remove(v);
		 clickables.remove(v);
	 } 
	

}
