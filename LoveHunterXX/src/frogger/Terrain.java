package frogger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import gui.components.Component;
import gui.components.Visible;

public class Terrain extends Component implements Runnable {
	
	private ArrayList<Obstacle> obs;
	private ArrayList<Platform> pf;
	private ArrayList<AnimatedPlatform> apf;
	private int terrain;
	private Color[] terrainColors = {Color.green,Color.darkGray,Color.blue};
	private boolean superCreated;
	private int obsDir;
	
	public Terrain(int x, int y, int w, int h, int terrain) {
		super(x, y, w, h);
		this.terrain = terrain;
		obs = new ArrayList<Obstacle>();
		this.obsDir = (Math.random()>.3)? 3:-3;
		superCreated = true;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		if(superCreated){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(terrainColors[terrain]);
			g.fillRect(0, 0, getWidth()-1,getHeight());
			g.setColor(Color.black);
			g.drawRect(0, 0, getWidth()-1,getHeight()-1);
		}
	}

	public void generateObstacles() {
		if(this.terrain==1){
			int randomLength = (int)(Math.random() *3)+4;
			for(int i=0;i<randomLength;i++){
				int startingPos = (obsDir>0) ? 0:800;
				String carSrc = (Math.random()>.5) ? "greencar.png":"truck.png";
				Obstacle c1 = new Obstacle(startingPos,getY()+10,50,25,obsDir,"resources/frogger/"+carSrc);
				obs.add(c1);
				FroggerGame.fs.addObject(c1);
				FroggerGame.fs.moveToBack(c1);
				c1.play();	
				try {
					int randomTime = (int)(Math.random() * 1200)+800;
					Thread.sleep(randomTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public void startThread(){
		Thread tThread = new Thread(this);
		tThread.start();
	}
	@Override
	public void run() {
		generateObstacles();
//		while(true){
//			checkPlayer();
//		}
		
	}

    private void checkPlayer() {
		// TODO Auto-generated method stub
		
	}

//	public void checkOutOfBounds() {
//		if(obs.size()>0){
//			Obstacle inFront = obs.get(0);
//			System.out.println(inFront.getX());
//			if(inFront.getX()>650){
//				obs.remove(inFront);
//				FroggerGame.fs.remove(inFront);
//				try {
//					Thread.sleep(500);
//					addNewObs();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//	}
	
//	public void addNewObs(){
//		Obstacle c1 = new Obstacle(0,getY()+10,50,25,3,"resources/frogger/truck.png");
//		obs.add(c1);
//		FroggerGame.fs.addObject(c1);
//		FroggerGame.fs.moveToBack(c1);
//		c1.play();
//	}

	public int getTerrain() {
		// TODO Auto-generated method stub
		return terrain;
	}
	

}
