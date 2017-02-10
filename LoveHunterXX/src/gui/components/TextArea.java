package gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TextArea extends TextLabel {

	public TextArea(int x, int y, int w, int h, String text) {
		super(x, y, w, h, text);
	}

	public void update(Graphics2D g){
		if(getText()!=null){
			g.setFont(new Font(getFont(),Font.PLAIN,getSize()));
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setColor(Color.black);
			FontMetrics fm = g.getFontMetrics();
			String[] wordArr = this.getText().split(" ");
			int wordArrIndex = 0;
			String toPrint = "";
			int linesPrinted = 1;
			if(fm.stringWidth(wordArr[0])<this.getWidth()-5){
				while(((linesPrinted)*(fm.getAscent() + fm.getDescent()) < this.getHeight()) && wordArrIndex<wordArr.length){
					toPrint+=wordArr[wordArrIndex];
					if(fm.stringWidth(toPrint)>this.getWidth())
						break;
					while(wordArrIndex+1<wordArr.length && fm.stringWidth(toPrint + " " + wordArr[wordArrIndex+1]) < this.getWidth()-5 ){
						wordArrIndex++;
						toPrint+=" "+wordArr[wordArrIndex];
					}
					g.drawString(toPrint, 5, linesPrinted * (fm.getAscent() + fm.getDescent()));
					linesPrinted++;
					wordArrIndex++;
					toPrint="";			
				}
			}
		}
	}
}
