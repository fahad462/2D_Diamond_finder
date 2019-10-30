package com.Diamond.Finder.AllStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.Diamond.Finder.Graphics.Entity;
import com.Diamond.Finder.States.Content;
import com.Diamond.Finder.States.GameState;
import com.Diamond.Finder.States.Keys;
import com.Diamond.Finder.UI.GamePane;

public class startState extends States {
	private BufferedImage logo;

	private int alpha;
	private int ticks;

	private final int FADE_IN = 60;
	private final int LENGTH = 60;
	private final int FADE_OUT = 60;

	public startState(GameState gsm) {
		super(gsm);
	}

	public void init() {
		ticks = 0;
		try {
			//logo = ImageIO.read(getClass().getResourceAsStream("/Logo/car_logo.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		handleInput();
		ticks++;
		if (ticks < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if (alpha < 0)
				alpha = 0;
		}
		if (ticks > FADE_IN + LENGTH) {
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if (alpha > 255)
				alpha = 255;
		}
		if (ticks > FADE_IN + LENGTH + FADE_OUT) {
			gsm.setState(GameState.MENU);
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(120, 120, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 120, 120, null);
		g.dispose();

		return resizedImage;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, GamePane.WIDTH, GamePane.HEIGHTT);
		// BufferedImage resizedImage = new BufferedImage(144, 144,
		// BufferedImage.TYPE_INT_RGB);
		// g = resizedImage.createGraphics();
		//g.drawImage(resizeImage(logo, BufferedImage.TYPE_INT_RGB), 0, 0, GamePane.WIDTH, GamePane.HEIGHTT, null);
		Content.drawString(g,"FAHAD", GamePane.WIDTH/2-25+5, GamePane.HEIGHT/2);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePane.WIDTH, GamePane.HEIGHTT);
	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameState.MENU);
		}
	}
}
