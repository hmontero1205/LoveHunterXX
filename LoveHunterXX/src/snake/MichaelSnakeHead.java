package snake;

public class MichaelSnakeHead extends MichaelInteractable {
	
	private String sprite;

	public MichaelSnakeHead(int x, int y, int width, int height, String path) {
		super(x, y, width, height, path);
	}

	public void setSprite(String str){
		loadImages(str,30,30);
	}
}
