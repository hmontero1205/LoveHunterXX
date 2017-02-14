package snake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.TextArea;
import gui.components.Visible;

public class GuideScreen extends ClickableScreen {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	public GuideScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<String> getLines(String str){ // will transform a single string to an array of lines of the string.
		ArrayList<String> lines = new ArrayList<String>();
		String tmp = "";
		
		for(int i = 0, n = str.length(); i < n; ++i){
			tmp += str.charAt(i);
			
			if(tmp.equals(" ")){ // delete the extra space after a period.
				tmp = "";
			}
			
			if(str.charAt(i) == '.'){
				lines.add(tmp);
				tmp = "";
			}
		}
		
		return lines;
	}
	
	public void addLines(String str, ArrayList<Visible> v, int xStart){
		ArrayList<String> lines = getLines(str);
		for(int i = 0, n = lines.size(); i < n; ++i){
			v.add(new TextArea(0,i * 50, 800, 600, lines.get(i)));
		}
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		// Background image.
		Graphic background = new Graphic(0, 0, 800, 600, "resources/guidebackground.jpg");
		
		// button to be clicked to transition into the next screen.
		Button button = new Button((WIDTH/2) - (100/2), (HEIGHT/2) - (100/2), 100, 60, "Continue", Color.BLACK, new Action() {
			
			@Override
			public void act() { // transition will occur here.
				System.out.println("Let's continue!");
				
			}
		});
		
		
		ArrayList<String> s = getLines("You need to find the best present for your girl. Using the arrows, move the cart and get as many"
				+ " presents as you can. Use the.");
		for(String ss: s){
			System.out.println(ss);
		}
		
		// text with all the instructions.
		TextArea guideBox = new TextArea(100, 100, 200, 200, "You need to find the best present for your girl.");
//		/TextArea guideBox = new TextArea(100, 100, 200, 200, "You need to find the best present for your girl.");
		
		// add items to the view list.
		viewObjects.add(background);
		viewObjects.add(button);
		viewObjects.add(guideBox);
		

	}

}
