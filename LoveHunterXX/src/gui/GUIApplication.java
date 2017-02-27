package gui;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public abstract class GUIApplication extends JFrame implements Runnable {

	private Screen currentScreen;

	public GUIApplication(int width, int height) {
		super();
		setBounds(20, 20, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initScreen();
		setVisible(true);
	}

	public abstract void initScreen();

	public void paint(Graphics g) {
		g.drawImage(currentScreen.getImage(), 0, 0, null);
	}

	public void setScreen(Screen s) {
		removeListeners();
		currentScreen = s;
		addListeners();
	}

	private void removeListeners() {
		if (currentScreen != null) {
			if (currentScreen.getMouseListener() != null)
				removeMouseListener(currentScreen.getMouseListener());
			if (currentScreen.getMouseMotionListener() != null)
				removeMouseMotionListener(currentScreen.getMouseMotionListener());
			if (currentScreen.getKeyListener() != null)
				removeKeyListener(currentScreen.getKeyListener());
			if (currentScreen.getMouseWheelListener() != null)
				removeMouseWheelListener(currentScreen.getMouseWheelListener());
		}
	}

	private void addListeners() {
		if (currentScreen != null) {
			if (currentScreen.getMouseListener() != null)
				addMouseListener(currentScreen.getMouseListener());
			if (currentScreen.getMouseMotionListener() != null)
				addMouseMotionListener(currentScreen.getMouseMotionListener());
			if (currentScreen.getKeyListener() != null)
				addKeyListener(currentScreen.getKeyListener());
			if (currentScreen.getMouseWheelListener() != null)
				addMouseWheelListener(currentScreen.getMouseWheelListener());
		}
	}

	public void run() {
		while (true) {
			currentScreen.update();
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
