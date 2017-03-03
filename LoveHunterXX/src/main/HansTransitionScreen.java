package main;

import java.awt.Color;
import java.util.List;

import gui.Screen;
import gui.components.Graphic;
import gui.components.TextArea;
import gui.components.TextLabel;
import gui.components.Visible;

public class HansTransitionScreen extends Screen implements Runnable {
	private Thread sThread;
	private Graphic back;
	private Graphic you;
	private Graphic her;
	private TextArea tBox;
	private TextLabel hLabel;
	private TextLabel yLabel;
	public int lovePoints;
	public HansTransitionScreen(int width, int height) {
		super(width, height);
		sThread = new Thread(this);
		sThread.start();
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		back = new Graphic(0,0,"resources/LoveHunterXXScreen.png");
		you = new Graphic(550,300,"resources/you.png");
		her = new Graphic(200,250,"resources/her.png");
		tBox = new TextArea(280,110,500,30,"Previously on LoveHunterX....");
		hLabel = new TextLabel(300,260,500,25,"");
		yLabel = new TextLabel(500,280,500,25,"");;
		tBox.setC(Color.white);
		viewObjects.add(back);
		viewObjects.add(hLabel);
		hLabel.setC(Color.white);
		viewObjects.add(yLabel);
		yLabel.setC(Color.white);
//		viewObjects.add(tBox);
//		viewObjects.add(you);
//		viewObjects.add(her);
		

	}

	@Override
	public void run() {
		//sequence 1
		sleep(1000);
//		back.loadImages("resources/bedroom.jpg", 800,600);
//		sleep(500);
//		addObject(tBox);
//		sleep(2000);
//		sleep(500);
//		addObject(you);
//		addObject(her);
//		hLabel.setText("What took you so long? xD");
//		tBox.setText("");
//		sleep(2000);
//		hLabel.setText("");
//		yLabel.setText("I wanted to ask you something...");
//		sleep(2000);
//		yLabel.setText("LET'S GO ON A DATE PLS");
//		sleep(2000);
//		hLabel.setText("Yeah sure lol");
//		yLabel.setText("");
//		sleep(2000);
//		hLabel.setText("");
//		remove(her);
//		remove(you);
//		back.loadImages("resources/mall.jpg",800,600);
//		tBox.setText("The next day...");
//		tBox.setC(Color.black);
//		sleep(2000);
//		tBox.setText("");
//		addObject(you);
//		you.setX(400);
//		yLabel.setX(350);
//		yLabel.setY(270);
//		yLabel.setC(Color.white);
//		yLabel.setText("Oh man I should buy her a gift or something");
//		sleep(2000);
//		yLabel.setText("I should go on... a shopping spree!11");
//		sleep(2500);
//		yLabel.setText("");
//		remove(you);
		LoveHunterXX.game.setScreen(LoveHunterXX.sis);
	}
	
	public void playSequence2(){
		back.loadImages("resources/lot.jpg", 800,600);
		addObject(you);
		you.setX(200);
		you.setY(500);
	}
	
	public void sleep(long t){
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
