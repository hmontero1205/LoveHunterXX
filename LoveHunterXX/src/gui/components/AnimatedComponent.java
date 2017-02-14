package gui.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedComponent extends MovingComponent{

	private ArrayList<BufferedImage> frame; //the images that can be displayed
	private ArrayList<Integer> times; //the time each image is displayed
	private long displayTime; //the time when the last image switched
	private int currentFrame; //the frame that is currently being displayed
	public ArrayList<BufferedImage> getFrame() {
		return frame;
	}

	public void setFrame(ArrayList<BufferedImage> frame) {
		this.frame = frame;
	}

	public ArrayList<Integer> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Integer> times) {
		this.times = times;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(long displayTime) {
		this.displayTime = displayTime;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public boolean isRepeat() {
		return repeat;
	}

	private boolean repeat;

	public static final int REFRESH_RATE = 20;

	public AnimatedComponent(int x, int y, int w, int h) {
		super(x, y, w, h);
		frame = new ArrayList<BufferedImage>();
		times = new ArrayList<Integer>();
		currentFrame = 0;
		repeat = true;
	}

	public void setRepeat(boolean b){
		repeat = b;
	}

	public boolean isAnimated(){
		return true;
	}
	
	public void addFrame(BufferedImage image, Integer time){
		frame.add(image);
		this.times.add(time);
	}

	public void drawImage(Graphics2D g) {
		long currentTime = System.currentTimeMillis();//gets time now
		//check if it's time to change the frame
		//and make sure that there are images in the frame list
		if(frame != null && frame.size() > 0 && frame.size() == times.size() && currentTime - displayTime > times.get(currentFrame)){
			displayTime = currentTime;

			//increase the currentFrameIndex but don't exceed size()
			currentFrame = (currentFrame+1)%frame.size();
			//end animation if not on repeat
			if(currentFrame == 0 && !repeat){
				setRunning(false);
				return;
			}
			//clear the previous image
			g = clear();
			BufferedImage newFrame = frame.get(currentFrame);
			g.drawImage(newFrame, 0,0,getWidth(),getHeight(),0,0,newFrame.getWidth(),newFrame.getHeight(),null);
		}
	}

}
