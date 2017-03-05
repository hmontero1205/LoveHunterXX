package main;

import java.awt.Color;
import java.util.List;

import gui.Screen;
import gui.components.Graphic;
import gui.components.TextArea;
import gui.components.TextLabel;
import gui.components.Visible;

public class HansTransitionScreen extends Screen implements Runnable{
	private Thread sThread;
	private Graphic back;
	private Graphic you;
	private Graphic her;
	private TextArea tBox;
	private TextLabel hLabel;
	private TextLabel yLabel;
	public int lovePoints;

	public int sequence;

	public HansTransitionScreen(int width, int height) {
		super(width, height);

		this.sequence = 0;
	}

	@Override
	public void initObjects(List<Visible> viewObjects) {
		back = new Graphic(0, 0, "resources/LoveHunterXXScreen.png");
		you = new Graphic(550, 300, "resources/you.png");
		her = new Graphic(200, 250, "resources/her.png");
		tBox = new TextArea(280, 110, 500, 30, "Previously on LoveHunterX....");
		hLabel = new TextLabel(300, 260, 500, 25, "");
		yLabel = new TextLabel(500, 280, 650, 25, "");

		tBox.setC(Color.white);
		viewObjects.add(back);
		viewObjects.add(hLabel);
		hLabel.setC(Color.white);
		viewObjects.add(yLabel);
		yLabel.setC(Color.white);
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void onDisplay() {
		new Thread(this).start();
	}
	
	public void run() {
		switch (sequence) {
		case 0:
			playSequence1();
			break;
		case 1:
			playSequence2();
			break;
		case 2:
			playSequence3();
			break;
		case 3:
			playSequence4();
			break;
		case 4:
			playSequence5();
			break;
		}
	}

	public void playSequence1() {
		// sequence 1
		sleep(1000);
		back.loadImages("resources/bedroom.jpg", 800, 600);
		sleep(500);
		addObject(tBox);
		sleep(2000);
		sleep(500);
		addObject(you);
		addObject(her);
		hLabel.setText("What took you so long? xD");
		tBox.setText("");
		sleep(2000);
		hLabel.setText("");
		yLabel.setText("I wanted to ask you something...");
		sleep(2000);
		yLabel.setText("LET'S GO ON A DATE PLS");
		sleep(2000);
		hLabel.setText("Yeah sure lol");
		yLabel.setText("");
		sleep(2000);
		hLabel.setText("");
		remove(her);
		remove(you);
		back.loadImages("resources/mall.jpg", 800, 600);
		tBox.setText("The next day...");
		tBox.setC(Color.black);
		sleep(2000);
		tBox.setText("");
		addObject(you);
		you.setX(400);
		yLabel.setX(350);
		yLabel.setY(270);
		yLabel.setC(Color.white);
		yLabel.setText("Oh man I should buy her a gift or something");
		sleep(2000);
		yLabel.setText("I should go on... a shopping spree!11");
		sleep(2500);
		yLabel.setText("");
		remove(you);

		LoveHunterXX.game.setScreen(LoveHunterXX.sis);
	}

	public void playSequence2() {
		back.loadImages("resources/lot.jpg", 800, 600);
		addObject(you);
		you.setX(400);
		you.setY(320);
		yLabel.setX(320);
		yLabel.setY(305);
		String text = (lovePoints > 2) ? "I'm such a great bf I got her a cool gift"
				: "I'm an awful bf I didn't get a good gift";
		yLabel.setText(text);
		sleep(2500);
		yLabel.setText("Oh well. It's time to go meet her at the park.");
		sleep(2500);
		yLabel.setX(150);
		yLabel.setText("Hmm to get there, I'll have to get to the other side of this street haha");
		sleep(2500);
		yLabel.setText("");
		remove(you);
		sleep(1500);

		LoveHunterXX.game.setScreen(LoveHunterXX.fis);
	}

	public void sleep(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void playSequence3() {
		back.loadImages("resources/park.jpg", 800, 600);
		addObject(you);
		you.setX(250);
		you.setY(320);
		
		her.setX(50);
		her.setY(320);
		addObject(her);
		hLabel.setX(20);
		hLabel.setY(290);
		
		String text = (lovePoints<4) ? "It's about time to made it smh" : "Hey babe I missed u :3";
		hLabel.setText(text);
		yLabel.setX(250);
		yLabel.setY(300);
		sleep(2500);
		hLabel.setText("");
		text =  (lovePoints<4) ? "Sorry for the hold up :/" : "heyyy I got u a gift :)";
		yLabel.setText(text);
		sleep(2500);
		yLabel.setText("Anyways let's go to the park!");
		sleep(2500);
		yLabel.setText("Hopefully there won't be any thirsty guys xD");
		sleep(1500);
		remove(you);
		remove(her);
		yLabel.setText("");

		LoveHunterXX.game.setScreen(LoveHunterXX.qttis);
	}

	public void playSequence4() {
		back.loadImages("resources/boardwalk.jpg", 800, 600);
		addObject(you);
		you.setX(600);
		you.setY(320);
		her.setX(150);
		her.setY(320);
		addObject(her);
		hLabel.setC(Color.black);
		yLabel.setC(Color.black);
		hLabel.setX(250);
		hLabel.setY(340);
		String text = (lovePoints < 5) ? "That park was awful" : "Thank you for protecting me :3";
		hLabel.setText(text);
		sleep(2500);
		hLabel.setText("");
		yLabel.setX(400);
		yLabel.setY(340);
		text = (lovePoints < 5) ? "Sorry about that yikes" : "Glad you enjoyed dude";
		yLabel.setText(text);
		sleep(2500);
		yLabel.setText("");
		yLabel.setX(250);
		yLabel.setText("How about a nice long walk on the beach?;)");
		sleep(2500);
		yLabel.setText("");
		remove(her);
		remove(you);
		
		LoveHunterXX.game.setScreen(LoveHunterXX.pis);
	}

	public void playSequence5() {
		back.loadImages("resources/beach.jpg", 800, 600);
		addObject(you);
		addObject(her);
		her.setX(350);
		her.setY(330);
		String text = (lovePoints < 8) ? "Everything hurts owie :(" : "That was really romantic~~";
		hLabel.setX(300);
		hLabel.setY(300);
		hLabel.setC(Color.black);
		hLabel.setText(text);
		sleep(2500);
		hLabel.setText(" ");
		text = (lovePoints > 8) ? "Yikes this day went poorly" : ";) ;) ;)";
		yLabel.setC(Color.black);
		yLabel.setText(text);
		sleep(2500);
		yLabel.setText("");
		hLabel.setText("Look. I have something to tell you.");
		sleep(2500);
		hLabel.setText("");
		yLabel.setText("!!!!!1!!!!!1!!1!1!11");
		sleep(2500);
		yLabel.setText("");
		hLabel.setText("I think I....");
		sleep(2500);
		tBox.setText("TO BE CONTINUED!!!");
	}

	public int getSequence() {
		return this.sequence;
	}
}