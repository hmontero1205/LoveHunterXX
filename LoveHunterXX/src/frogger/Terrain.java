package frogger;

import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.components.Component;

public class Terrain extends Component {
	
	private ArrayList<Obstacle> obs;
	private ArrayList<Platform> pf;
	private ArrayList<AnimatedPlatform> apf;
	
	public Terrain(int x, int y, int w, int h, int terrainType) {
		super(x, y, w, h);
		if(terrainType == FroggerScreen.ROAD) {
			obs = new ArrayList<Obstacle>();
		} else if(terrainType == FroggerScreen.WATER) {
			double rand = Math.random() * 1;
		} else if(terrainType == FroggerScreen.SAFEZONE) {
			
		}
	}

	@Override
	public void update(Graphics2D arg0) {
		// TODO Auto-generated method stub

	}

}
