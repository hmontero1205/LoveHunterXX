package gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Button extends TextLabel implements Clickable {

	private Color color;
	private Action action;
	
	public Button(int x, int y, int w, int h, String text, Color color, Action action) {
		super(x, y, w, h, text);
		this.color = color;
		this.action = action;
		update();
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color c){
		this.color = c;
		update();
	}
	public void update(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		g.fillRoundRect(0, 0, getWidth(),getHeight(), 35, 25);
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 25);
		
		g.setFont(new Font(getFont(),Font.PLAIN,getSize()));
		FontMetrics fm = g.getFontMetrics();
		if(getText()!= null){
			g.setColor(Color.white);
			String t = getText();
			int cutoff = t.length();
			while(cutoff > 0 && fm.stringWidth(t) > getWidth()){
				cutoff --;
				t = t.substring(0,cutoff); 
			}
			g.drawString(t, (getWidth()-fm.stringWidth(t))/2, 
					(getHeight()+fm.getAscent()-fm.getDescent())/2);
		}
		
	}

	@Override
	public boolean isHovered(int x, int y) {
		return ((x>this.getX() && x<(this.getX()+this.getWidth())) && (y>this.getY() && y<(this.getY()+this.getHeight())));
	}

	@Override
	public void act() {
		if(this.action!=null)
			this.action.act();
	}
}
