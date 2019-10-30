package com.Diamond.Finder.UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.JPanel;

import com.Diamond.Finder.States.GameState;
import com.Diamond.Finder.States.Keys;

public class GamePane extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 128;
	public static final int HEIGHT = 128;
	public static final int HEIGHTT = HEIGHT + 16;
	public static final int SCALE = 3;

	public Thread thread;
	private boolean isRunning;
	private int FPS = 30;
	private int time = 1000 / FPS;
	private BufferedImage image;
	private Graphics2D g2d;
	
	public GameState state;

	public GamePane() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHTT * SCALE));
		this.setFocusable(true);
		requestFocus();
	}

	private void init() {
		isRunning = true;
		image = new BufferedImage(WIDTH, HEIGHTT, 1);
		g2d = (Graphics2D) image.getGraphics();
		state = new GameState();
	}

	public void update() {
		// TODO Auto-generated method stub
		// gamestate update
		// key update
		state.update();
		Keys.update();
	}

	public void draw() {
		// draw gamestate
		state.draw (g2d);
	}

	private void drawToScreen() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHTT * SCALE, null);
		g2.dispose();
	}

	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		if (thread == null) {
			addKeyListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		Keys.keySet(arg0.getKeyCode(), true);
		//System.out.println(Keys.UP + " " + KeyEvent.VK_UP);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		/// keys update
		Keys.keySet(arg0.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		/// keys update
	}

	@Override
	public void run() {

		init();
		long start;
		long end;
		long wait;
		while (isRunning) {
			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			end = System.nanoTime();
			end = end - start;
			wait = time - end / 1000000;

			if (wait < 0)
				wait = time;
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
