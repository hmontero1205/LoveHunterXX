package frogger;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import gui.components.Action;
import gui.components.Component;

public class PowerUp extends Component{
	private Action effect;
	private String imgSrc;
	private boolean superCreated;

	public PowerUp(int x, int y, int w, int h, String s, Action action) {
		super(x,y,w,h);
		superCreated = true;
		this.effect = action;
		this.imgSrc = s;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if(superCreated){
			System.out.println(imgSrc);
			ImageIcon icon = new ImageIcon("resources/frogger/"+imgSrc);
			g.drawImage(icon.getImage(),0,0, getWidth(), getHeight(), null);
		}
		
	}

	public boolean isTouchingPlayer(PlayerInterface p) {
		boolean touching = false;
		if (p.getX() <= this.getX() + this.getWidth() && p.getX() >= this.getX()
				|| p.getX() + p.getWidth() <= this.getX() + this.getWidth() && p.getX() + p.getWidth() >= this.getX()) {
			if (p.getY() <= this.getY() + this.getHeight() && p.getY() >= this.getY()
					|| p.getY() + p.getHeight() <= this.getY() + this.getHeight()
							&& p.getY() + p.getHeight() >= this.getY()) {
				touching = true;
			}
		}
		return touching;
	}

	public Action getEffect() {
		return this.effect;
	}

}
