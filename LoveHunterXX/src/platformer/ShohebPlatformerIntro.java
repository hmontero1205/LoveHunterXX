package platformer;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import gui.ClickableScreen;
import gui.Screen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.Visible;
import main.LoveHunterXX;

public class ShohebPlatformerIntro extends Screen implements MouseListener {
	private Graphic img;
	private Button proceedButton;
	private int choice;
	
	public ShohebPlatformerIntro(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void initObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		img = new Graphic(0, 0, 800, 600, "resources/lwb.png");
		proceedButton = new Button(325, 425, 150, 80, "Continue", Color.pink, new Action() {

			@Override
			public void act() {
				if (choice == 0) {
					img.loadImages("resources/lwbhtp.png", 800, 600);
				} else if (choice == 1) {
					System.out.println("hey");
					LoveHunterXX.game.setScreen(LoveHunterXX.ps);
				}

				choice++;
			}

		});

		viewObjects.add(img);
		viewObjects.add(proceedButton);

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(proceedButton.isHovered(e.getX(), e.getY())){
			proceedButton.act();
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
	
	public MouseListener getMouseListener(){
		return this;
	}

	}

