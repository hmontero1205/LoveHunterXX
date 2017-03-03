/**
 * @author Hans
 *
 */
package main;

import frogger.HansFroggerIntroScreen;
import frogger.HansFroggerScreen;
import gui.GUIApplication;
import quenchTheThirst.BillyQTTScreen;
import quenchTheThirst.BillyShooterIntroScreen;
import snake.MichaelDavidSnakeScreen;
import snake.MichaelIntroScreen;

public class LoveHunterXX extends GUIApplication {
	public MichaelDavidSnakeScreen ss;
	public MichaelIntroScreen sis;
	
	public HansFroggerScreen fs;
	public HansFroggerIntroScreen fis;
	
	public BillyQTTScreen qtts;
	public BillyShooterIntroScreen qttis;
	
	public LoveHunterXX(int width, int height) {
		super(width, height);
		setResizable(false);
	}

	@Override
	public void initScreen() {
		// TODO Auto-generated method stub
		
	}

	

}
