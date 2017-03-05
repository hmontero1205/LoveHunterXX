/**
 * @author Hans
 *
 */
package main;

import javax.swing.ImageIcon;

import frogger.HansFroggerGame;
import frogger.HansFroggerIntroScreen;
import frogger.HansFroggerScreen;
import gui.GUIApplication;
import platformer.ShohebPlatformerIntro;
import platformer.ShohebPlatformerScreen;
import quenchTheThirst.BillyQTTScreen;
import quenchTheThirst.BillyShooterIntroScreen;
import snake.MichaelDavidSnakeScreen;
import snake.MichaelIntroScreen;

public class LoveHunterXX extends GUIApplication {
	public static LoveHunterXX game;
	public static HansTransitionScreen ts;
	
	public static MichaelDavidSnakeScreen ss;
	public static MichaelIntroScreen sis;
	
	public static HansFroggerScreen fs;
	public static HansFroggerIntroScreen fis;
	
	public static BillyQTTScreen qtts;
	public static BillyShooterIntroScreen qttis;
	
	public static ShohebPlatformerIntro pis;
	public static ShohebPlatformerScreen ps;
	
	public LoveHunterXX(int width, int height) {
		super(width, height);
		setResizable(true);
	}

	@Override
	public void initScreen() {
		ts = new HansTransitionScreen(800,600);
		sis = new MichaelIntroScreen(800,600);
		ss = new MichaelDavidSnakeScreen(800,600);
		
		fis = new HansFroggerIntroScreen(800,600);
		fs = new HansFroggerScreen(800,600);
		
		qttis = new BillyShooterIntroScreen(800,600);
		qtts = new BillyQTTScreen(800,600);
		
		pis = new ShohebPlatformerIntro(800,600);
		ps = new ShohebPlatformerScreen(800,600);
	}
	
	public static void main(String[] args){
		game = new LoveHunterXX(800, 600);
		game.setTitle("LoveHunterXX");
		game.setIconImage(new ImageIcon("resources/LoveHunterXXIcon.png").getImage());
		Thread go = new Thread(game);
		go.start();
		game.setScreen(fs);
	}

}
