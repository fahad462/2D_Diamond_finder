package com.Diamond.Finder.AllStates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.Diamond.Finder.States.AudioPlayer;
import com.Diamond.Finder.States.Content;
import com.Diamond.Finder.States.GameState;
import com.Diamond.Finder.States.Keys;

public class Menu extends States {

	private BufferedImage bg;
	private BufferedImage diamond;

	private int currentOption = 0;
	private String[] options = { "START", "QUIT" };

	public Menu(GameState gsm) {
		super(gsm);
	}

	public void init() {
		bg = Content.MENUBG[0][0];
		diamond = Content.DIAMOND[0][0];
		// AudioPlayer.load("/SFX/collect.wav", "collect");
		// AudioPlayer.load("/SFX/menuoption.wav", "menuoption");
	}

	@Override
	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {

		g.drawImage(bg, 0, 0, null);

		Content.drawString(g, "MIST Student", 20, 30);
		Content.drawString(g, "And", 50, 40);
		Content.drawString(g, "Poralekha :3", 20, 50);
		
		Content.drawString(g, options[0], 44, 90);
		Content.drawString(g, options[1], 48, 100);

		if (currentOption == 0)
			g.drawImage(diamond, 25, 86, null);
		else if (currentOption == 1)
			g.drawImage(diamond, 25, 96, null);
		//System.out.println("called");
	}

	public void handleInput() {
		//System.out.println(Keys.isPressed(Keys.DOWN));
		if (Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			//AudioPlayer.play("menuoption");
			
			currentOption++;
		}
		if (Keys.isPressed(Keys.UP) && currentOption > 0) {
			//AudioPlayer.play("menuoption");
			currentOption--;
		}
		if (Keys.isPressed(Keys.ENTER)) {
			//AudioPlayer.play("collect");
			selectOption();
		}
	}

	private void selectOption() {
		if (currentOption == 0) {
			gsm.setState(GameState.PLAY);
		}
		if (currentOption == 1) {
			System.exit(0);
		}
	}

}
