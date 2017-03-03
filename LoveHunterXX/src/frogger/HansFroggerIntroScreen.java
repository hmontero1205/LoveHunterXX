package frogger;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.Visible;
import main.LoveHunterXX;
import quenchTheThirst.AriqShooterGame;

public class HansFroggerIntroScreen extends Screen implements MouseListener{
	private boolean second;
	private Button optBut;
	public HansFroggerIntroScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		Graphic backImg = new Graphic(0, 0, 800, 600, "resources/frogger/froggerintro.png");
		optBut = new Button(425, 461, 100, 35, "Proceed", Color.pink, new Action() {

			@Override
			public void act() {
				if (!second) {
					backImg.loadImages("resources/frogger/htp.png", 800, 600);
					update();
					second = true;
				}
				else{
					LoveHunterXX.game.setScreen(LoveHunterXX.fs);
				}
			}

		});
		viewObjects.add(backImg);
		viewObjects.add(optBut);

	}

	public MouseListener getMouseListener(){
		return this;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(optBut.isHovered(e.getX(), e.getY())){
			optBut.act();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


