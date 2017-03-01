package gui.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Graphic implements Visible {

	private BufferedImage image;
	private boolean loadedImages;
	private int x;
	private int y;

	// full size graphics constructor
	public Graphic(int x, int y, String imageLocation) {
		this.x = x;
		this.y = y;
		loadedImages = false;
		loadImages(imageLocation, 0, 0);
	}

	public Graphic(int x, int y, int w, int h, String imageLocation) {
		this.x = x;
		this.y = y;
		loadedImages = false;
		loadImages(imageLocation, w, h);
	}

	public Graphic(int x, int y, double scale, String imageLocation) {
		this.x = x;
		this.y = y;
		loadedImages = false;
		loadImages(imageLocation, scale);
	}

	public void loadImages(String imageLocation, double scale) {
		try {
			ImageIcon icon = new ImageIcon(imageLocation);
			int newWidth = (int) (icon.getIconWidth() * scale);
			int newHeight = (int) (icon.getIconHeight() * scale);
			image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(icon.getImage(), 0, 0, newWidth, newHeight, 0, 0, icon.getIconWidth(), icon.getIconHeight(),
					null);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, newWidth - 1, newHeight - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadImages(String imageLocation, int w, int h) {
		try {
			// full size
			ImageIcon icon = new ImageIcon(imageLocation);

			if (w <= 0 && h <= 0) {
				image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = image.createGraphics();
				g.drawImage(icon.getImage(), 0, 0, null);
				g.drawRect(0, 0, icon.getIconHeight() - 1, icon.getIconWidth() - 1);
			} else {
				image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = image.createGraphics();

				g.drawImage(icon.getImage(), 0, 0, w, h, 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
				g.drawRect(0, 0, w - 1, h - 1);
			}
			loadedImages = true;
		} catch (Exception e) {
			// when it can't find the image
			e.printStackTrace();
		}
	}

	public boolean isLoadedImages() {
		return loadedImages;
	}

	public void setLoadedImages(boolean loadedImages) {
		this.loadedImages = loadedImages;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public boolean isAnimated() {
		return false;
	}

	public void update() {
		// does nothing - images stay the same

	}

}
