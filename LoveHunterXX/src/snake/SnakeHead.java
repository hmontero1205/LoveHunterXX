package snake;

public class SnakeHead extends Interactable {
	
	private String sprite;

	public SnakeHead(int x, int y, int width, int height, String path) {
		super(x, y, width, height, path);
	}

	public void setSprite(String str){
		loadImages(str,30,30);
		System.out.println("I was called");
	}
}
