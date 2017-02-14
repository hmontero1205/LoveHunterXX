package snake;

import java.awt.Color;
import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.Button;
import gui.components.Graphic;
import gui.components.TextArea;
import gui.components.Visible;

public class GuideScreen extends ClickableScreen {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private String instructions = "You need to find the best present for your girl."
			+ " You can do this by collecting all the presents you see."
			+ " Use the arrows to move left,up,right, or down."
			+ " But also, be sure to avoid the obstacles that will destroy"
			+ " some of the ";
	
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
		// Will create individual TextArea lines for each sentence.
		// specifically with the period at the end of each sentence.
		System.out.println("Creating lines.");
		ArrayList<String> lines = getLines(str);
		for(int i = 0, n = lines.size(); i < n; ++i){
			v.add(new TextArea(xStart, (i + 2) * 50, 800, 600, lines.get(i)));
		}
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		// Background image.
		Graphic background = new Graphic(0, 0, 800, 600, "resources/guidebackground.jpg");
		
		// button to be clicked to transition into the next screen.
		Button button = new Button((WIDTH/2) - (100/2), (HEIGHT/2) - (100/2), 100, 60, "Continue", Color.BLACK, new Action() {
			
			@Override
			public void act() { // transition will occur here.
				System.out.println("Let's continue!");
				
			}
		});
		
		// add items to the view list.
		viewObjects.add(background);
		viewObjects.add(button);
		
		// add the lines of instructions to the screen.
		addLines("You need to find the best present for your girl. Collect all the presents"
				+ " yUsing the arrows, move the cart and get as many"
				+ " presents as you can. Use the arrows.", viewObjects, 10);
		

	}

}
